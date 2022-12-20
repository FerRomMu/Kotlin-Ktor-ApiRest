package vsapp.controllers

import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.UserDTO
import vsapp.model.dtos.mapping.UserMapper
import vsapp.service.UserServiceImpl

class UserController() {

    private val userMapper = UserMapper()
    private val service = UserServiceImpl()

    /**
     * Checks the given user to exists and returns his DTO.
     * param: data has the user and the password needed for the user to login.
     * returns: UserDTO of the logged user or null if there is no user with such password and username.
     */
    fun login(data: LoginUserDTO): UserDTO? {
        return userMapper.toDTO(service.login(data.user, data.password))
    }

    /**
     * Get the userDTO for the given user id.
     * param: the id of the user to get his dto.
     * returns: UserDTO of the user or null if there is no user with such id.
     */
    fun getUser(id: Long): UserDTO? {
        return userMapper.toDTO(service.getUser(id))
    }
}