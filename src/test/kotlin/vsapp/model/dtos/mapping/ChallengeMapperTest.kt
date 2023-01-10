package vsapp.model.dtos.mapping

import io.mockk.every
import io.mockk.mockk
import vsapp.model.*
import kotlin.test.Test
import vsapp.model.dtos.*
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ChallengeMapperTest {

    private val mockMapper = mockk<MemberMapper>()
    private val challengerMapper = ChallengeMapper(mockMapper)

    @Test
    fun `test categoriesToDTO method`() {

        val category = Category(1L, "fafafa", "descripción")
        val category2 = Category(1L, "fefefe", "descripción")
        val list = listOf(category2, category)
        val expectedDTO = CategoriesDTO(
            listOf(
                challengerMapper.categoryToDTO(category2),
                challengerMapper.categoryToDTO(category)
            )
        )

        val result = challengerMapper.categoriesToDTO(list)

        assertEquals(expectedDTO, result)

    }

    @Test
    fun `test categoryToDTO method`() {
        val category = Category(1L, "fafafa", "descripción")
        val expectedDTO = CategoryDTO(1L, "fafafa", "descripción")

        val result = challengerMapper.categoryToDTO(category)

        assertEquals(expectedDTO, result)
    }

    @Test
    fun `test categoryFromDTO method`() {
        val categoryDTO = CategoryDTO(1L, "fafafa", "descripción")
        val expected = Category(1L, "fafafa", "descripción")

        val result = challengerMapper.categoryFromDTO(categoryDTO)

        assertEquals(expected.id, result.id)
        assertEquals(expected.name, result.name)
        assertEquals(expected.description, result.description)
    }

    @Test
    fun `test challengeToDTO method`() {

        val members = listOf(Member(1L,"fafafa", Gender.Male, 5, 1L))
        val membersDTO = listOf(MemberSimplifiedDTO(1L,"fafafa"))

        every { mockMapper.allToSimplifiedDTO(eq(members))} returns membersDTO

        val challenge = Challenge(
            1L,
            "fafafa",
            "body",
            listOf("1","2","3"),
            5,
            members
        )
        val expectedDTO = ChallengeDTO(
            1L,
            "fafafa",
            "body",
            listOf("1","2","3"),
            5,
            membersDTO
        )

        val result = challengerMapper.challengeToDTO(challenge)

        assertEquals(expectedDTO, result)
    }

    @Test
    fun `test challengeToDTO method returns null if is null`() {

        val challenge = null

        val result = challengerMapper.challengeToDTO(challenge)

        assertNull(result)
    }

    @Test
    fun `test resultFromDTO method`() {

        val memberDTO = MemberSimplifiedDTO(1L,"fafafa")
        val memberDTO2 = MemberSimplifiedDTO(2L,"fefefe")
        val member = Member(1L,"fafafa", null, null, null)
        val member2 = Member(2L,"fefefe", null, null, null)
        val resultDTO = ChallengeResultDTO(
            1L,
            1L,
            "fafafa",
            5,
            listOf(memberDTO),
            listOf(memberDTO2)
        )
        val expected = ChallengeResult(
            1L,
            1L,
            "fafafa",
            5,
            listOf(member),
            listOf(member2)
        )
        every { mockMapper.fromSimplifiedDTO(memberDTO, null) } returns member
        every { mockMapper.fromSimplifiedDTO(memberDTO2, null) } returns member2

        val result = challengerMapper.resultFromDTO(resultDTO)

        assertEquals(expected.id, result.id)
        assertEquals(expected.partyId, result.partyId)
        assertEquals(expected.option, result.option)
        assertEquals(expected.points, result.points)
        assertEquals(expected.accepted, result.accepted)
        assertEquals(expected.rejected, result.rejected)

    }

    @Test
    fun `test pointsToDTO method`() {
        TODO("Not yet implemented")
    }

}