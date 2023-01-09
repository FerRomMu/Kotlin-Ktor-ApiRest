package vsapp.controllers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.ChallengeResult
import vsapp.model.Member
import vsapp.model.dtos.*
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
        //Setup
        val categoryList = listOf(mockCategory1, mockCategory2)
        every { mockService.getCategories() } returns categoryList
        every { mockMapper.categoriesToDTO(eq(categoryList)) } returns mockCategoriesDTO

        //Exercise
        val result = controller.getCategories()

        //Verify
        assertEquals(result, mockCategoriesDTO)
        verify(exactly = 1) { mockService.getCategories() }
    }

    @Test
    fun `getChallenge llama a getChallenge del service y devuelve un challenge`() {
        //Setup
        every { mockService.getChallenge(eq(mockCategory1), eq(1L), eq(1L)) } returns mockChallenge
        every { mockRequestDTO.category } returns mockCategoryDTO
        every { mockRequestDTO.memberId } returns 1L
        every { mockRequestDTO.partyId } returns 1L
        every { mockMapper.categoryFromDTO(eq(mockCategoryDTO)) } returns mockCategory1
        every { mockMapper.challengeToDTO(eq(mockChallenge)) } returns mockChallengeDTO

        //Exercise
        val result = controller.getChallenge(mockRequestDTO, 1L)

        //Verify
        assertEquals(result, mockChallengeDTO)
        verify(exactly = 1) { mockService.getChallenge(any(), any(), any()) }
    }

    @Test
    fun `getChallenge llama a getChallenge del service y devuelve null si no hay dicho miembro para ese usuario`() {
        //Setup
        every { mockService.getChallenge(eq(mockCategory1), eq(1L), eq(1L)) } returns null
        every { mockRequestDTO.category } returns mockCategoryDTO
        every { mockRequestDTO.memberId } returns 1L
        every { mockRequestDTO.partyId } returns 1L
        every { mockMapper.categoryFromDTO(eq(mockCategoryDTO)) } returns mockCategory1
        every { mockMapper.challengeToDTO(isNull()) } returns null

        //Exercise
        val result = controller.getChallenge(mockRequestDTO, 1L)

        //Verify
        assertNull(result)
        verify(exactly = 1) { mockService.getChallenge(any(), any(), any()) }
    }

    @Test
    fun `saveResult llama a saveResult del service y devuelve un PointDTO`(){

        //Setup
        val member1 = mockk<Member>()
        val member2 = mockk<Member>()
        val member3 = mockk<Member>()

        val mockResultDTO = mockk<ChallengeResultDTO>()
        val mockResult = mockk<ChallengeResult>()

        val allMembers = listOf(member1, member2, member3)
        val mockPointsDTO = mockk<PointsDTO>()

        every { mockService.saveResult(eq(mockResult), eq(1L)) } returns allMembers
        every { mockMapper.resultFromDTO(eq(mockResultDTO)) } returns mockResult
        every { mockMapper.toPointsDTO(eq(allMembers), eq(1L)) } returns mockPointsDTO
        every { mockResult.partyId } returns 1L

        //Exercise
        val result = controller.saveResult(mockResultDTO, 1L)

        //Verify
        assertEquals(result, mockPointsDTO)
        verify(exactly = 1) { mockService.saveResult(mockResult, 1L) }
    }
}