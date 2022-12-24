package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class PartyDTO(
    val order: List<MemberSimplifiedDTO>,
    val family: List<List<Long>>,
    val relations: List<RelationDTO>
)