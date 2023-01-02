package vsapp.repository.mockDb

import vsapp.model.Member
import vsapp.repository.MemberDAO

class MockMemberDAO(): MemberDAO {
    val db = MockTables

    override fun memberById(id: Long): Member? {
        return db.memberTable[id]
    }
}