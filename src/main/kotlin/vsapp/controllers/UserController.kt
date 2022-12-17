package vsapp.controllers

import vsapp.exceptions.WrongLoginException
import vsapp.service.data.AppSystem
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.UserDTO

class UserController() {

    private val system = AppSystem

    /**
     * Checks the given user to exists and returns his DTO.
     * param: data has the user and the password needed for the user to login.
     * returns: UserDTO of the logged user.
     * throws: WrongLoginException when password or user are wrong.
     */
    fun login(data: LoginUserDTO): UserDTO {
        return system.users[Pair(data.user, data.password)] ?: throw WrongLoginException("Wrong user or password.")
    }
}