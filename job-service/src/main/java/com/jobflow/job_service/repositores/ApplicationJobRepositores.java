package com.jobflow.job_service.repositores;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobflow.job_service.model.JobApplication;

public interface ApplicationJobRepositores extends JpaRepository<JobApplication, Long> {

}
