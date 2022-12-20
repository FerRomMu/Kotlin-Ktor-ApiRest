package vsapp.service

import vsapp.model.User
import vsapp.repository.AppSystem

class UserServiceImpl: UserService {

    override fun login(user: String, password: String): User? {
        return AppSystem.usersById[AppSystem.idOf[Pair(user,password)]]
    }


    override fun getUser(id: Long): User? {
        return AppSystem.usersById[id]
    }

    override fun signIn(user: String, password: String, email: String): User? {
        return AppSystem.register(user,password,email)
    }
}