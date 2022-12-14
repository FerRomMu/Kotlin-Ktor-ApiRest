package vsapp.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.PartyDTO
import vsapp.model.dtos.UserDTO

fun Route.userRoute() {
    route("/user") {
        post("/login") { call.respond(UserDTO(0,"a",listOf<Long>(),"a@a", PartyDTO("holanda"))) }
    }
}