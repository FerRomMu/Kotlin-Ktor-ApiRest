package vsapp.model.dtos

import kotlinx.serialization.Serializable
import vsapp.model.dtos.PartyDTO

@Serializable
data class UserDTO(
    val id: Long,
    val user: String,
    val partiesIds: List<Long>,
    val email: String,
    val lastParty: PartyDTO
    )