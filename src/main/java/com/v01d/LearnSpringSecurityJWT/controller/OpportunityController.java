package com.v01d.LearnSpringSecurityJWT.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.LearnSpringSecurityJWT.dto.OpportunityCreateDto;
import com.v01d.LearnSpringSecurityJWT.model.Opportunity;
import com.v01d.LearnSpringSecurityJWT.service.OpportunityService;

@RestController
@RequestMapping("/api/v1/opportunity")
public class OpportunityController {

  @Autowired
  private OpportunityService opportunityService;

  // Get all the opportunities
  @GetMapping
  public ResponseEntity<List<Opportunity>> getAllOpportunities() {
    try {
      List<Opportunity> allOpportunities = opportunityService.getAllOpportunities();

      return ResponseEntity.ok(allOpportunities);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // Get opportunity by Id
  @GetMapping("/{id}")
  public ResponseEntity<Object> getOpportunityById(@PathVariable Long id) {
    try {
      Opportunity foundOpportunity = opportunityService.getOpportunityById(id);

      return ResponseEntity.ok(foundOpportunity);
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
  }

  // Get Opportunities By User
  @GetMapping("/byUser/{userId}")
  public ResponseEntity<Object> getOpportunitiesByUser(@PathVariable Long userId) {
    try {
      List<Opportunity> foundOpportunities = opportunityService.getUsersOps(userId);

      return ResponseEntity.ok(foundOpportunities);
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
  }

  // Create a new Opportunity
  @PostMapping("/{userId}")
  public ResponseEntity<Object> createOpportunity(@RequestBody OpportunityCreateDto oppDto, @PathVariable Long userId) {
    try {
      return ResponseEntity.ok(opportunityService.createOpportunity(oppDto, userId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  }

  // Edit an opportunity
  @PutMapping("/{oppId}")
  public ResponseEntity<Object> editOpportunity(@RequestBody OpportunityCreateDto oppDto, @PathVariable Long oppId) {
    try {
      return ResponseEntity.ok(opportunityService.editOpportunity(oppDto, oppId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
  }

  // Delete an opportunity
  @DeleteMapping("/{oppId}")
  public ResponseEntity<Object> deleteOpportunity(@PathVariable Long oppId) {
    try {
      return ResponseEntity.ok(opportunityService.deleteOpportunity(oppId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
  }

  @PostMapping("/turnToJob/{oppId}")
  public ResponseEntity<Object> turnOpportunityIntoJob(@PathVariable Long oppId) {
    try {
      return ResponseEntity.ok(opportunityService.turnOpportunityToJob(oppId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
  }
}
