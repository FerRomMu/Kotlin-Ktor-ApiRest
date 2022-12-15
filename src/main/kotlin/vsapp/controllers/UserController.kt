package vsapp.controllers

import vsapp.exceptions.WrongLoginException
import vsapp.service.data.AppSystem
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.UserDTO

class UserController() {
    private val system = AppSystem

    fun login(data: LoginUserDTO): UserDTO {
        return system.users[Pair(data.user, data.password)] ?: throw WrongLoginException("Wrong user or password.")
    }
}