package vsapp.controllers

import vsapp.model.dtos.MemberDTO
import vsapp.model.dtos.mapping.MemberMapper
import vsapp.service.MemberService

class MemberController (private val mapper: MemberMapper,
                        private val service: MemberService) {

    /**
     * Gets member with given id if possible.
     * params: member's id and his user id.
     * returns: A memberDTO if member exist, null instead.
     */
    fun getMember(id: Long, userId: Long): MemberDTO? {
        return mapper.toDTO(service.getMember(id, userId))
    }

}