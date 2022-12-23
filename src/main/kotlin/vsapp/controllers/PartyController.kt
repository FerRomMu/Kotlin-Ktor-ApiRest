package vsapp.controllers

import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.service.PartyService

class PartyController(private val mapper: PartyMapper,
                      private val service: PartyService) {

    fun getParty(id: Long, userId: Long): PartyDTO? {
        return mapper.toDTO(service.getParty(id, userId))
    }

    fun createParty(partyDTO: PartyDTO, userId: Long): Pair<Long,PartyDTO> {
        val party = mapper.fromDTO(partyDTO)
        party.userId = userId
        val partyMade = service.createParty(party)
        return Pair(partyMade.id!!, mapper.toDTO(partyMade)!!)
    }

    fun editParty(partyDTO: PartyDTO, id: Long, userId: Long): PartyDTO? {
        val party = mapper.fromDTO(partyDTO)
        party.userId = userId
        party.id = id
        return mapper.toDTO(service.editParty(party))
    }

    fun delete(partyDTO: PartyDTO, userId: Long) {
        TODO()
    }
}