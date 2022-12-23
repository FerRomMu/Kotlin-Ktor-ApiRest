package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class CategoriesDTO(
    val categories: List<CategoryDTO>
)