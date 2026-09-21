package com.jobflow.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> login(@RequestBody CompanyDTO companyDTO) {
        String token = companyServices.login(companyDTO);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/companyregister")
    public ResponseEntity<Company> cadastro(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(201).body(companyServices.saveCompany(companyDTO));
    }
}
