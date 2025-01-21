package com.v01d.LearnSpringSecurityJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.v01d.LearnSpringSecurityJWT.dto.ActivationRequest;
import com.v01d.LearnSpringSecurityJWT.dto.EditUserDto;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users") // Base path for all user-related endpoints
public class UserController {

  @Autowired
  private UserService userService;

  // Get all users

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // Get a user by ID
  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  // Get a user by username
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/username/{username}")
  public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
    try {
      User user = userService.getUserByUsername(username);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  // Get a user by email
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/email/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
    try {
      User user = userService.getUserByEmail(email);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  // Edit user info
  @PutMapping("/{id}")
  public ResponseEntity<?> editUserInfo(@RequestBody EditUserDto newUser, @PathVariable Long id) {
    try {
      User updatedUser = userService.editUserInfo(newUser, id);
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  // Change user password
  @PutMapping("/{id}/change-password")
  public ResponseEntity<?> changePassword(@RequestBody String newPassword, @PathVariable Long id) {
    try {
      User updatedUser = userService.changePassword(newPassword, id);
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  // Delete a user by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
    try {
      String message = userService.deleteUserById(id);
      return new ResponseEntity<>(message, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Activate the account
  @PutMapping("/activateAccount/{id}")
  public ResponseEntity<?> activateAccount(@PathVariable Long id,@RequestBody ActivationRequest activationRequest){
    try {
      System.out.println("========== Activating the account ========");
      boolean isActivated = userService.activateAccount(id, activationRequest.getActivationCode());
      return new ResponseEntity<>(isActivated, HttpStatus.OK);
    }
    catch(Exception ex){
      System.out.println("======= Error activating the account ====== for error " + ex.getMessage());
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



}
