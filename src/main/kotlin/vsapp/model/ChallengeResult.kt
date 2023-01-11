package vsapp.model

import vsapp.model.Member

class ChallengeResult (val id: Long,
              val partyId: Long,
              val option: String?,
              val points: Int,
              val accepted: List<Member>,
              val rejected: List<Member>)