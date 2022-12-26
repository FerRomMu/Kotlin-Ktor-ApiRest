package vsapp.controllers
import com.auth0.jwt.JWT
import io.ktor.server.config.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import vsapp.model.dtos.UserDTO
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TokenControllerTest {

    val config = mockk<HoconApplicationConfig>()
    val propertyAudience = mockk<ApplicationConfigValue>()
    val propertySecret = mockk<ApplicationConfigValue>()
    val propertyissuer = mockk<ApplicationConfigValue>()
    lateinit var tokenController: TokenController
    val user = UserDTO(1, "user", listOf(), "user@test.com", null)

    @BeforeTest
    fun setup() {

        every { config.property("jwt.audience") } returns propertyAudience
        every { config.property("jwt.secret") } returns propertySecret
        every { config.property("jwt.issuer") } returns propertyissuer

        every { propertyAudience.getString() } returns "myaudience"
        every { propertySecret.getString() } returns "mysecret"
        every { propertyissuer.getString() } returns "myissuer"

        tokenController = TokenController(config)
    }

    @Test
    fun `Should generate a valid JWT token`() {
        val token = tokenController.generateJWTToken(user)

        val decodedToken = JWT.decode(token)
        assertEquals(decodedToken.audience, listOf("myaudience"))
        assertEquals(decodedToken.issuer, "myissuer")
        assertTrue(decodedToken.expiresAt.after(Date(System.currentTimeMillis() + 10000000)))
        assertEquals(decodedToken.getClaim("user").asString(), user.user)
        assertEquals(decodedToken.getClaim("userId").asLong(), user.id)
    }

    @Test
    fun `Should verify a JWT token`() {
        val token = tokenController.generateJWTToken(user)
        val verifier = tokenController.verifyJWTToken()

        val claims = verifier.verify(token).claims
        assertEquals(claims["user"]!!.asString(), user.user)
        assertEquals(claims["userId"]!!.asLong(), user.id)
    }
}