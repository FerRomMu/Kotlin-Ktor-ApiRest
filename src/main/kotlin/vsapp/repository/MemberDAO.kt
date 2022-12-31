package vsapp.repository

import vsapp.model.Member

interface MemberDAO {
    fun memberById(id: Long): Member?
}