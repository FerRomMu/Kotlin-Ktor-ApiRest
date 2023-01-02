package vsapp.model.dtos.mapping

import vsapp.model.Party
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.RelationDTO

class PartyMapper(private val memberMapper: MemberMapper) {
    fun toDTO(party: Party?): PartyDTO? {
        val members = party?.order?.map { member -> memberMapper.toSimplifiedDTO(member) }
        val relations = party?.relations?.map { (id1, id2) -> RelationDTO(id1, id2) }
        return if(party != null) { PartyDTO(members!!, party.family, relations!!) } else { null }
    }

    fun fromDTO(partyDTO: PartyDTO, userId: Long): Party {
        val members = partyDTO.order.map { member -> memberMapper.fromSimplifiedDTO(member, userId) }
        val relations = partyDTO.relations.map { relation -> Pair(relation.id1, relation.id2) }
        return Party(null,userId, members, partyDTO.family, relations)
    }
}