package com.v01d.LearnSpringSecurityJWT.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpportunityCreateDto {

  private String companyName;

  private String city;

  private String postName;

  private String jobType;

  private String email;

  private String phone;

  private String status;

}
