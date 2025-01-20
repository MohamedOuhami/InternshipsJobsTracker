package com.v01d.LearnSpringSecurityJWT.dto;

import lombok.Data;

/**
 * AuthResponseDto
 */
@Data
public class AuthResponseDto {

  private String accessToken;
  private Long id;
}
