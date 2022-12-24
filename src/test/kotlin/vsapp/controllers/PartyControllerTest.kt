package vsapp.controllers

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import vsapp.model.Party
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.service.PartyService
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PartyControllerTest {

    private val mockService = mockk<PartyService>()
    private val mockMapper = mockk<PartyMapper>()
    private val partyController = PartyController(mockMapper, mockService)
    private val aParty = mockk<Party>()
    private val aPartyDTO = mockk<PartyDTO>()

    @BeforeTest
    fun setup() {

        every { mockMapper.toDTO(eq(aParty)) } returns(aPartyDTO)
        every { mockMapper.toDTO(isNull()) } returns(null)
        every { mockMapper.fromDTO(eq(aPartyDTO)) } returns(aParty)

    }

    @Test
    fun `getParty llama al getParty del service y me devuelve una party`() {
        every { mockService.getParty(eq(0L), any()) } returns aParty

        val result = partyController.getParty(0L, 0L)

        assertEquals(result, aPartyDTO)
        verify(exactly = 1) { mockService.getParty(0L, 0L)}
    }

    @Test
    fun `getParty llama al getParty del service y si no esta devuelve null`() {
        every { mockService.getParty(neq(0L), any()) } returns null

        val result = partyController.getParty(1L, 0L)

        assertEquals(result, null)
        verify(exactly = 1) { mockService.getParty(1L, 0L)}
    }

    @Test
    fun `createParty llama a create del service y devuelve un par con la id y la party`() {
        every { mockService.createParty(aParty) } returns aParty
        justRun { aParty.userId = 0L }
        every { aParty.id } returns 0L

        val result = partyController.createParty(aPartyDTO, 0L)

        assertEquals(result.first, 0L)
        assertEquals(result.second, aPartyDTO)
        verify(exactly = 1) { mockService.createParty(aParty) }
    }

    @Test
    fun `editParty llama a editParty del service y devuelve la party editada`() {
        every { mockService.editParty(aParty) } returns aParty
        justRun { aParty.userId = 0L }
        justRun { aParty.id = 0L }

        val result = partyController.editParty(aPartyDTO, 0L, 0L)

        assertEquals(result, aPartyDTO)
        verify(exactly = 1) { mockService.editParty(aParty) }
    }

    @Test
    fun `editParty llama a editParty del service y devuelve null si no habia tal party`() {
        every { mockService.editParty(aParty) } returns null
        justRun { aParty.userId = 0L }
        justRun { aParty.id = 0L }

        val result = partyController.editParty(aPartyDTO, 0L, 0L)

        assertEquals(result, null)
        verify(exactly = 1) { mockService.editParty(aParty) }
    }

    @Test
    fun `deleteParty llama a deleteParty del service y devuelve verdadero si fue borrado`() {
        every { mockService.deleteParty(eq(0L), eq(0L)) } returns true

        assertTrue { partyController.deleteParty(0L,0L) }
        verify { mockService.deleteParty(0L, 0L) }
    }

    @Test
    fun `deleteParty llama a deleteParty del service y devuelve falso si no fue borrado`() {
        every { mockService.deleteParty(eq(0L), neq(0L)) } returns false

        assertFalse { partyController.deleteParty(0L,1L) }
        verify { mockService.deleteParty(0L, 1L) }
    }
}