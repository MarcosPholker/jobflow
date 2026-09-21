package com.jobflow.auth_service.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobflow.auth_service.Repository.CompanyRepository;
import com.jobflow.auth_service.dto.CompanyDTO;
import com.jobflow.auth_service.enums.TypeUser;
import com.jobflow.auth_service.model.Company;
import com.jobflow.auth_service.security.TokenService;

@Service
public class CompanyServices {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    CompanyServices(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Company saveCompany(CompanyDTO companyDTO) {
        if (companyRepository.findByEmail(companyDTO.getEmail()) != null) {
            throw new RuntimeException("Email ja cadastrado");
        }

        Company company = new Company(null, companyDTO.getName(), companyDTO.getEmail(), passwordEncoder.encode(companyDTO.getPassword()));
        company.setTypeUser(TypeUser.COMPANY);
        return companyRepository.save(company);
    }

    public String login(CompanyDTO companyDTO) {
        Company company = companyRepository.findByEmail(companyDTO.getEmail());
        if (company != null && passwordEncoder.matches(companyDTO.getPassword(), company.getPassword())) {
            return tokenService.gerarToken(company);
        }
        return null;
    }
}
