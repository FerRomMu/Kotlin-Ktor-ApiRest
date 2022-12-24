package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class RelationDTO(
    val id1: Long,
    val id2: Long
)
