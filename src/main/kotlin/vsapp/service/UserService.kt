package vsapp.service

import vsapp.model.User

interface UserService {
    fun login(user: String, password: String): User?
    fun getUser(id: Long): User?
}