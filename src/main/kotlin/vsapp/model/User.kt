package vsapp.model

import vsapp.model.dtos.PartyDTO

class User(
    var id: Long?,
    val user: String,
    val partiesIds: List<Long>,
    val email: String,
    val lastParty: Party?
)