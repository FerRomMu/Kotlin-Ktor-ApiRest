package vsapp.service

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import vsapp.exceptions.ForbiddenPartyException
import vsapp.exceptions.MalformedPartyException
import vsapp.model.Party
import vsapp.repository.PartyDAO
import kotlin.test.*

class PartyServiceTest {

    private val partyDao = mockk<PartyDAO>()
    private val partyMocked = mockk<Party>()
    private val partyService = PartyServiceImpl(partyDao)

    @Test
    fun `getParty returns null if party does not exist`() {
        //Setup
        every { partyDao.partyById(1L) } returns null
        val partyId = 1L
        val userId = 2L

        //Excersice
        val party = partyService.getParty(partyId, userId)

        //Verify
        assertNull(party)
        verify(exactly = 1) { partyDao.partyById(1L) }
    }

    @Test(expected = ForbiddenPartyException::class)
    fun `getParty throws ForbiddenPartyException if party exists but owner is different`() {
        //Setup
        val partyId = 1L
        val userId = 2L
        val partyOwnerId = 3L
        every { partyDao.partyById(partyId) } returns partyMocked
        every { partyMocked.userId } returns partyOwnerId

        //Exercise
        partyService.getParty(partyId, userId)
    }

    @Test
    fun `getParty returns party if exists and owner is the given user`() {
        //Setup
        val partyId = 1L
        val userId = 2L
        every { partyDao.partyById(partyId) } returns partyMocked
        every { partyMocked.userId } returns 2L

        //Exercise
        val party = partyService.getParty(partyId, userId)

        //Verify
        assertEquals(partyMocked, party)
        verify(exactly = 1) { partyDao.partyById(1L) }
    }

    @Test
    fun `createParty returns created party`() {
        //Setup
        val party = partyMocked
        every { partyDao.createParty(party) } returns party
        every { party.isValid() } returns true

        //Exercise
        val createdParty = partyService.createParty(party)

        //Verify
        assertEquals(party, createdParty)
        verify(exactly = 1) { partyDao.createParty(party) }
    }

    @Test(expected = MalformedPartyException::class)
    fun `createParty throws MalformedPartyException if party is invalid`() {
        //Setup
        val party = partyMocked
        every { partyDao.createParty(party) } returns party
        every { party.isValid() } returns false

        //Exercise
        partyService.createParty(party)
    }

    @Test
    fun `editParty returns null if party does not exist`() {
        //Setup
        every { partyDao.partyById(1L) } returns null
        every { partyMocked.id } returns 1L
        every { partyMocked.isValid() } returns true
        every { partyMocked.userId } returns 1L

        //Exercise
        val editedParty = partyService.editParty(partyMocked)

        //Verify
        assertTrue(editedParty == null)
        verify(exactly = 0) { partyDao.editParty(any()) }
    }

    @Test(expected = MalformedPartyException::class)
    fun `editParty throws MalformedPartyException if party is invalid`() {
        //Setup
        every { partyMocked.isValid() } returns false
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L

        //Exercise
        partyService.editParty(partyMocked)
    }

    @Test(expected = ForbiddenPartyException::class)
    fun `editParty throws ForbiddenPartyException if party exists but owner is different`() {
        //Setup
        every { partyMocked.isValid() } returns true
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L
        val partyOwnerId = 2L
        every { partyDao.partyById(1L) } returns Party(partyMocked.id, partyOwnerId, listOf(), listOf(), listOf())

        //Exercise
        partyService.editParty(partyMocked)
    }

    @Test
    fun `editParty returns edited party if party exists and owner is the given user`() {
        //Setup
        every { partyDao.editParty(partyMocked) } returns partyMocked
        every { partyDao.partyById(1L) } returns partyMocked
        every { partyMocked.isValid() } returns true
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L

        //Exercise
        val editedParty = partyService.editParty(partyMocked)

        //Verify
        assertEquals(editedParty, partyMocked)
        verify(exactly = 1) { partyDao.editParty(partyMocked) }
    }

    @Test(expected = ForbiddenPartyException::class)
    fun `deleteParty throws ForbiddenPartyException if party exists but owner is different`() {
        //Setup
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L
        val partyOwnerId = 2L
        every { partyDao.partyById(1L) } returns Party(partyMocked.id, partyOwnerId, listOf(), listOf(), listOf())

        //Exercise
        partyService.deleteParty(partyMocked.id!!, 1L)
    }

    @Test
    fun `deleteParty returns false if party does not exist`() {
        //Setup
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L
        every { partyDao.partyById(1L) } returns null

        //Exercise
        val deleted = partyService.deleteParty(partyMocked.id!!, 1L)

        //Verify
        assertFalse(deleted)
        verify(exactly = 0) { partyDao.deleteParty(any()) }
    }

    @Test
    fun `deleteParty returns true if party exists and owner is the given user`() {
        //Setup
        every { partyMocked.id } returns 1L
        every { partyMocked.userId } returns 1L
        every { partyDao.partyById(1L) } returns partyMocked
        justRun { partyDao.deleteParty(1L) }

        //Exercise
        val bool = partyService.deleteParty(partyMocked.id!!, 1L)

        //Verify
        assertTrue(bool)
        verify(exactly = 1) { partyDao.deleteParty(1L) }
    }
}