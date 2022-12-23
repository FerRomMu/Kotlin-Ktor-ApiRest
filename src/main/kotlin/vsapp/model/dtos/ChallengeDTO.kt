package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class ChallengeDTO(
    val id: Long,
    val title: String,
    val body: String,
    val options: List<String>,
    val points: Int,
    val others: List<MemberSimplifiedDTO>
)