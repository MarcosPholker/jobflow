package com.jobflow.job_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobflow.job_service.enums.StatusApplicationJob;
import com.jobflow.job_service.model.Job;
import com.jobflow.job_service.model.JobApplication;
import com.jobflow.job_service.repositores.JobRepositores;

@ExtendWith(MockitoExtension.class)
class ApplicationJobServiceTest {

    @Mock
    private JobRepositores jobRepositores;

    @InjectMocks
    private ApplicationJobService applicationJobService;

    @Test
    void applicationJob_shouldCreateApplicationWhenJobExists() {
        Job job = new Job(10L, "Developer", "Build backend", 7000.0, "Remote", "Acme", "FULL_TIME", LocalDateTime.now());
        when(jobRepositores.findById(10L)).thenReturn(Optional.of(job));

        JobApplication result = applicationJobService.applicationJob(5L, 10L);

        assertNotNull(result);
        assertEquals(5L, result.getUserId());
        assertEquals(10L, result.getJobId());
        assertEquals(StatusApplicationJob.APPLIED, result.getStatus());
        assertNotNull(result.getAppliedAt());
    }

    @Test
    void applicationJob_shouldReturnNullWhenJobDoesNotExist() {
        when(jobRepositores.findById(99L)).thenReturn(Optional.empty());

        JobApplication result = applicationJobService.applicationJob(5L, 99L);

        assertNull(result);
    }
}
