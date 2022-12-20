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
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.LoginUserDTO
import vsapp.model.dtos.SignInDTO

/**
 * Configure all routes that corresponds to /user.
 */
fun Route.userRoute() {

    val userController = UserController()
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

                call.respond(userController.getUser(userId)!!)
            }
        }
        post("/register") {
            try {
                val signedUser = userController.signIn(call.receive<SignInDTO>())
                if(signedUser == null){
                    call.respond(HttpStatusCode(409, "Conflict"), ErrorDTO("Usuario o email en uso."))
                }
                call.response.headers.append("Authentication", tokenController.generateJWTToken(signedUser!!))
                call.respond(signedUser)
            } catch(e: BadRequestException) {
                call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Body needs to have a user, a password and a email."))
            }
        }
    }
}