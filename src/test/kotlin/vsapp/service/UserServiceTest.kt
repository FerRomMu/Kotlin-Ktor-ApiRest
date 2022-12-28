package vsapp.service

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import vsapp.exceptions.ConflictMailOrUserException
import vsapp.model.User
import vsapp.repository.UserDAO
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UserServiceTest {

    private val userDao = mockk<UserDAO>()
    private val userService = UserServiceImpl(userDao)
    private val userMock = mockk<User>()
    /*
    @BeforeTest
    fun setUp() {
        every { userDao.getUser(any()) } returns null
        every { userDao.signUp(any(), any(), any()) } returns User()
        every { userDao.deleteUser(any()) } returns Unit
    }*/

    @Test
    fun `login returns null if user does not exist`() {
        //Setup
        val username = "user"
        val password = "password"
        every { userDao.getHash(username) } returns null

        //Exercise
        val user = userService.login(username, password)

        //Verify
        assertNull(user)
        verify(exactly = 0) { userDao.userByUsername(username) }
    }

    @Test
    fun `login returns null if password is wrong`() {
        //Setup
        val username = "user"
        val password = "password"
        every { userDao.getHash(username) } returns "wrongPassword"

        //Exercise
        val user = userService.login(username, password)

        //Verify
        assertNull(user)
        verify(exactly = 0) { userDao.userByUsername(username) }
    }

    @Test
    fun `login returns user if exists and password is correct`() {
        //Setup
        val username = "user"
        val password = "password"
        every { userDao.getHash(username) } returns "password"
        every { userDao.userByUsername(username) } returns userMock
        //Exercise
        val user = userService.login(username, password)

        //Verify
        assertEquals(user, userMock)
        verify(exactly = 1) { userDao.userByUsername(username) }
    }

    @Test
    fun `getUser returns null if user does not exist`() {
        //Setup
        every { userDao.userById(1L) } returns null
        val userId = 1L

        //Exercise
        val user = userService.getUser(userId)

        //Verify
        assertNull(user)
        verify(exactly = 1) { userDao.userById(1L) }
    }

    @Test
    fun `getUser returns user if exists`() {
        //Setup
        every { userDao.userById(1L) } returns userMock
        val userId = 1L

        //Exercise
        val user = userService.getUser(userId)

        //Verify
        assertEquals(user, userMock)
        verify(exactly = 1) { userDao.userById(1L) }
    }

    @Test(expected = ConflictMailOrUserException::class)
    fun `signUp throws ConflictMailOrUserException if username is already in use`() {
        //Setup
        every { userDao.isUserInUse("user") } returns true
        every { userDao.isMailInUse("email@example.com") } returns false
        val username = "user"
        val password = "password"
        val email = "email@example.com"

        //Excercise
        userService.signUp(username, password, email)
    }

    @Test(expected = ConflictMailOrUserException::class)
    fun `signUp throws ConflictMailOrUserException if email is already in use`() {
        //Setup
        every { userDao.isUserInUse("user") } returns false
        every { userDao.isMailInUse("email@example.com") } returns true
        justRun { userDao.editPasswordUser(any()) }

        val username = "user"
        val password = "password"
        val email = "email@example.com"

        //Exercise
        userService.signUp(username, password, email)
    }

    @Test
    fun `signUp returns user if registration was successful`() {
        //Setup
        every { userDao.isUserInUse("user") } returns false
        every { userDao.isMailInUse("email@example.com") } returns false
        every { userDao.createUser(any()) } returns userMock
        justRun { userDao.editPasswordUser(any()) }
        val username = "user"
        val password = "password"
        val email = "email@example.com"

        //Exercise
        val user = userService.signUp(username, password, email)
        assertEquals(user, userMock)
    }

    @Test
    fun `deleteUser deletes user if exists`() {
        //Setup
        val userId = 1L
        every { userDao.userById(userId) } returns userMock
        justRun { userDao.deleteUser(userId) }

        //Exercise
        val result = userService.deleteUser(userId)

        assertTrue(result)
        verify(exactly = 1) { userDao.deleteUser(userId) }
    }

    @Test
    fun `deleteUser returns false if user does not exist`() {
        //Setup
        val userId = 1L
        every { userDao.userById(userId) } returns null

        //Exercise
        val result = userService.deleteUser(userId)

        assertFalse(result)
        verify(exactly = 0) { userDao.deleteUser(userId) }
    }
}