package vsapp.model.dtos

import kotlinx.serialization.Serializable

@Serializable data class SignInDTO(val user: String, val password: String, val email: String)