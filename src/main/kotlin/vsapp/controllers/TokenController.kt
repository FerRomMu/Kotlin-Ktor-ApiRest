package vsapp.controllers

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*
import vsapp.model.dtos.UserDTO
import java.util.*

class TokenController(val config: HoconApplicationConfig) {

    /**
     * Generates a JWT token for a given user.
     * param: The userDTO of the user to generate the token.
     * returns: A string with the value of the JWT Token.
     * TODO: Set an expiration time for the token returned.
     */
    fun generateJWTToken(user: UserDTO): String {
        val audience = config.property("jwt.audience").getString()
        val secret = config.property("jwt.secret").getString()
        val issuer = config.property("jwt.issuer").getString()
        val expirationData = System.currentTimeMillis() + 10800000

        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withExpiresAt(Date(expirationData))
            .withClaim("user", user.user)
            .withClaim("userId", user.id)
            .sign(Algorithm.HMAC256(secret))
    }
}