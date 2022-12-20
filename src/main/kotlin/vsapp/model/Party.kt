package vsapp.model

class Party (
    val id: Long,
    var userId: Long?,
    val order: List<Any>,
    val family: List<List<Any>>,
    val relations: List<Pair<Any,Any>>
    )