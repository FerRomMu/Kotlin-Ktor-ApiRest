package vsapp.service

import vsapp.model.User

interface UserService {
    /**
     * Try to login a user using the username and the password given.
     * params: user is his username and password is his password.
     * returns: The user with such password and username or null if there is not such user.
     */
    fun login(user: String, password: String): User?

    /**
     * Get the user with the given id.
     * params: The id of the user to get.
     * returns: The user with given id or null.
     */
    fun getUser(id: Long): User?

    /**
     * Register on the database a new user if possible and returns it.
     * params: user, password and email of the new user to register.
     * returns: The user created if it has been registered or null if not.
     * throws: ConflictMailOrUserException if the email or username of the user to signup is
     * already in use.
     */
    fun signUp(user: String, password: String, email: String): User?
}