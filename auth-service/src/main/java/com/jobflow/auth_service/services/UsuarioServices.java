package com.jobflow.auth_service.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobflow.auth_service.Repository.UsuarioRepository;
import com.jobflow.auth_service.dto.UsuarioDTO;
import com.jobflow.auth_service.enums.TypeUser;
import com.jobflow.auth_service.model.Usuario;
import com.jobflow.auth_service.security.TokenService;

@Service 
public class UsuarioServices {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UsuarioServices(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Usuario saveUsuario(UsuarioDTO usuarioDTO){
        String encodedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
        Usuario usuario = new Usuario(null , usuarioDTO.getUsername(), usuarioDTO.getEmail(), encodedPassword);
        usuario.setTypeUser(TypeUser.USER);

        if(usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null){
            throw new RuntimeException("Email ja cadastrado");
        }
        
        return usuarioRepository.save(usuario);
    }

    public String login(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if(usuario != null && passwordEncoder.matches(usuarioDTO.getPassword(), usuario.getPassword())){
            return tokenService.gerarToken(usuario.getId(), usuarioDTO.getEmail(), TypeUser.COMPANY);
        }
        return null;
    }

}
