package vsapp.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import vsapp.plugins.configureRouting
import kotlin.test.assertEquals

class UserRoutesTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/login").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Te logueaste", bodyAsText())
        }
    }
}