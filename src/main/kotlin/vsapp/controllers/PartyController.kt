package vsapp.controllers

import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.service.PartyService

class PartyController(private val mapper: PartyMapper,
                      private val service: PartyService) {

    /**
     * Gets party with given id if possible.
     * params: party's id and his user id.
     * returns: A partyDTO if party exist, null instead.
     */
    fun getParty(id: Long, userId: Long): PartyDTO? {
        return mapper.toDTO(service.getParty(id, userId))
    }

    /**
     * Creates a party with the information of the partyDTO for the user of the given id.
     * params: A partyDTO with the information of the party to be created and the user id
     *         that creates it.
     * returns: A Pair with the id of the party created and his DTO.
     */
    fun createParty(partyDTO: PartyDTO, userId: Long): Pair<Long,PartyDTO> {
        val party = mapper.fromDTO(partyDTO)
        party.userId = userId
        val partyMade = service.createParty(party)
        return Pair(partyMade.id!!, mapper.toDTO(partyMade)!!)
    }

    /**
     * Edits a party with the given id using the information of the partyDTO.
     * params: A partyDTO with the information of the party to be edited and his id and the user id
     *         that creates it.
     * returns: A PartyDTO of the party updated or null if the party doesn't exist.
     */
    fun editParty(partyDTO: PartyDTO, id: Long, userId: Long): PartyDTO? {
        val party = mapper.fromDTO(partyDTO)
        party.userId = userId
        party.id = id
        return mapper.toDTO(service.editParty(party))
    }

    /**
     * Deletes a party with the given id.
     * params: The id of the party to be deleted and the user id of that party.
     */
    fun delete(partyDTO: PartyDTO, userId: Long) {
        TODO()
    }
}