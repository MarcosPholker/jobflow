package com.jobflow.job_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import com.jobflow.job_service.model.Job;
import com.jobflow.job_service.repositores.JobRepositores;
class JobServiceTest {

    private JobRepositores jobRepositores;

    private JobService jobService;

    void saveJob_shouldPersistAndReturnJob() {
        Job job = new Job(1L, "Developer", "Build backend", 7500.0, "Remote", "Acme", "FULL_TIME", LocalDateTime.now());
        when(jobRepositores.save(job)).thenReturn(job);

       
    }

    void getJobById_shouldReturnExistingJob() {
        Job job = new Job(2L, "QA Analyst", "Test APIs", 6500.0, "São Paulo", "Contoso", "HYBRID", LocalDateTime.now());
        when(jobRepositores.findById(2L)).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(2L);

        assertNotNull(result);
        assertEquals("QA Analyst", result.getTitle());
    }


    void getJobById_shouldReturnNullWhenNotFound() {
        when(jobRepositores.findById(999L)).thenReturn(Optional.empty());

        Job result = jobService.getJobById(999L);

        assertNull(result);
    }


    void updateJob_shouldSaveAndReturnUpdatedJob() {
        Job job = new Job(3L, "Designer", "Design interfaces", 6200.0, "Rio", "Globex", "REMOTE", LocalDateTime.now());
        when(jobRepositores.save(job)).thenReturn(job);

        Job result = jobService.updateJob(job);

        assertEquals("Designer", result.getTitle());
        verify(jobRepositores).save(job);
    }


    void deleteJob_shouldDelegateDeleteToRepository() {
        jobService.deleteJob(4L);

        verify(jobRepositores).deleteById(4L);
    }


    void getAllJobs_shouldReturnRepositoryList() {
        List<Job> jobs = List.of(
            new Job(1L, "Developer", "Build backend", 7500.0, "Remote", "Acme", "FULL_TIME", LocalDateTime.now()),
            new Job(2L, "Manager", "Lead team", 9000.0, "Berlin", "Zeta", "REMOTE", LocalDateTime.now())
        );
        when(jobRepositores.findAll()).thenReturn(jobs);

        List<Job> result = jobService.getAllJobs();

        assertEquals(2, result.size());
        assertEquals("Manager", result.get(1).getTitle());
    }
}
