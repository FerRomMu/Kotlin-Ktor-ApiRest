package vsapp.repository.mockDb

import vsapp.model.Party
import vsapp.model.User

/**
 * Fake data to test
 */
object MockTables {

    var nextId = 3L
    var nextPartyId = 1L

    fun getId(): Long {
        nextId++
        return nextId - 1L
    }

    fun getPartyId(): Long {
        nextPartyId++
        return nextPartyId - 1L
    }

    val usersTable = mutableMapOf<Long, User>(
        0L to User(0,"a",listOf<Long>(),"a@a", null),
        1L to User(1,"b",listOf<Long>(),"@bb", null),
        2L to User(2,"c",listOf<Long>(),"c@c", null)
    )

    val partyTable = mutableMapOf<Long, Party>()

    val passwordsTable = mutableMapOf<String, String>(
        "a" to "a",
        "b" to "b",
        "c" to "c"
    )
}