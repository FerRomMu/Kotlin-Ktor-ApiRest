package vsapp.controllers

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*
import vsapp.model.dtos.UserDTO
import java.util.*

class TokenController(private val config: HoconApplicationConfig) {

    val audience = config.property("jwt.audience").getString()
    val secret = config.property("jwt.secret").getString()
    val issuer = config.property("jwt.issuer").getString()
    val expirationData = System.currentTimeMillis() + 10800000

    /**
     * Generates a JWT token for a given user.
     * param: The userDTO of the user to generate the token.
     * returns: A string with the value of the JWT Token.
     */
    fun generateJWTToken(user: UserDTO): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withExpiresAt(Date(expirationData))
            .withClaim("user", user.user)
            .withClaim("userId", user.id)
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}