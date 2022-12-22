package vsapp.controllers

import vsapp.model.Party
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.service.PartyService

class PartyController(private val mapper: PartyMapper,
                      private val service: PartyService) {

    fun getParty(id: Long, userId: Long): PartyDTO {
        return mapper.toDTO(service.getParty(id,userId))
    }

    fun createParty(partyDTO: PartyDTO, userId: Long): Pair<Long,PartyDTO> {
        var party: Party = mapper.fromDTO(partyDTO)
        party.userId = userId
        party = service.createParty(party)
        return Pair(party.id!!, mapper.toDTO(party))
    }

    fun editParty(partyDTO: PartyDTO, userId: Long): PartyDTO {
        val party = mapper.fromDTO(partyDTO)
        party.userId = userId
        return mapper.toDTO(service.editParty(party))
    }
}