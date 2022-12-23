package vsapp.service

import vsapp.exceptions.ForbiddenPartyException
import vsapp.exceptions.MalformedPartyException
import vsapp.model.Party
import vsapp.repository.AppSystem

class PartyServiceImpl: PartyService {

    override fun getParty(id: Long, userId: Long): Party? {
        val party = AppSystem.partyById[id] ?: return null
        if (party.userId != userId) { throw ForbiddenPartyException() }
        return party
    }

    override fun createParty(party: Party): Party {
        if (!party.isValid()) { throw MalformedPartyException() }
        return AppSystem.createParty(party)
    }

    override fun editParty(party: Party): Party? {
        if (!party.isValid()) { throw MalformedPartyException()}
        val partyToEdit = AppSystem.partyById[party.id!!] ?: return null
        if (partyToEdit.userId != party.userId) { throw ForbiddenPartyException() }
        return AppSystem.editParty(party)
    }

    override fun deleteParty(id: Long, userId: Long): Boolean {
        val party = AppSystem.partyById[id] ?: return false
        if (userId != party.userId) { throw ForbiddenPartyException() }
        AppSystem.deleteParty(party)
        return true
    }
}