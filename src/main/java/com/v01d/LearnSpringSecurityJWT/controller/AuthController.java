package com.v01d.LearnSpringSecurityJWT.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.LearnSpringSecurityJWT.dto.AuthResponseDto;
import com.v01d.LearnSpringSecurityJWT.dto.LoginRequestDto;
import com.v01d.LearnSpringSecurityJWT.dto.RegisterDto;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.repository.UserRepository;
import com.v01d.LearnSpringSecurityJWT.service.AuthServiceImpl;
import com.v01d.LearnSpringSecurityJWT.service.UserService;
import com.v01d.LearnSpringSecurityJWT.util.EmailSender;

/**
 * AuthController
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  private AuthServiceImpl authService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;
  

  @PostMapping("/login")
  // Build the Login REST api
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginDto){

    System.out.println("From the Auth Controller");
    System.out.println(loginDto);

    // Receive the token from the auth service
    String token = authService.login(loginDto);


    System.out.println("This is supposed to be the token " + token);

    User foundUser = userRepository.findByEmail(loginDto.getEmail()).get();

    // Set the token as a response using JwtAuthResponse Dto class
    AuthResponseDto authResponseDto = new AuthResponseDto();
    authResponseDto.setAccessToken(token);
    authResponseDto.setId(foundUser.getId());

    // Return the response to the user
    return new ResponseEntity<>(authResponseDto,HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterDto registerDto) throws Exception{

    User userSaved = authService.register(registerDto);

    userService.sendActivationAccount(registerDto.getEmail());
    return ResponseEntity.ok(userSaved);
  }
  

}
