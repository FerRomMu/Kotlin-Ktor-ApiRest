package vsapp.service.data

import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO

object AppSystem {

    val users = mapOf(
        Pair("a", "a") to UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda")),
        Pair("b", "b") to UserDTO(0,"b",listOf<Long>(),"@bb", PartyDTO("holanda")),
        Pair("c", "c") to UserDTO(0,"c",listOf<Long>(),"c@c", PartyDTO("holanda"))
    )
}