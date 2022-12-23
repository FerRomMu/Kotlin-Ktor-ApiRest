package vsapp.model.dtos

import vsapp.model.User

data class PasswordUserDTO(val user: User, val password: String)