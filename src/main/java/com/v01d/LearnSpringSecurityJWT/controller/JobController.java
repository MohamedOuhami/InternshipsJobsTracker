package com.v01d.LearnSpringSecurityJWT.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.LearnSpringSecurityJWT.dto.JobCreateDto;
import com.v01d.LearnSpringSecurityJWT.model.Job;
import com.v01d.LearnSpringSecurityJWT.service.JobService;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

  @Autowired
  private JobService jobService;

  // Get all the jobortunities
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<Job>> getAlljobortunities() {
    try {
      List<Job> allJobs = jobService.getAllJobs();

      return ResponseEntity.ok(allJobs);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // Get job by Id
  @GetMapping("/{id}")
  public ResponseEntity<Object> getJobById(@PathVariable Long id) {
    try {
      Job foundJob = jobService.getJobById(id);

      return ResponseEntity.ok(foundJob);
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
  }

  // Get jobortunities By User
  @GetMapping("/byUser/{userId}")
  public ResponseEntity<Object> getjobortunitiesByUser(@PathVariable Long userId) {
    try {
      List<Job> foundjobortunities = jobService.getUsersJobs(userId);

      return ResponseEntity.ok(foundjobortunities);
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
  }

  // Create a new Job
  @PostMapping("/{userId}")
  public ResponseEntity<Object> createJob(@RequestBody JobCreateDto jobDto, @PathVariable Long userId) {
    try {
      return ResponseEntity.ok(jobService.createJob(jobDto, userId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  }

  // Edit an job
  @PutMapping("/{jobId}")
  public ResponseEntity<Object> editJob(@RequestBody JobCreateDto jobDto, @PathVariable Long jobId) {
    try {
      return ResponseEntity.ok(jobService.editJob(jobDto, jobId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
  }

  // Delete an job
  @DeleteMapping("/{jobId}")
  public ResponseEntity<Object> deleteJob(@PathVariable Long jobId) {
    try {
      return ResponseEntity.ok(jobService.deleteJob(jobId));
    } catch (Exception ex) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());

      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
  }
}
