package vsapp.model.dtos.mapping

import io.mockk.mockk
import vsapp.model.*
import kotlin.test.Test
import vsapp.model.dtos.*
import kotlin.test.assertEquals

class ChallengeMapperTest {

    private val challengerMapper = ChallengeMapper()

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

        val challenge = Challenge(
            1L,
            "fafafa",
            "body",
            listOf("1","2","3"),
            5,
            listOf(Member(1L,"fafafa", Gender.Male, 5, 1L))
        )
        val expectedDTO = ChallengeDTO(
            1L,
            "fafafa",
            "body",
            listOf("1","2","3"),
            5,
            listOf(MemberSimplifiedDTO(1L,"fafafa"))
        )

        val result = challengerMapper.challengeToDTO(challenge)

        assertEquals(expectedDTO, result)
    }

    @Test
    fun `test resultFromDTO method`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `test pointsToDTO method`() {
        TODO("Not yet implemented")
    }

}