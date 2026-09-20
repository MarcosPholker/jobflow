package com.jobflow.auth_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jobflow.auth_service.Repository.UsuarioRepository;
import com.jobflow.auth_service.dto.UsuarioDTO;
import com.jobflow.auth_service.model.Usuario;
import com.jobflow.auth_service.security.TokenService;

@ExtendWith(MockitoExtension.class)
class UsuarioServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UsuarioServices usuarioServices;

    @Test
    void saveUsuario_shouldEncodePasswordAndPersistUser() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("alice", "alice@example.com", "plain-password");
        when(passwordEncoder.encode("plain-password")).thenReturn("encoded-password");

        Usuario savedUsuario = new Usuario(1L, "alice", "alice@example.com", "encoded-password");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(savedUsuario);

        Usuario result = usuarioServices.saveUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("encoded-password", result.getPassword());
        verify(usuarioRepository).save(argThat(usuario ->
            "alice".equals(usuario.getUsername()) &&
            "alice@example.com".equals(usuario.getEmail()) &&
            "encoded-password".equals(usuario.getPassword())
        ));
    }

    @Test
    void login_shouldReturnTokenWhenCredentialsMatch() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("alice", "alice@example.com", "plain-password");
        Usuario usuario = new Usuario(1L, "alice", "alice@example.com", "encoded-password");

        when(usuarioRepository.findByEmail("alice@example.com")).thenReturn(usuario);
        when(passwordEncoder.matches("plain-password", "encoded-password")).thenReturn(true);
        when(tokenService.gerarToken("alice@example.com")).thenReturn("jwt-token");

        String token = usuarioServices.login(usuarioDTO);

        assertEquals("jwt-token", token);
    }

    @Test
    void login_shouldReturnNullWhenCredentialsDoNotMatch() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("alice", "alice@example.com", "wrong-password");
        Usuario usuario = new Usuario(1L, "alice", "alice@example.com", "encoded-password");

        when(usuarioRepository.findByEmail("alice@example.com")).thenReturn(usuario);
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        String token = usuarioServices.login(usuarioDTO);

        assertNull(token);
    }
}
