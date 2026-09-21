package com.jobflow.auth_service.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobflow.auth_service.enums.TypeUser;
import com.jobflow.auth_service.model.Company;
import com.jobflow.auth_service.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        return gerarToken(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getTypeUser()
        );
    }

    public String gerarToken(Company company) {

        return gerarToken(
                company.getId(),
                company.getEmail(),
                company.getTypeUser()
        );
    }

    public String gerarToken(Long id, String email, TypeUser typeUser) {

        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(email)
                .claim("userId", id)
                .claim("role", typeUser.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {

        try {

            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(
                            secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (Exception e) {
            return null;
        }
    }

    public String validarToken(String token) {

        Claims claims = getClaims(token);

        if (claims == null) {
            return null;
        }

        return claims.getSubject();
    }

    public String getRole(String token) {

        Claims claims = getClaims(token);

        if (claims == null) {
            return null;
        }

        return claims.get("role", String.class);
    }
}