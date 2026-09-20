package com.jobflow.job_service.services;

import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.jobflow.job_service.model.Job;
import com.jobflow.job_service.repositores.JobRepositores;
import com.jobflow.job_service.dto.JobDTO;

@Service
public class JobService {
    private final JobRepositores jobRepositores;
    JobService(JobRepositores jobRepositores){
        this.jobRepositores = jobRepositores;
    }

    public Job saveJob(Job job){
        return jobRepositores.save(job);
    }
    public Job getJobById(Long id){
        return jobRepositores.findById(id).orElse(null);
    }
    public Job updateJob(Long id, JobDTO jobDTO){
        Job updateJob = jobRepositores.findById(id).orElse(null);
        updateJob.setTitle(jobDTO.getTitle());
        updateJob.setDescription(jobDTO.getDescription());
        updateJob.setSalary(jobDTO.getSalary());
        updateJob.setCreatedAt(LocalDateTime.now());
        updateJob.setLocation(jobDTO.getLocation());
        updateJob.setCompany(jobDTO.getCompany());
        return jobRepositores.save(updateJob);
    }
    public Job updateJob(Job job){
        return jobRepositores.save(job);
    }
    public void deleteJob(Long id){
        jobRepositores.deleteById(id);
    }
    public List<Job> getAllJobs(){
        return jobRepositores.findAll();
    }
}
