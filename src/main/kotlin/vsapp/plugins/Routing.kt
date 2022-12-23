package vsapp.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import vsapp.routes.partyRoutes
import vsapp.routes.userRoute

/**
 * Configure all routes for the https api.
 */
fun Application.configureRouting() {
    routing {
        userRoute()
        partyRoutes()
    }
}