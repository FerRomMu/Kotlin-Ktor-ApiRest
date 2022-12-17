package vsapp.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import vsapp.controllers.TokenController

fun Application.configureSecurity() {

    val config = HoconApplicationConfig(ConfigFactory.load())
    val tokenController = TokenController(config)

    authentication {
            jwt {
                verifier(tokenController.verifyJWTToken())
                realm = config.property("jwt.realm").getString()
                validate { jwtCredential ->
                    print(jwtCredential.payload.getClaim("user").asString())
                    if(jwtCredential.payload.getClaim("userId").asLong() != null) {
                        JWTPrincipal(jwtCredential.payload)
                    } else {
                        null
                    }
                }
            }
        }
}
