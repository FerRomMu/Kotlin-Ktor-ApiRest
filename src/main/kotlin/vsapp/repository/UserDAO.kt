package vsapp.repository

import vsapp.model.dtos.PasswordUserDTO
import vsapp.model.User

interface UserDAO {
    /**
     * Returns user with the given Id.
     */
    fun userById(id: Long): User?

    /**
     * Returns user with the given username.
     */
    fun userByUsername(user: String): User?

    /**
     * creates a user.
     */
    fun createUser(user: User): User

    /**
     * register the password of the user.
     */
    fun editPasswordUser(passwordUser: PasswordUserDTO): PasswordUserDTO

    /**
     * deletes a user.
     */
    fun deleteUser(id: Long)

    /**
     * Returns hash of the password of the given user if exists.
     */
    fun getHash(user: String): String?
}