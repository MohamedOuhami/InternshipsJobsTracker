package com.v01d.LearnSpringSecurityJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.LearnSpringSecurityJWT.model.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

  
}
