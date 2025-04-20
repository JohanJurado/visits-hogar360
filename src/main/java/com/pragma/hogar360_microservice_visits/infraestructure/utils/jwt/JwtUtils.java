package com.pragma.hogar360_microservice_visits.infraestructure.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.ExceptionConstants.*;
import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.InfrastructureConstants.*;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String generateMicroserviceToken() {
        return JWT.create()
                .withSubject(SUBJECT_MICROSERVICE)
                .withIssuer(userGenerator)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .withClaim(AUTHORITIES_CLAIM_KEY, ROLE_PREFIX.concat(AUTHORITY_MICROSERVICE))
                .sign(Algorithm.HMAC256(privateKey));
    }

    public DecodedJWT validateToken(String token){
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.decode(token);
        } catch (JWTDecodeException ex) {
            throw new JWTVerificationException(TOKEN_MALFORMED_MESSAGE);
        }

        if (decodedJWT.getExpiresAt() != null &&
                decodedJWT.getExpiresAt().before(new Date())) {
            throw new JWTVerificationException(TOKEN_EXPIRED_MESSAGE);
        }

        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(userGenerator)
                .build();
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException(TOKEN_INVALID_MESSAGE);
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String nameClaim) {
        return decodedJWT.getClaim(nameClaim);
    }
}
