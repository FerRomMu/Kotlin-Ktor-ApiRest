package vsapp.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vsapp.controllers.UserController
import vsapp.exceptions.WrongLoginException
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.LoginUserDTO

fun Route.userRoute() {
    val userController = UserController()
    route("/user") {
        post("/login") {
            try {
                val login = userController.login(call.receive<LoginUserDTO>())
                call.response.headers.append("Authentication", "ToDO")
                call.respond(login)
            } catch(e: BadRequestException) {
                call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Body needs to have a user and a password."))
            } catch(e: WrongLoginException) {
                call.respond(ErrorDTO(e.message))
            }
        }
    }
}