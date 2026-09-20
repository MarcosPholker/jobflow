package com.jobflow.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobflow.auth_service.dto.CompanyDTO;
import com.jobflow.auth_service.model.Company;
import com.jobflow.auth_service.services.CompanyServices;

@RestController
public class CompanyController {
    private final CompanyServices companyServices;

    CompanyController(CompanyServices companyServices){
        this.companyServices = companyServices;
    }

    @PostMapping("/companylogin")
    public ResponseEntity<Company> login(String email, String password){
        return ResponseEntity.ok(companyServices.login(email, password));
    }

    @PostMapping("/companyregister")
    public ResponseEntity<Company> cadastro(CompanyDTO companyDTO){
        return ResponseEntity.ok(companyServices.saveCompany(companyDTO));
    }
}
