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
import vsapp.exceptions.ForbiddenMemberException
import vsapp.model.dtos.ChallengeRequestDTO
import vsapp.model.dtos.ChallengeResultDTO
import vsapp.model.dtos.ErrorDTO
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
                try {
                    val challenge = controller.getChallenge(call.receive<ChallengeRequestDTO>(), userId)
                    if(challenge == null) {
                        call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("Not found member with given id."))
                    }
                    call.respond(challenge!!)
                } catch (e: ForbiddenMemberException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO(e.message))
                }
            }
            put("result"){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val result = controller.saveResult(call.receive<ChallengeResultDTO>(), userId)
                    if(result == null) {
                        call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("Not found members with given ids."))
                    }
                    call.respond(result!!)
                } catch (e: ForbiddenMemberException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO(e.message))
                }
            }
        }
    }
}