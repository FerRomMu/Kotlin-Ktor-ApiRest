package vsapp.routes

import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import vsapp.controllers.ChallengeController

fun Route.challengeRoutes() {

    val controller: ChallengeController by inject()

    route("challenge"){
        authenticate {
            get("categories"){}
            get(){}
            put("result"){}
        }
    }
}