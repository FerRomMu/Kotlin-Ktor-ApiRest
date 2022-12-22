package vsapp.controllers

import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.SignInDTO
import vsapp.model.dtos.UserDTO
import vsapp.model.dtos.mapping.UserMapper
import vsapp.service.UserService
import vsapp.service.UserServiceImpl

class UserController(private val userMapper: UserMapper,
                     private val service: UserService
) {

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

    /**
     * Try to sign in a new user and returns the UserDTO for that new user.
     * param: data has the user, password and email for the user to signin.
     * returns: UserDTO of the register user or null if it was not possible.
     */
    fun signIn(data: SignInDTO): UserDTO? {
        return userMapper.toDTO(service.signIn(data.user, data.password, data.email))
    }
}