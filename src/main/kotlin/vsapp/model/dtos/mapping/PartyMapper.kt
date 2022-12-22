package vsapp.model.dtos.mapping

import vsapp.model.Party
import vsapp.model.dtos.PartyDTO

class PartyMapper {
    fun toDTO(party: Party?): PartyDTO? {
        return if(party != null) { PartyDTO(party.order, party.family, party.relations) } else { null }
    }

    fun fromDTO(partyDTO: PartyDTO): Party {
        return Party(null,null,partyDTO.order,partyDTO.family,partyDTO.relations)
    }
}