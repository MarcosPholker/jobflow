package com.jobflow.job_service.model;

import java.time.LocalDateTime;

import com.jobflow.job_service.enums.StatusApplicationJob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long jobId;
    private LocalDateTime appliedAt;
    private StatusApplicationJob status;

    public JobApplication(){}

    public JobApplication(Long id, Long userId, Long jobId, LocalDateTime appliedAt, StatusApplicationJob status) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.appliedAt = appliedAt;
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public StatusApplicationJob getStatus() {
        return status;
    }

    public void setStatus(StatusApplicationJob status) {
        this.status = status;
    }

}
