package com.v01d.LearnSpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.LearnSpringSecurityJWT.model.Role;

/**
 * RoleRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

  Optional<Role> findByName(String name);
  
}
