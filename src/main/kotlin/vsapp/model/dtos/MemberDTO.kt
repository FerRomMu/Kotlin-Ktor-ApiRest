package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class MemberDTO(
    val id: Long,
    val name: String,
    val points: Int
)