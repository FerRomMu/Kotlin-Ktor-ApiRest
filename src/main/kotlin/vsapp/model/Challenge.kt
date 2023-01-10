package vsapp.model

import vsapp.model.dtos.MemberSimplifiedDTO

class Challenge(
    val id: Long,
    val title: String,
    val body: String,
    val options: List<String>,
    val points: Int,
    val others: List<Member>
) {
}