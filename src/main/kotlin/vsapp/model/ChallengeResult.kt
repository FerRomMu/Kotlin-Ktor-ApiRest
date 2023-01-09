package vsapp.model

import vsapp.model.dtos.MemberSimplifiedDTO

class ChallengeResult (val id: Long,
              val partyId: Long,
              val option: String?,
              val points: Int,
              val accepted: List<MemberSimplifiedDTO>,
              val rejected: List<MemberSimplifiedDTO>)