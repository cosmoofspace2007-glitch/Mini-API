package com.example.recruitmentapiapplication.service;

import com.example.recruitmentapiapplication.dto.request.CompanyRequest;
import com.example.recruitmentapiapplication.dto.respone.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest request);
    CompanyResponse getCompanyById(Long id);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse updateCompany(Long id, CompanyRequest request);
    void deleteCompany(Long id);
}