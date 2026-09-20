package com.jobflow.auth_service.dto;

public class UsuarioDTO {
    private String username;
    private String email;
    private String password;


    public UsuarioDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
}
