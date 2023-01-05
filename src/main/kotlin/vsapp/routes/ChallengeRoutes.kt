package vsapp.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import vsapp.controllers.ChallengeController
import vsapp.model.dtos.PartyDTO

fun Route.challengeRoutes() {

    val controller: ChallengeController by inject()

    route("challenge"){
        authenticate {
            get("categories"){
                call.respond(controller.getCategories())
            }
            get(){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val challenge = controller.getChallenge(call.receive<PartyDTO>(), userId)
                call.respond(challenge)
            }
            put("result"){
                
            }
        }
    }
}