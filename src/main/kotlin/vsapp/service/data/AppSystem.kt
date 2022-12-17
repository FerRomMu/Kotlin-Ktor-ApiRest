package vsapp.service.data

import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO

/**
 * Fake data to test
 */
object AppSystem {

    val users = mapOf(
        Pair("a", "a") to UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda")),
        Pair("b", "b") to UserDTO(1,"b",listOf<Long>(),"@bb", PartyDTO("holanda")),
        Pair("c", "c") to UserDTO(2,"c",listOf<Long>(),"c@c", PartyDTO("holanda"))
    )

    val usersById: Map<Long, UserDTO> = mapOf(
        0L to UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda")),
        1L to UserDTO(1,"b",listOf<Long>(),"@bb", PartyDTO("holanda")),
        2L to UserDTO(2,"c",listOf<Long>(),"c@c", PartyDTO("holanda"))
    )
}