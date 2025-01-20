package com.v01d.LearnSpringSecurityJWT.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  private Long id;

  private String companyName;

  private String city;

  private String postName;

  private String jobType;

  private String description;

  private String email;

  private String phone;

  @ManyToOne
  @JsonIgnore
  private User creator;
}
