package vsapp.model

class Party (
    var id: Long?,
    var userId: Long?,
    val order: List<Member>,
    val family: List<List<Long>>,
    val relations: List<Pair<Long,Long>>
    ) {
    fun isValid(): Boolean { return true }
}