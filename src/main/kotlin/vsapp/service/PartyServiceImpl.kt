package vsapp.service

import vsapp.exceptions.ForbiddenPartyException
import vsapp.exceptions.NotFoundPartyException
import vsapp.model.Party
import vsapp.repository.AppSystem

class PartyServiceImpl: PartyService {

    override fun getParty(id: Long, userId: Long): Party? {
        val party = AppSystem.partyById[id]
        if (party != null && party.userId != userId) { throw ForbiddenPartyException("Party with given id is forbidden for this user.") }
        return party
    }

    override fun createParty(party: Party): Party? {
        //TODO checkValidParty()
        return AppSystem.createParty(party)
    }

    override fun editParty(party: Party): Party? {
        TODO("Not yet implemented")
    }

    override fun deleteParty(party: Party): Party? {
        TODO("Not yet")
    }
}