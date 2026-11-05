package com.example.recruitmentapiapplication.repository;

import com.example.recruitmentapiapplication.entity.Job;
import com.example.recruitmentapiapplication.entity.JobStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class JobSpecification {
    public static Specification<Job> filterJobs(String keyword, String location, JobStatus status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(keyword)) {
                String pattern = "%" + keyword.trim().toLowerCase() + "%";
                Predicate titleLike = cb.like(cb.lower(root.get("title")), pattern);
                Predicate descLike = cb.like(cb.lower(root.get("description")), pattern);
                predicates.add(cb.or(titleLike, descLike));
            }

            if (StringUtils.hasText(location)) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + location.trim().toLowerCase() + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}