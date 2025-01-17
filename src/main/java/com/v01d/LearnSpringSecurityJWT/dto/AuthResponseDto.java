package com.v01d.LearnSpringSecurityJWT.dto;

import lombok.Data;

/**
 * AuthResponseDto
 */
@Data
public class AuthResponseDto {

  private String email;
  private String accessToken;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
