package vsapp.service

import vsapp.exceptions.ForbiddenPartyException
import vsapp.exceptions.MalformedPartyException
import vsapp.model.Party
import vsapp.repository.PartyDAO

class PartyServiceImpl(private val partyDao: PartyDAO): PartyService {

    override fun getParty(id: Long, userId: Long): Party? {
        val party = partyDao.partyById(id) ?: return null
        if (party.userId != userId) { throw ForbiddenPartyException() }
        return party
    }

    override fun createParty(party: Party): Party {
        if (!party.isValid()) { throw MalformedPartyException() }
        return partyDao.createParty(party)
    }

    override fun editParty(party: Party): Party? {
        if (!party.isValid()) { throw MalformedPartyException()}
        val partyToEdit = partyDao.partyById(party.id!!) ?: return null
        if (partyToEdit.userId != party.userId) { throw ForbiddenPartyException() }
        return partyDao.editParty(party)
    }

    override fun deleteParty(id: Long, userId: Long): Boolean {
        val party = partyDao.partyById(id) ?: return false
        if (userId != party.userId) { throw ForbiddenPartyException() }
        partyDao.deleteParty(id)
        return true
    }
}