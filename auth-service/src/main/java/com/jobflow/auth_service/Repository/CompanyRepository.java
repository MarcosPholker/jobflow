package com.jobflow.auth_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jobflow.auth_service.model.Company;

@Repository 
public interface CompanyRepository extends JpaRepository<Company, Long>{
    Company findByEmail(String email);
}
