package vsapp.controllers

import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.function.ThrowingRunnable
import vsapp.exceptions.WrongLoginException
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO
import vsapp.repository.AppSystem
import kotlin.test.assertEquals

class UserControllerTest {
    private val userController = UserController(AppSystem)

    @Test
    fun `si el usuario es valido al loguear obtengo su DTO`() {
        val loginData = LoginUserDTO("a", "a")
        val result = userController.login(loginData)
        assertEquals(result, UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda")))
    }

    @Test
    fun `si el usuario es invalido al loguear un error de login`() {
        val loginData = LoginUserDTO("a", "asd")
        assertThrows("Wrong user or password.", WrongLoginException::class.java, ThrowingRunnable(){ userController.login(loginData) })
    }

}