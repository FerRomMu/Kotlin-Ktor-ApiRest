package vsapp.service

import vsapp.model.Party

interface PartyService {
    fun getParty(id: Long, userId: Long): Party
    fun createParty(party: Party): Party
    fun editParty(party: Party): Party
}