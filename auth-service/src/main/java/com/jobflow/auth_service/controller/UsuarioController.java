package com.jobflow.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobflow.auth_service.dto.UsuarioDTO;
import com.jobflow.auth_service.model.Usuario;
import com.jobflow.auth_service.services.UsuarioServices;

@RestController 
public class UsuarioController {
    
    private UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices){
        this.usuarioServices = usuarioServices;
    }

    @PostMapping("/userregister")
    public ResponseEntity<Usuario> registerUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario savedUsuario = usuarioServices.saveUsuario(usuarioDTO);
        return ResponseEntity.status(201).body(savedUsuario);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioDTO usuarioDTO){
        String token = usuarioServices.login(usuarioDTO);
        if(token != null){
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
