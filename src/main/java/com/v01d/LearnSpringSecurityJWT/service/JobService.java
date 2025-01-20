package com.v01d.LearnSpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.LearnSpringSecurityJWT.dto.JobCreateDto;
import com.v01d.LearnSpringSecurityJWT.model.Job;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.repository.JobRepository;
import com.v01d.LearnSpringSecurityJWT.repository.UserRepository;

@Service
public class JobService {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private UserRepository userRepository;

  // Get all opportunities
  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  // Get oppportunity by Id
  public Job getJobById(Long id) throws Exception {
    Optional<Job> Optjob = jobRepository.findById(id);

    if (Optjob.isPresent()) {
      return Optjob.get();
    }
    throw new Exception("Could not find the job with the id " + id);
  }

  // Get job by User
  public List<Job> getUsersJobs(Long userId) throws Exception {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User foundUser = optionalUser.get();
      List<Job> usersJobs = foundUser.getJobs();
      return usersJobs;
    } else {
      throw new Exception("Could not find the user of id " + userId);
    }
  }

  // Create a new Job
  public Job createJob(JobCreateDto jobCreateDto, Long userId) throws Exception {

    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User foundUser = optionalUser.get();
      Job createdJob = new Job();

      createdJob.setCompanyName(jobCreateDto.getCompanyName());
      createdJob.setCity(jobCreateDto.getCity());
      createdJob.setEmail(jobCreateDto.getEmail());
      createdJob.setPhone(jobCreateDto.getPhone());
      createdJob.setJobType(jobCreateDto.getJobType());
      createdJob.setPostName(jobCreateDto.getPostName());
      createdJob.setDescription(jobCreateDto.getDescription());
      createdJob.setCreator(foundUser);

      return jobRepository.save(createdJob);

    } else {
      throw new Exception("Could not find the user of id " + userId);
    }

  }

  // Edit an job
  public Job editJob(JobCreateDto jobCreateDto, Long oppId) throws Exception {

    Optional<Job> optionalJob = jobRepository.findById(oppId);

    if (optionalJob.isPresent()) {
      Job foundJob = optionalJob.get();

      foundJob.setCompanyName(jobCreateDto.getCompanyName());
      foundJob.setCity(jobCreateDto.getCity());
      foundJob.setEmail(jobCreateDto.getEmail());
      foundJob.setPhone(jobCreateDto.getPhone());
      foundJob.setJobType(jobCreateDto.getJobType());
      foundJob.setPostName(jobCreateDto.getPostName());
      foundJob.setDescription(jobCreateDto.getDescription());


      return jobRepository.save(foundJob);

    } else {
      throw new Exception("Could not find the job of id " + oppId);
    }

  }

  // Delete an job
  public String deleteJob(Long jobId) throws Exception {
    Optional<Job> optionalJob = jobRepository.findById(jobId);

    if (optionalJob.isPresent()) {
      jobRepository.deleteById(jobId);
      return "Job deleted successfully";
    } else {
      throw new Exception("Could not find the job of id " + jobId);
    }

  }
}
