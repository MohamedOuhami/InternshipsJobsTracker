package com.v01d.LearnSpringSecurityJWT.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.v01d.LearnSpringSecurityJWT.dto.EditUserDto;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.repository.UserRepository;
import com.v01d.LearnSpringSecurityJWT.util.EmailSender;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EmailSender emailSender;

  // Get a list of all the User
  public List<User> getAllUsers() {

    List<User> fetchedUsers = userRepository.findAll();

    return fetchedUsers;

  }

  // Get a User by his Id
  public User getUserById(Long id) throws Exception {
    Optional<User> optUser = userRepository.findById(id);

    if (optUser.isPresent()) {
      return optUser.get();
    } else {
      throw new Exception("Could not find the User with the id " + id);
    }
  }

  // Get a User by his Id
  public User getUserByUsername(String username) throws Exception {
    Optional<User> optUser = userRepository.findByUsername(username);

    if (optUser.isPresent()) {
      return optUser.get();
    } else {
      throw new Exception("Could not find the User with the username " + username);
    }
  }

  // Get a User by his Id
  public User getUserByEmail(String email) throws Exception {
    Optional<User> optUser = userRepository.findByEmail(email);

    if (optUser.isPresent()) {
      return optUser.get();
    } else {
      throw new Exception("Could not find the User with the email " + email);
    }
  }

  // Edit the User info
  public User editUserInfo(EditUserDto newUser, Long id) throws Exception {
    Optional<User> optUser = userRepository.findById(id);

    if (optUser.isPresent()) {
      User foundUser = optUser.get();

      foundUser.setFirstName(newUser.getFirstName());
      foundUser.setLastName(newUser.getLastName());
      foundUser.setUsername(newUser.getUsername());
      foundUser.setDob(newUser.getDob());

      return userRepository.save(foundUser);

    } else {
      throw new Exception("Could not find the user with the id " + id);
    }
  }

  // Change the password
  public User changePassword(String password, Long id) throws Exception {
    Optional<User> optUser = userRepository.findById(id);

    if (optUser.isPresent()) {
      User foundUser = optUser.get();

      if (BCrypt.checkpw(password, foundUser.getPassword())) {

        throw new Exception("The new password should not match the old one");

      }

      String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

      foundUser.setPassword(hashedPassword);

      return userRepository.save(foundUser);

    } else {
      throw new Exception("Could not find the user with the id " + id);
    }
  }

  // Delete a user by Id
  public String deleteUserById(Long id) {
    try {
      userRepository.deleteById(id);
      return "User was deleted successfully";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  // Send the account code
  public void sendActivationAccount(String email) throws Exception {

    // Send the activation Number to the user
    try {
      User foundUser = getUserByEmail(email);
      int activationCode = emailSender.sendActivationCode(email);
      foundUser.setActivationCode(activationCode);
      foundUser.setLastSentCode(LocalDateTime.now());
      userRepository.save(foundUser);

    } catch (Exception ex) {
      System.out.println("There was an error");
    }
  }

  // Activate the code
  public boolean activateAccount(long id, int sentActivation) throws Exception {

    try {
      User foundUser = getUserById(id);

      // Getting the last timestamp
      LocalDateTime lastSentCode = foundUser.getLastSentCode();
      if (lastSentCode == null || lastSentCode.plusMinutes(10).isBefore(LocalDateTime.now())) {
        System.out.println("Activation code is expired");
        return false; // Code is expired
      }

      if (sentActivation == foundUser.getActivationCode()) {
        foundUser.setActivated(true);
        userRepository.save(foundUser);
        return true; // Account successfully activated
      }

      return false; // Activation code did not match
    } catch (Exception ex) {
      System.out.println("There was an error: " + ex.getMessage());
      ex.printStackTrace(); // Log the stack trace for debugging
      throw ex; // Re-throw the exception to let the caller handle it
    }

  }
}
