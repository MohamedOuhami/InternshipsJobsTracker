package com.v01d.LearnSpringSecurityJWT.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.expression.spel.ast.OpOr;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * User
 */
@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  // We want that the username be not null, and to be unique
  @Column(nullable = false, unique = true)
  private String username;

  // Same here, we want the email to be not null and to be unique
  @Column(nullable = false, unique = true)
  private String email;

  // We want now the password to not be null, but it's okay if It was not unique
  @Column(nullable = false)
  private String password;

  // Do a Many To Many relationship between the users and the roles
  @ManyToMany(fetch = FetchType.EAGER)
  // Create a join table that has the id of the user_id referencing the id
  // attribute of the user, and role_if for the attribute id of the role
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles;

  private LocalDate dob;

  @OneToMany(mappedBy = "creator")
  private List<Opportunity> opportunities;

  @OneToMany(mappedBy = "creator")
  private List<Job> jobs;

  public User() {
  }

  public User(String firstName, String lastName, String username, String email, String password, Set<Role> roles,LocalDate dob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.dob = dob;
  }
}
