package com.bg.bzahov.achievementsBG.security.jwt;

import com.bg.bzahov.achievementsBG.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_AUTHORIZATION_TOKEN_INVALID;

@Component
public class JWTGenerator {
    //private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final Key SECURITY_KEY = Keys.secretKeyFor(SIGNATURE_ALGORITHM);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = getExpireDate(currentDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SECURITY_KEY, SIGNATURE_ALGORITHM)
                .compact();
        printTokenIfDebug(token);
        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECURITY_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECURITY_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException(
                    ERROR_AUTHORIZATION_TOKEN_INVALID,
                    ex.fillInStackTrace()
            );
        }
    }

    @NotNull
    private static Date getExpireDate(Date currentDate) {
        return new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
    }
    public static final boolean DEBUG = false;

    private static void printTokenIfDebug(String token) {
        if (DEBUG) {
            System.out.println("New token :");
            System.out.println(token);
        }
    }
}
