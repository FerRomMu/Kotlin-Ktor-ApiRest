package vsapp.model.dtos.mapping

import io.mockk.every
import io.mockk.mockk
import vsapp.model.Gender
import vsapp.model.Member
import vsapp.model.Party
import vsapp.model.dtos.MemberSimplifiedDTO
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.RelationDTO
import kotlin.test.Test
import kotlin.test.assertEquals

class PartyMapperTest {
    private val memberMapper = mockk<MemberMapper>()
    private val partyMapper = PartyMapper(memberMapper)

    @Test
    fun testToDTO() {
        // Setup
        val party = Party(
            id = 1L,
            userId = 1L,
            order = listOf(
                Member(id = 1, name = "Member 1", Gender.Male, 0, null),
                Member(id = 2, name = "Member 2", Gender.Female, 0, null)
            ),
            family = listOf(listOf(1,2)),
            relations = listOf(
                Pair(1, 2)
            )
        )
        every { memberMapper.toSimplifiedDTO(any()) } returns MemberSimplifiedDTO(0L, "mocked")

        //Exercise
        val partyDTO = partyMapper.toDTO(party)

        //Verify
        assertEquals(2, partyDTO!!.order.size)
        assertEquals(1, partyDTO.family.size)
        assertEquals(1, partyDTO.relations.size)
    }

    @Test
    fun testFromDTO() {
        //Setup
        val partyDTO = PartyDTO(
            order = listOf(
                MemberSimplifiedDTO(id = 1, name = "Member 1"),
                MemberSimplifiedDTO(id = 2, name = "Member 2")
            ),
            family = listOf(),
            relations = listOf(
                RelationDTO(id1 = 1, id2 = 2),
                RelationDTO(id1 = 2, id2 = 1)
            )
        )
        every { memberMapper.fromSimplifiedDTO(any()) } returns Member(0L, "mocked", null, null, null)

        //Exercise
        val party = partyMapper.fromDTO(partyDTO)

        //Verify
        assertEquals(2, party.order.size)
        assertEquals(0, party.family.size)
        assertEquals(2, party.relations.size)
    }
}