package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class CategoryDTO(
    val id: Long,
    val name: String,
    val description: String
)