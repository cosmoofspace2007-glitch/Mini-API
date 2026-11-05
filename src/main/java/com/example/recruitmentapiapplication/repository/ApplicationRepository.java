package com.example.recruitmentapiapplication.repository;

import com.example.recruitmentapiapplication.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByJobIdAndEmail(Long jobId, String email);
    List<Application> findByJobId(Long jobId);
}