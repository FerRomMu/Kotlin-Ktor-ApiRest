package vsapp.repository

import vsapp.model.Party

interface PartyDAO {
    /**
     * Returns party with the given Id.
     */
    fun partyById(id: Long): Party?

    /**
     * creates a party.
     */
    fun createParty(party: Party): Party

    /**
     * edits a party.
     */
    fun editParty(party: Party): Party

    /**
     * deletes a party.
     */
    fun deleteParty(id: Long)
}