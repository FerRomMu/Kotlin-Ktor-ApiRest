package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class ChallengeRequestDTO (val category: CategoryDTO,
                                              val memberId: Long,
                                              val partyId: Long)