package vsapp.model

data class UserDTO(
    val id: Long,
    val user: String,
    val partiesIds: List<Long>,
    val email: String,
    val lastParty: PartyDTO
    )
