package com.jobflow.auth_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import com.jobflow.auth_service.enums.TypeUser;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank 
    private String name;
    @NotBlank 
    private String email;
    @NotBlank 
    private String password;
    
    private TypeUser typeUser;

    public Company() {}

    public Company(Long id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

  
    public void setPassword(String password) {
        this.password = password;
    }


    public TypeUser getTypeUser() {
        return typeUser;
    }


    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

}
