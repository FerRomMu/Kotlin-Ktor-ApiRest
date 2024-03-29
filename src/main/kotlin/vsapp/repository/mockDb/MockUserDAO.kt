package vsapp.repository.mockDb

import vsapp.model.dtos.PasswordUserDTO
import vsapp.model.User
import vsapp.repository.UserDAO

class MockUserDAO: UserDAO {
    val db = MockTables

    override fun userById(id: Long): User? {
        return db.usersTable[id]
    }

    override fun userByUsername(user: String): User? {
        return db.usersTable.values.first { u -> u.user == user }
    }

    override fun createUser(user: User): User {
        user.id = db.nextId
        db.usersTable[user.id!!] = user
        return user
    }

    override fun editPasswordUser(passwordUser: PasswordUserDTO) {
        db.passwordsTable[passwordUser.user.user] = passwordUser.password
    }

    override fun deleteUser(id: Long) {
        db.passwordsTable.remove(db.usersTable[id]!!.user)
        db.usersTable.remove(id)
    }

    override fun getHash(user: String): String? {
        return db.passwordsTable[user]
    }

    override fun isUserInUse(user: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isMailInUse(mail: String): Boolean {
        TODO("Not yet implemented")
    }
}