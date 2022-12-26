package vsapp.service

import vsapp.model.dtos.PasswordUserDTO
import vsapp.model.User
import vsapp.repository.UserDAO

class UserServiceImpl(private val userDao: UserDAO): UserService {

    override fun login(user: String, password: String): User? {
        val hash = userDao.getHash(user)
        return if (hash != null && password == hash) { userDao.userByUsername(user) } else { null }
    }

    override fun getUser(id: Long): User? {
        return userDao.userById(id)
    }

    override fun signUp(user: String, password: String, email: String): User? {
        val userMade = userDao.createUser(User(null,user,listOf(),email,null))
        userDao.editPasswordUser(PasswordUserDTO(userMade, password))
        return userMade
    }

    override fun deleteUser(id: Long) {
        userDao.deleteUser(id)
    }
}