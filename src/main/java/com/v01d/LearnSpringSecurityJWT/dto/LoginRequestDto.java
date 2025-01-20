package com.v01d.LearnSpringSecurityJWT.dto;

import lombok.Data;

/**
 * LoginRequestDto
 */
@Data
public class LoginRequestDto {

  private String email;
  private String password;
}
