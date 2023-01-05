package vsapp.controllers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import vsapp.model.Category
import vsapp.model.Member
import vsapp.model.dtos.CategoriesDTO
import vsapp.model.dtos.MemberDTO
import vsapp.model.dtos.mapping.ChallengeMapper
import vsapp.service.ChallengeService
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class ChallengeControllerTest {

    private val mockCategory1 = mockk<Category>()
    private val mockCategory2 = mockk<Category>()
    private val mockCategoriesDTO = mockk<CategoriesDTO>()
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

}