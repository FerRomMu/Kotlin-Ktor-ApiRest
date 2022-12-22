package vsapp.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import vsapp.controllers.PartyController
import vsapp.exceptions.ForbiddenPartyException
import vsapp.exceptions.NotFoundPartyException
import vsapp.model.dtos.ErrorDTO
import vsapp.model.dtos.PartyDTO

fun Route.partyRoutes() {

    val controller: PartyController by inject()

    route("party"){
        authenticate {
            get("/{id}"){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val party = controller.getParty(call.parameters["id"]!!.toLong(), userId)
                    call.respond(party)
                } catch (e: ForbiddenPartyException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO("Invalid party for this user."))
                } catch (e: NotFoundPartyException){
                    call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO(e.message))
                }
            }
            post(){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                val (id, party) = controller.createParty(call.receive<PartyDTO>(), userId)
                if (party == null){
                    call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Malformed party."))
                }
                call.response.headers.append("partyId", id.toString())
                call.respond(party)
            }
            put("/{id}/edit"){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val party = controller.editParty(call.receive<PartyDTO>(), userId)
                    if (party == null) {
                        call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO("Malformed party."))
                    }
                    call.respond(party)
                } catch (e: ForbiddenPartyException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO("Invalid party for this user."))
                } catch (e: NotFoundPartyException){
                    call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO(e.message))
                }
            }
        }
    }
}