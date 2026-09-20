package com.jobflow.job_service.repositores;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobflow.job_service.model.Job;

import org.springframework.stereotype.Repository;

@Repository 
public interface JobRepositores extends JpaRepository<Job, Long> {
}