package vsapp.repository.mockDb

import vsapp.model.Member
import vsapp.repository.MemberDAO

class MockMemberDAO(): MemberDAO {
    override fun memberById(id: Long): Member? {
        TODO("Not yet implemented")
    }
}