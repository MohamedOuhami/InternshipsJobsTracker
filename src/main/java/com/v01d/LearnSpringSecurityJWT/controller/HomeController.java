package com.v01d.LearnSpringSecurityJWT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 */
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin")
  public ResponseEntity<String> helloAdmin() {
    return ResponseEntity.ok("Hello Admin");
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/user")
  public ResponseEntity<String> helloUser() {
    return ResponseEntity.ok("Hello User");
  }

}
