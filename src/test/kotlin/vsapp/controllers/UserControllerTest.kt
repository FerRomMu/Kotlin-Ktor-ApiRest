package vsapp.controllers

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import vsapp.model.User
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.SignInDTO
import vsapp.model.dtos.UserDTO
import vsapp.model.dtos.mapping.UserMapper
import vsapp.service.UserService
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class UserControllerTest {

    private val mockService = mockk<UserService>()
    private val mockMapper = mockk<UserMapper>()
    private val userController = UserController(mockMapper, mockService)
    private val aUser = mockk<User>()
    private val aUserDTO = mockk<UserDTO>()

    @BeforeTest
    fun setup() {
        every { mockService.login(eq("a"), eq("a")) } returns aUser
        every { mockService.login(eq("a"), neq("a")) } returns null

        every { mockService.getUser(eq(0L)) } returns aUser
        every { mockService.getUser(neq(0L)) } returns null

        every { mockService.signUp(eq("fafafa"),eq( "1234"), eq("fa@test.com")) } returns aUser
        every { mockService.signUp(neq("fafafa"),any(), any()) } returns null
        every { mockService.signUp(any(),any(), neq("fa@test.com")) } returns null

        every { mockMapper.toDTO(eq(aUser)) } returns(aUserDTO)
        every { mockMapper.toDTO(isNull()) } returns(null)
    }

    @Test
    fun `si el usuario es valido al loguear obtengo su DTO`() {
        val result = userController.login(LoginUserDTO("a", "a"))
        assertEquals(result, aUserDTO)
    }

    @Test
    fun `si el usuario es invalido al loguear devuelve null`() {
        val result = userController.login(LoginUserDTO("a", "asd"))
        assertEquals(result, null)
    }

    @Test
    fun `si el usuario existe al pedirlo lo recibo`() {
        val result = userController.getUser(0L)
        assertEquals(result, aUserDTO)
    }

    @Test
    fun `si el usuario no existe al pedirlo recibo null`() {
        val result = userController.getUser(999999999999999L)
        assertEquals(result, null)
    }

    @Test
    fun `si trato de registrar un usuario valido, se registra y me lo devuelve`() {
        val newUser = userController.signUp(SignInDTO("fafafa", "1234", "fa@test.com"))
        assertEquals(newUser, aUserDTO)
    }

    @Test
    fun `si trato de registrar a alguien con un mail o username invalido no se registra`() {
        var newUser = userController.signUp(SignInDTO("a", "1234", "fa@test.com"))
        assertEquals(newUser, null)
        newUser = userController.signUp(SignInDTO("fafafa", "1234", "a@a"))
        assertEquals(newUser, null)
    }
}