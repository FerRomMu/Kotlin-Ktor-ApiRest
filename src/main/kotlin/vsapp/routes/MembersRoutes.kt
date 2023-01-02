package vsapp.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import vsapp.controllers.MemberController
import vsapp.exceptions.ForbiddenMemberException
import vsapp.model.dtos.ErrorDTO

fun Route.membersRoutes() {

    val controller: MemberController by inject()

    route("/member"){
        authenticate {
            get("/{memberId}"){
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("userId").asLong()
                try {
                    val member = controller.getMember(call.parameters["memberId"]!!.toLong(), userId)
                    if(member == null) {
                        call.respond(HttpStatusCode(404, "NotFound"), ErrorDTO("Not found member with given id."))
                    }
                    call.respond(member!!)
                } catch (e: ForbiddenMemberException){
                    call.respond(HttpStatusCode(403, "Forbidden"), ErrorDTO(e.message))
                }
            }
        }
    }
}