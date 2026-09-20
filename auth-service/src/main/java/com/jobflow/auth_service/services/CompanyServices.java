package com.jobflow.auth_service.services;

import org.springframework.stereotype.Service;

import com.jobflow.auth_service.Repository.CompanyRepository;
import com.jobflow.auth_service.model.Company;
import com.jobflow.auth_service.dto.CompanyDTO;
import com.jobflow.auth_service.enums.TypeUser;

@Service
public class CompanyServices {
    private final CompanyRepository companyRepository;
    CompanyServices(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(CompanyDTO companyDTO){

        if(companyRepository.findByEmail(companyDTO.getEmail()) != null){
            throw new RuntimeException("Email ja cadastrado");
        }

        Company company = new Company(null, companyDTO.getName(), companyDTO.getEmail(), companyDTO.getPassword());
        company.setTypeUser(TypeUser.COMPANY);
        return companyRepository.save(company);
    }

    public Company login(String email, String password){
        Company company = companyRepository.findByEmail(email);
        if(company != null && company.getPassword().equals(password)){
            return company;
        }
        throw new RuntimeException("email e senha não correspodem");
    }
}
