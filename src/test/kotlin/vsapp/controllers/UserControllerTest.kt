package vsapp.controllers

import org.junit.Test
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.SignInDTO
import vsapp.model.dtos.UserDTO
import kotlin.test.assertEquals

class UserControllerTest {
    private val userController = UserController()
    private val testUser = UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda"))

    @Test
    fun `si el usuario es valido al loguear obtengo su DTO`() {
        val result = userController.login(LoginUserDTO("a", "a"))
        assertEquals(result, testUser)
    }

    @Test
    fun `si el usuario es invalido al loguear devuelve null`() {
        val result = userController.login(LoginUserDTO("a", "asd"))
        assertEquals(result, null)
    }

    @Test
    fun `si el usuario existe al pedirlo lo recibo`() {
        var result = userController.getUser(0L)
        assertEquals(result, testUser)
    }

    @Test
    fun `si el usuario no existe al pedirlo recibo null`() {
        val result = userController.getUser(999999999999999L)
        assertEquals(result, null)
    }

    @Test
    fun `si trato de registrar un usuario valido, se registra y puedo recuperarlo`() {
        val newUser = userController.signIn(SignInDTO("fafafa", "12324", "fafafa@fa.com"))
        assertEquals(newUser, userController.getUser(newUser!!.id))
    }

    @Test
    fun `si trato de registrar a alguien con un mail o username invalido no se registra`() {
        var newUser = userController.signIn(SignInDTO("a", "fdakad", "fafafa@fa.com"))
        assertEquals(newUser, null)
        newUser = userController.signIn(SignInDTO("fafafa", "vafda", "a@a"))
        assertEquals(newUser, null)
    }
}