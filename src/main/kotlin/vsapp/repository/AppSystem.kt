package vsapp.repository

import vsapp.model.Party
import vsapp.model.User
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO
import vsapp.service.UserService

/**
 * Fake data to test
 */
object AppSystem {

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

    val idOf: MutableMap<Pair<String, String>, Long> = mutableMapOf(
        Pair("a", "a") to 0L,
        Pair("b", "b") to 1L,
        Pair("c", "c") to 2L
    )

    val unaParty = Party(0L, 0L, listOf("fafafa","fefefe"),listOf(), listOf())

    val usersById: MutableMap<Long, User> = mutableMapOf(
        0L to User(0,"a",listOf<Long>(),"a@a", unaParty),
        1L to User(1,"b",listOf<Long>(),"@bb", null),
        2L to User(2,"c",listOf<Long>(),"c@c", null)
    )

    val partyById: MutableMap<Long, Party> = mutableMapOf(
        0L to unaParty
    )

    fun register(user: String, password: String, email: String): User? {
        if (usersById.any { it.value.user == user || it.value.email == email }) {
            return null
        }
        val newUser = User(getId(), user, mutableListOf(), email, null)
        idOf[Pair(user,password)] = newUser.id
        usersById[newUser.id] = newUser
        return newUser
    }

    fun createParty(party: Party): Party {
        partyById[getPartyId()] = party
        return party
    }

    fun editParty(party: Party): Party {
        partyById[party.id!!] = party
        return party
    }

    fun deleteUser(id: Long) {}
    fun deleteParty(party: Party) {}
}