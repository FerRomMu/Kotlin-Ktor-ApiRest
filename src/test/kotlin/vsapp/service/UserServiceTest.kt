package vsapp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import vsapp.model.User
import vsapp.repository.UserDAO
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
    /*
    @Test
    fun `getUser returns null if user does not exist`() {
        val userId = 1L
        val user = userService.getUser(userId)
        assertTrue(user == null)
    }

    @Test
    fun `getUser returns user if exists`() {
        val userId = 1L
        every { userDao.getUser(userId) } returns User(id = userId)
        val user = userService.getUser(userId)
        assertEquals(userId, user?.id)
    }

    @Test(expected = ConflictMailOrUserException::class)
    fun `signUp throws ConflictMailOrUserException if email or username is already in use`() {
        val username = "user"
        val password = "password"
        val email = "email@example.com"
        every { userDao.signUp(username, password, email) } throws ConflictMailOrUserException()
        userService.signUp(username, password, email)
    }

    @Test
    fun `signUp returns user if registration was successful`() {
        val username = "user"
        val password = "password"
        val email = "email@example.com"
        val user = userService.signUp(username, password, email)
        assertEquals(username, user?.username)
        assertEquals(password, user?.password)
        assertEquals(email, user?.email)
    }

    @Test
    fun `deleteUser deletes user if exists`() {
        val userId = 1L
        every { userDao.deleteUser(userId) } returns Unit
        userService.deleteUser(userId)
        verify { userDao.deleteUser(userId) }
    }*/
}