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
import vsapp.exceptions.MalformedPartyException
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
                    if(party == null) {
                        call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("Not found party with given id."))
                    }
                    call.respond(party!!)
                } catch (e: ForbiddenPartyException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO(e.message))
                }
            }
            post(){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val (id, party) = controller.createParty(call.receive<PartyDTO>(), userId)
                    call.response.headers.append("partyId", id.toString())
                    call.respond(party!!)
                } catch(e: MalformedPartyException){
                    call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO(e.message))
                }
            }
            put("/{id}/edit"){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val party = controller.editParty(call.receive<PartyDTO>(), call.parameters["id"]!!.toLong(), userId)
                    if (party == null) {
                        call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("Not found party with given id."))
                    }
                    call.respond(party!!)
                } catch (e: ForbiddenPartyException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO(e.message))
                } catch (e: MalformedPartyException){
                    call.respond(HttpStatusCode(400, "BadRequest"), ErrorDTO(e.message))
                }
            }
            delete("/{id}/delete"){
                TODO()
            }
        }
    }
}