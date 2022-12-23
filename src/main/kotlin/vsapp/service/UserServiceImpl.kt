package vsapp.service

import com.auth0.jwt.algorithms.Algorithm
import vsapp.model.PasswordUser
import vsapp.model.User
import vsapp.repository.AppSystem
import vsapp.repository.UserDAO

class UserServiceImpl(private val userDao: UserDAO): UserService {

    override fun login(user: String, password: String): User? {
        val hash = userDao.getHash(user)
        return if (password == hash) { userDao.userByUsername(user) } else { null }
    }

    override fun getUser(id: Long): User? {
        return userDao.userById(id)
    }

    override fun signUp(user: String, password: String, email: String): User? {
        val user = userDao.createUser(User(null,user,listOf(),email,null))
        userDao.editPasswordUser(PasswordUser(user, password))
        return user
    }

    override fun deleteUser(id: Long) {
        AppSystem.deleteUser(id)
    }
}