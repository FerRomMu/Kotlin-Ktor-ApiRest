package vsapp.routes

import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vsapp.controllers.TokenController
import vsapp.controllers.UserController
import vsapp.exceptions.WrongLoginException
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.LoginUserDTO
import vsapp.service.data.AppSystem

/**
 * Configure all routes that corresponds to /user.
 */
fun Route.userRoute() {

    val userController = UserController(AppSystem)
    val tokenController = TokenController(HoconApplicationConfig(ConfigFactory.load()))

    route("/user") {
        post("/login") {
            try {
                val login = userController.login(call.receive<LoginUserDTO>())
                call.response.headers.append("Authentication", tokenController.generateJWTToken(login))
                call.respond(login)
            } catch(e: BadRequestException) {
                call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Body needs to have a user and a password."))
            } catch(e: WrongLoginException) {
                call.respond(ErrorDTO(e.message))
            }
        }
        authenticate {
            get("/self") {
                val principal = call.principal<JWTPrincipal>()
                val userId = principal!!.payload.getClaim("userId").asLong()

                call.respond(AppSystem.usersById[userId]!!)
            }
        }
    }
}