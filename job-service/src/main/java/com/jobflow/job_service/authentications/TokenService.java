package com.jobflow.job_service.authentications;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public Claims validarToken(String token) {

        try {

            SecretKey key = Keys.hmacShaKeyFor(
                    secret.getBytes(StandardCharsets.UTF_8)
            );

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (Exception e) {
            return null;
        }
    }
}