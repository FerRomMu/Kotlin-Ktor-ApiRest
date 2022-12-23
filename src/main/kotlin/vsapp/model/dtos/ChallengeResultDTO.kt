package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class ChallengeResultDTO(
    val id: Long,
    val partyId: Long,
    val option: String?,
    val points: Int,
    val accepted: List<MemberSimplifiedDTO>,
    val rejected: List<MemberSimplifiedDTO>
)