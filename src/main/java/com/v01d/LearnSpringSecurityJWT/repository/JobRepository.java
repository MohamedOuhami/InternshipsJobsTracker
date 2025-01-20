package com.v01d.LearnSpringSecurityJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.LearnSpringSecurityJWT.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

  
}
