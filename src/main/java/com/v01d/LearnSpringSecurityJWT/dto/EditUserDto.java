package com.v01d.LearnSpringSecurityJWT.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EditUserDto {

  private String firstName;
  private String lastName;
  private String username;
  private LocalDate dob;

}
