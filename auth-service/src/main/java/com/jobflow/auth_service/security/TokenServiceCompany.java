package com.jobflow.auth_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobflow.auth_service.model.Company;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenServiceCompany {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Company company) {

    SecretKey key = Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8)
    );

    return Jwts.builder()
            .subject(company.getEmail())
            .claim("userId", company.getId())
            .claim("role", company.getTypeUser().name())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 3600000))
            .signWith(key)
            .compact();
    }
    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            
            // Retorna o assunto (ex: usuário ou e-mail) se for válido
            return claims.getSubject();
        } catch (Exception e) {
            // Token inválido ou expirado
            return null;
        }
    }
        

}
