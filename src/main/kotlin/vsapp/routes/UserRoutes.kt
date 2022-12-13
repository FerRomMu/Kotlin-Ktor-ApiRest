package vsapp.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute() {
    route("/user") {
        post("/login") { call.respondText("Te logueaste") }
    }
}