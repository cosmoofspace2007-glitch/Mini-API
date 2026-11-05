package com.example.recruitmentapiapplication.service;

import com.example.recruitmentapiapplication.dto.request.JobRequest;
import com.example.recruitmentapiapplication.dto.respone.JobResponse;
import com.example.recruitmentapiapplication.dto.respone.PageResponse;
import com.example.recruitmentapiapplication.entity.JobStatus;
import org.springframework.data.domain.Pageable;

public interface JobService {
    JobResponse createJob(JobRequest request);
    JobResponse getJobById(Long id);
    PageResponse<JobResponse> getJobs(String keyword, String location, JobStatus status, Pageable pageable);
    JobResponse updateJob(Long id, JobRequest request);
    void deleteJob(Long id);
}
