package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class PointsDTO(val partyId: Long,
                     val record: Int,
                     val members: List<MemberDTO>)
