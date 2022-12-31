package vsapp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import vsapp.exceptions.ForbiddenMemberException
import vsapp.model.Member
import vsapp.repository.MemberDAO
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MemberServiceTest {
    private val memberDao = mockk<MemberDAO>()
    private val memberMocked = mockk<Member>()
    private val memberService = MemberServiceImpl(memberDao)

    @Test
    fun `getMember returns null if member does not exist`() {
        //Setup
        every { memberDao.memberById(1L) } returns null
        val memberId = 1L
        val userId = 2L

        //Excersice
        val member = memberService.getMember(memberId, userId)

        //Verify
        assertNull(member)
        verify(exactly = 1) { memberDao.memberById(1L) }
    }

    @Test(expected = ForbiddenMemberException::class)
    fun `getMember throws ForbiddenMemberException if member exists but owner is different`() {
        //Setup
        val memberId = 1L
        val userId = 2L
        val memberOwnerId = 3L
        every { memberDao.memberById(memberId) } returns memberMocked
        every { memberMocked.userId } returns memberOwnerId

        //Exercise
        memberService.getMember(memberId, userId)
    }

    @Test
    fun `getMember returns member if exists and owner is the given user`() {
        //Setup
        val memberId = 1L
        val userId = 2L
        every { memberDao.memberById(memberId) } returns memberMocked
        every { memberMocked.userId } returns 2L

        //Exercise
        val member = memberService.getMember(memberId, userId)

        //Verify
        assertEquals(memberMocked, member)
        verify(exactly = 1) { memberDao.memberById(1L) }
    }
}