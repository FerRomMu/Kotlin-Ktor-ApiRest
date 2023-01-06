package vsapp.controllers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.dtos.CategoriesDTO
import vsapp.model.dtos.CategoryDTO
import vsapp.model.dtos.ChallengeDTO
import vsapp.model.dtos.ChallengeRequestDTO
import vsapp.model.dtos.mapping.ChallengeMapper
import vsapp.service.ChallengeService
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ChallengeControllerTest {

    private val mockCategory1 = mockk<Category>()
    private val mockCategory2 = mockk<Category>()
    private val mockCategoryDTO = mockk<CategoryDTO>()
    private val mockCategoriesDTO = mockk<CategoriesDTO>()

    private val mockChallenge = mockk<Challenge>()
    private val mockChallengeDTO = mockk<ChallengeDTO>()
    private val mockRequestDTO = mockk<ChallengeRequestDTO>()

    private val mockService = mockk<ChallengeService>()
    private val mockMapper = mockk<ChallengeMapper>()

    private val controller = ChallengeController(mockMapper, mockService)

    @BeforeTest
    fun setup() {

    }

    @Test
    fun `getCategories llama a getCategories del service y me devuelve un CategoriesDTO`() {
        val categoryList = listOf(mockCategory1, mockCategory2)
        every { mockService.getCategories() } returns categoryList
        every { mockMapper.categoriesToDTO(eq(categoryList)) } returns mockCategoriesDTO

        val result = controller.getCategories()

        assertEquals(result, mockCategoriesDTO)
        verify(exactly = 1) { mockService.getCategories() }
    }

    @Test
    fun `getChallenge llama a getChallenge del service y devuelve un challenge`() {
        every { mockService.getChallenge(eq(mockCategory1), eq(1L), eq(1L)) } returns mockChallenge
        every { mockRequestDTO.category } returns mockCategoryDTO
        every { mockRequestDTO.memberId } returns 1L
        every { mockRequestDTO.partyId } returns 1L
        every { mockMapper.categoryToDTO(eq(mockCategoryDTO)) } returns mockCategory1
        every { mockMapper.challengeToDTO(eq(mockChallenge)) } returns mockChallengeDTO

        val result = controller.getChallenge(mockRequestDTO, 1L)

        assertEquals(result, mockChallengeDTO)
        verify(exactly = 1) { mockService.getChallenge(any(), any(), any()) }
    }

    @Test
    fun `getChallenge llama a getChallenge del service y devuelve null si no hay dicho miembro para ese usuario`() {
        every { mockService.getChallenge(eq(mockCategory1), eq(1L), eq(1L)) } returns null
        every { mockRequestDTO.category } returns mockCategoryDTO
        every { mockRequestDTO.memberId } returns 1L
        every { mockRequestDTO.partyId } returns 1L
        every { mockMapper.categoryToDTO(eq(mockCategoryDTO)) } returns mockCategory1
        every { mockMapper.challengeToDTO(isNull()) } returns null

        val result = controller.getChallenge(mockRequestDTO, 1L)

        assertNull(result)
        verify(exactly = 1) { mockService.getChallenge(any(), any(), any()) }
    }
}