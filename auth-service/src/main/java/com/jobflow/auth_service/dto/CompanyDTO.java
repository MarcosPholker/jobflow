package com.jobflow.auth_service.dto;

import com.jobflow.auth_service.enums.TypeUser;

public class CompanyDTO {
    private String name;
    private String email;
    private String password;
    private TypeUser typeUser;

    public CompanyDTO(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.typeUser = TypeUser.COMPANY;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return typeUser return the typeUser
     */
    public TypeUser getTypeUser() {
        return typeUser;
    }

    /**
     * @param typeUser the typeUser to set
     */
    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

}
