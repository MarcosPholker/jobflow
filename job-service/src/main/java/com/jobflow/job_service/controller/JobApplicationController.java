package com.jobflow.job_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobflow.job_service.services.JobApplicationService;
import com.jobflow.job_service.model.JobApplication;

@RestController 

public class JobApplicationController {
    JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/application")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<JobApplication> jobApplication(@PathVariable Long jobId) {
        return ResponseEntity.ok().body(jobApplicationService.applicationJob(jobId));
    }
}
