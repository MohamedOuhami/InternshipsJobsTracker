package com.v01d.LearnSpringSecurityJWT.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * RegisterDto
 */
@Data
public class RegisterDto {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String username;
  private LocalDate dob;
}
