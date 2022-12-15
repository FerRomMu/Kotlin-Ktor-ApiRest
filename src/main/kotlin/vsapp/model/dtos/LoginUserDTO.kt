package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class LoginUserDTO(val user: String, val password: String)
