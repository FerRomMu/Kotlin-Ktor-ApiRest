package vsapp.model

class Party (
    val id: Long?,
    var userId: Long?,
    val order: List<String>,
    val family: List<List<String>>,
    val relations: List<Pair<String,String>>
    )