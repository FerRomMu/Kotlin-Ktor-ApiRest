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
import org.koin.ktor.ext.inject
import vsapp.controllers.TokenController
import vsapp.controllers.UserController
import vsapp.exceptions.ConflictMailOrUserException
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.SignInDTO


/**
 * Configure all routes that corresponds to /user.
 */
fun Route.userRoute() {

    val userController: UserController by inject()
    val tokenController = TokenController(HoconApplicationConfig(ConfigFactory.load()))

    route("/user") {
        post("/login") {
            try {
                val login = userController.login(call.receive<LoginUserDTO>())
                if(login == null){
                    call.respond(ErrorDTO("Wrong user or password."))
                }
                call.response.headers.append("Authentication", tokenController.generateJWTToken(login!!))
                call.respond(login)
            } catch(e: BadRequestException) {
                call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Body needs to have a user and a password."))
            }
        }
        authenticate {
            get("/self") {
                val principal = call.principal<JWTPrincipal>()
                val userId = principal!!.payload.getClaim("userId").asLong()
                val user = userController.getUser(userId)
                if (user == null) {
                    call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("User not found, but it should have been."))
                }
                call.respond(user!!)
            }
        }
        post("/register") {
            try {
                val signedUser = userController.signUp(call.receive<SignInDTO>())
                call.response.headers.append("Authentication", tokenController.generateJWTToken(signedUser!!))
                call.respond(signedUser)
            } catch(e: BadRequestException) {
                call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Body needs to have a user, a password and a email."))
            } catch(e: ConflictMailOrUserException) {
                call.respond(HttpStatusCode(409, "Conflict"), ErrorDTO(e.message))
            }
        }
        delete("/delete") {
            
        }
    }
}