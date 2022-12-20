package vsapp.model

import vsapp.model.dtos.PartyDTO

class User(
    val id: Long,
    val user: String,
    val partiesIds: List<Long>,
    val email: String,
    val lastParty: PartyDTO?
)