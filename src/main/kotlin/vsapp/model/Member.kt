package vsapp.model

class Member(
    var id: Long?,
    val name: String,
    val gender: Gender?,
    var points: Int?
) {
}