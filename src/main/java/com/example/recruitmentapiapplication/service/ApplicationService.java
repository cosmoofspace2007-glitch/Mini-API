package com.example.recruitmentapiapplication.service;


import com.example.recruitmentapiapplication.dto.request.ApplicationRequest;
import com.example.recruitmentapiapplication.dto.respone.ApplicationResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse applyJob(Long jobId, ApplicationRequest request);
    List<ApplicationResponse> getApplicationsByJobId(Long jobId);
}

