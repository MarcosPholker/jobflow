package com.jobflow.job_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobflow.job_service.model.Job;
import com.jobflow.job_service.services.JobService;
import com.jobflow.job_service.dto.JobDTO;


@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Job> createJob(@RequestBody  Job job) {
        Job createdJob = jobService.saveJob(job);
        return ResponseEntity.ok(createdJob);
    }

    @PutMapping ("/update/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Job> updateJob(@PathVariable  Long id,@RequestBody  JobDTO jobDTO) {
        return ResponseEntity.ok(jobService.updateJob(id,jobDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alljobs")
    public ResponseEntity <List<Job>> getAllJobs() {
        return ResponseEntity.ok().body(jobService.getAllJobs());
    }
}
