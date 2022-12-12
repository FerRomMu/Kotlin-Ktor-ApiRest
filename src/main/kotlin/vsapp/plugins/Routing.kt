package vsapp.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import vsapp.routes.userRoute

fun Application.configureRouting() {
    routing {
        userRoute()
    }
}