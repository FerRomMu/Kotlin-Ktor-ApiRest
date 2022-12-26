package vsapp.service

import vsapp.model.Party

interface PartyService {

    /**
     * Returns a party if exists and his owner has the given user id.
     * params: id is the id of the party and userId the id of the user that has that party.
     * returns: Party if exists, null otherwise.
     * throws: ForbiddenPartyException if the party owner it's not the user of the given id.
     */
    fun getParty(id: Long, userId: Long): Party?

    /**
     * Creates the given party.
     * params: A party to be created.
     * returns: The created party.
     * throws: MalformedPartyException if the party to be created it's invalid.
     */
    fun createParty(party: Party): Party

    /**
     * Edits the given party.
     * params: A party to be edited.
     * requires: Given party has to have an id and a userId.
     * returns: The edited party or null if the party does not exist.
     * throws:
     *      - MalformedPartyException if the party to be created it's invalid.
     *      - ForbiddenPartyException if the party owner it's not the user of the given id.
     */
    fun editParty(party: Party): Party?

    /**
     * Deletes the given party if exists.
     * params: A party to be deleted.
     * returns: True if it was deleted or false if party does not exist.
     * throws:
     *      - ForbiddenPartyException if the party owner it's not the user of the given id.
     */
    fun deleteParty(id: Long, userId: Long): Boolean
}