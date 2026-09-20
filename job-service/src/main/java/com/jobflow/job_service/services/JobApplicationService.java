package com.jobflow.job_service.services;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jobflow.job_service.repositores.JobRepositores;
import com.jobflow.job_service.model.JobApplication;
import com.jobflow.job_service.model.Job;
import com.jobflow.job_service.enums.StatusApplicationJob;
import com.jobflow.job_service.repositores.ApplicationJobRepositores;

@Service 
public class JobApplicationService {
    private final JobRepositores jobRepositores;
    private final ApplicationJobRepositores applicationJobRepositores;
    public JobApplicationService(JobRepositores jobRepositores, ApplicationJobRepositores applicationJobRepositores) {
        this.jobRepositores = jobRepositores;
        this.applicationJobRepositores = applicationJobRepositores;
    }

    public JobApplication applicationJob(Long jobId) {
        Job job = jobRepositores.findById(jobId).orElse(null);
        
        if (job != null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Long userId = (Long) authentication.getPrincipal();

            JobApplication applicationJob = new JobApplication();

            applicationJob.setJobId(jobId);
            applicationJob.setUserId(userId);
            applicationJob.setStatus(StatusApplicationJob.APPLIED);
            applicationJob.setAppliedAt(LocalDateTime.now());

            
            return applicationJobRepositores.save(applicationJob);
        }
        return null;
    }

}
