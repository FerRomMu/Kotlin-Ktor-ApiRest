package vsapp.model.dtos.mapping;

import vsapp.model.User
import vsapp.model.dtos.UserDTO;

class UserMapper {

    fun fromDTO(dto: UserDTO?): User? {
        TODO("")
    }

    fun toDTO(user: User?): UserDTO? {
        return if(user != null) { UserDTO(user.id, user.user, user.partiesIds, user.email, user.lastParty) } else { null }
    }
}