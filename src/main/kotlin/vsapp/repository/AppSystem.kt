package vsapp.repository

import vsapp.model.User
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO
import vsapp.service.UserService

/**
 * Fake data to test
 */
object AppSystem {

    val idOf = mapOf(
        Pair("a", "a") to 0L,
        Pair("b", "b") to 1L,
        Pair("c", "c") to 2L
    )

    val usersById: Map<Long, User> = mapOf(
        0L to User(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda")),
        1L to User(1,"b",listOf<Long>(),"@bb", PartyDTO("holanda")),
        2L to User(2,"c",listOf<Long>(),"c@c", PartyDTO("holanda"))
    )

}