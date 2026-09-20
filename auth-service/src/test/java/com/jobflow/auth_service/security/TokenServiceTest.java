package com.jobflow.auth_service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class TokenServiceTest {

    private static final String SECRET = "JobFlowJWTSecretKey2026MuitoSegura123456789";

    @Test
    void gerarToken_shouldCreateTokenThatCanBeValidated() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", SECRET);

        String token = tokenService.gerarToken("alice@example.com");

        assertNotNull(token);
        assertEquals("alice@example.com", tokenService.validarToken(token));
    }

    @Test
    void validarToken_shouldReturnNullForInvalidToken() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", SECRET);

        String result = tokenService.validarToken("invalid-token");

        assertNull(result);
    }
}
