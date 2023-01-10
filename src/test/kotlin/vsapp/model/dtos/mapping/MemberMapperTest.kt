package vsapp.model.dtos.mapping

import vsapp.model.Gender
import vsapp.model.Member
import vsapp.model.dtos.MemberDTO
import vsapp.model.dtos.MemberSimplifiedDTO
import kotlin.test.Test
import kotlin.test.assertEquals

class MemberMapperTest {
    private val memberMapper = MemberMapper()

    @Test
    fun `test toDTO method`() {
        //Setup
        val member = Member(1, "John", Gender.Male, 100, null)
        val expectedMemberDTO = MemberDTO(1, "John", "Male", 100)

        //Exercise
        val memberDTO = memberMapper.toDTO(member)

        //Verify
        assertEquals(expectedMemberDTO, memberDTO)
    }

    @Test
    fun `test fromDTO method`() {
        //Setup
        val memberDTO = MemberDTO(1, "John", "Male", 100)
        val expectedMember = Member(1, "John", Gender.Male, 100, null)

        //Exercise
        val member = memberMapper.fromDTO(memberDTO)

        //Verify
        assertEquals(expectedMember.id, member.id)
        assertEquals(expectedMember.points, member.points)
        assertEquals(expectedMember.gender, member.gender)
        assertEquals(expectedMember.name, member.name)
    }

    @Test
    fun `test toSimplifiedDTO method`() {
        //Setup
        val member = Member(1, "John", Gender.Male, 100, null)
        val expectedMemberSimplifiedDTO = MemberSimplifiedDTO(1, "John")

        //Exercise
        val memberSimplifiedDTO = memberMapper.toSimplifiedDTO(member)

        //Verify
        assertEquals(expectedMemberSimplifiedDTO, memberSimplifiedDTO)
    }

    @Test
    fun `test fromSimplifiedDTO method`() {
        //Setup
        val memberSimplifiedDTO = MemberSimplifiedDTO(1, "John")
        val expectedMember = Member(1, "John", null, null, 1L)

        //Exercise
        val member = memberMapper.fromSimplifiedDTO(memberSimplifiedDTO, 1L)

        //Verify
        assertEquals(expectedMember.id, member.id)
        assertEquals(expectedMember.points, member.points)
        assertEquals(expectedMember.gender, member.gender)
        assertEquals(expectedMember.name, member.name)
    }

    @Test
    fun `test allToSimplifiedDTO method`() {
        //Setup
        val member1 = Member(1, "John", Gender.Male, 100, null)
        val member2 = Member(2, "Fafa", Gender.Male, 100, null)

        val memberSimplifiedDTO = MemberSimplifiedDTO(1, "John")
        val memberSimplifiedDTO2 = MemberSimplifiedDTO(2, "Fafa")
        val list = listOf(member1, member2)

        //Exercise
        val result = memberMapper.allToSimplifiedDTO(list)

        //Verify
        assertEquals(result.size, 2)
        assertEquals(result[0], memberSimplifiedDTO)
        assertEquals(result[1], memberSimplifiedDTO2)
    }
}