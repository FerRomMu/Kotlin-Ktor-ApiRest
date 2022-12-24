package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class MemberSimplifiedDTO(
    val id: Long?,
    val name: String
)