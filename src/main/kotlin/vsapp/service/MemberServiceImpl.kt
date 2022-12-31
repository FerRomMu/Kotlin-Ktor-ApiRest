package vsapp.service

import vsapp.exceptions.ForbiddenMemberException
import vsapp.model.Member
import vsapp.repository.MemberDAO

class MemberServiceImpl(val dao: MemberDAO): MemberService {

    override fun getMember(id: Long, idUser: Long): Member? {
        val member = dao.memberById(id) ?: return null
        if(idUser != member.userId) { throw ForbiddenMemberException() }
        return member
    }
}