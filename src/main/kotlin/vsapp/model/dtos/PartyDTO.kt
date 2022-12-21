package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class PartyDTO(
    val order: List<String>,
    val family: List<List<String>>,
    val relations: List<Pair<String,String>>
)