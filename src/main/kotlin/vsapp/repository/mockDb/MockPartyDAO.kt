package vsapp.repository.mockDb

import vsapp.model.Party
import vsapp.repository.PartyDAO

class MockPartyDAO: PartyDAO {
    val db = MockTables

    override fun partyById(id: Long): Party? {
        return db.partyTable[id]
    }

    override fun createParty(party: Party): Party {
        party.id = db.getPartyId()
        db.partyTable[party.id!!] = party
        return party
    }

    override fun editParty(party: Party): Party {
        db.partyTable[party.id!!] = party
        return party
    }

    override fun deleteParty(id: Long) {
        db.partyTable.remove(id)
    }
}