package com.v01d.LearnSpringSecurityJWT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.v01d.LearnSpringSecurityJWT.model.Role;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.repository.RoleRepository;
import com.v01d.LearnSpringSecurityJWT.repository.UserRepository;

@SpringBootApplication
public class LearnSpringSecurityJwtApplication {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(LearnSpringSecurityJwtApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {

      System.out.println("======== Creating the ROLES =========");
      // Insert the roles
      Role userRole = new Role("ROLE_USER");
      Role adminRole = new Role("ROLE_ADMIN");

      roleRepository.saveAll(List.of(userRole, adminRole));

      Set<Role> userRoleSet = new HashSet<>();
      Set<Role> adminRoleSet = new HashSet<>();

      userRoleSet.add(userRole);
      adminRoleSet.add(adminRole);
    };

  }
}
