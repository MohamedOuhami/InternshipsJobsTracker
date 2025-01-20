package com.v01d.LearnSpringSecurityJWT.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opportunity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private String companyName;

  private String city;

  private String postName;

  private String jobType;

  private String Status;

  private String email;

  private String phone;

  private LocalDate nextInterview;

  @ManyToOne
  @JsonIgnore
  private User creator;
}
