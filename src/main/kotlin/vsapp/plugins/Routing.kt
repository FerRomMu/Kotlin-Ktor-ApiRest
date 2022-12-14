package vsapp.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import vsapp.routes.userRoute

fun Application.configureRouting() {
    routing {
        userRoute()
    }
}