package vsapp.service

import vsapp.model.Member

interface MemberService {
    fun getMember(id: Long, idUser: Long): Member?
}