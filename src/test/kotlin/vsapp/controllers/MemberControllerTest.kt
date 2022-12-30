package vsapp.controllers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import vsapp.model.Member
import vsapp.model.dtos.MemberDTO
import vsapp.model.dtos.mapping.MemberMapper
import vsapp.service.MemberService
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class MemberControllerTest {

    private val controller = MemberController()
    private val aMember = mockk<Member>()
    private val aMemberDTO = mockk<MemberDTO>()
    private val mockService = mockk<MemberService>()
    private val mockMapper = mockk<MemberMapper>()

    @BeforeTest
    fun setup() {

        every { mockMapper.toDTO(eq(aMember)) } returns(aMemberDTO)
        every { mockMapper.fromDTO(eq(aMemberDTO)) } returns(aMember)

    }

    @Test
    fun `getMember llama al getMember del service y me devuelve un Member`() {
        every { mockService.getMember(eq(0L), any()) } returns aMember

        val result = controller.getMember(0L, 0L)

        assertEquals(result, aMemberDTO)
        verify(exactly = 1) { mockService.getMember(0L, 0L) }
    }

    @Test
    fun `getMember llama al getMember del service y si no esta devuelve null`() {
        every { mockService.getMember(neq(0L), any()) } returns null

        val result = controller.getMember(1L, 0L)

        assertEquals(result, null)
        verify(exactly = 1) { mockService.getMember(1L, 0L) }
    }
}