package vsapp.model.dtos.mapping

import vsapp.model.Gender
import vsapp.model.Member
import vsapp.model.dtos.MemberDTO
import vsapp.model.dtos.MemberSimplifiedDTO

class MemberMapper {

    fun toDTO(member: Member): MemberDTO {
        return MemberDTO(member.id, member.name, member.gender.toString(), member.points)
    }

    fun fromDTO(memberDTO: MemberDTO): Member {
        val gender = Gender.values().firstOrNull { it.name == memberDTO.gender } ?: Gender.Other
        return Member(memberDTO.id, memberDTO.name, gender, memberDTO.points)
    }

    fun toSimplifiedDTO(member: Member): MemberSimplifiedDTO {
        return MemberSimplifiedDTO(member.id!!, member.name)
    }

    fun fromSimplifiedDTO(memberSimplifiedDTO: MemberSimplifiedDTO): Member {
        return Member(memberSimplifiedDTO.id, memberSimplifiedDTO.name, null, null)
    }
}