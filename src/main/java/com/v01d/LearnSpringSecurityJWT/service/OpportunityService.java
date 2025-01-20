package com.v01d.LearnSpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.LearnSpringSecurityJWT.dto.OpportunityCreateDto;
import com.v01d.LearnSpringSecurityJWT.model.Job;
import com.v01d.LearnSpringSecurityJWT.model.Opportunity;
import com.v01d.LearnSpringSecurityJWT.model.User;
import com.v01d.LearnSpringSecurityJWT.repository.JobRepository;
import com.v01d.LearnSpringSecurityJWT.repository.OpportunityRepository;
import com.v01d.LearnSpringSecurityJWT.repository.UserRepository;

@Service
public class OpportunityService {

  @Autowired
  private OpportunityRepository opportunityRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JobRepository jobRepository;

  // Get all opportunities
  public List<Opportunity> getAllOpportunities() {
    return opportunityRepository.findAll();
  }

  // Get oppportunity by Id
  public Opportunity getOpportunityById(Long id) throws Exception {
    Optional<Opportunity> Optopportunity = opportunityRepository.findById(id);

    if (Optopportunity.isPresent()) {
      return Optopportunity.get();
    }
    throw new Exception("Could not find the opporunity with the id " + id);
  }

  // Get opportunity by User
  public List<Opportunity> getUsersOps(Long userId) throws Exception {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User foundUser = optionalUser.get();
      List<Opportunity> usersOps = foundUser.getOpportunities();
      return usersOps;
    } else {
      throw new Exception("Could not find the user of id " + userId);
    }
  }

  // Create a new Opportunity
  public Opportunity createOpportunity(OpportunityCreateDto oppCreateDto, Long userId) throws Exception {

    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User foundUser = optionalUser.get();
      Opportunity createdOpp = new Opportunity();

      createdOpp.setCity(oppCreateDto.getCity());
      createdOpp.setEmail(oppCreateDto.getEmail());
      createdOpp.setPhone(oppCreateDto.getPhone());
      createdOpp.setStatus("Posted");
      createdOpp.setJobType(oppCreateDto.getJobType());
      createdOpp.setPostName(oppCreateDto.getPostName());
      createdOpp.setCompanyName(oppCreateDto.getCompanyName());
      createdOpp.setCreator(foundUser);

      return opportunityRepository.save(createdOpp);

    } else {
      throw new Exception("Could not find the user of id " + userId);
    }

  }

  // Edit an opportunity
  public Opportunity editOpportunity(OpportunityCreateDto oppCreateDto, Long oppId) throws Exception {

    Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(oppId);

    if (optionalOpportunity.isPresent()) {
      Opportunity foundOpportunity = optionalOpportunity.get();

      foundOpportunity.setCity(oppCreateDto.getCity());
      foundOpportunity.setEmail(oppCreateDto.getEmail());
      foundOpportunity.setPhone(oppCreateDto.getPhone());
      foundOpportunity.setStatus(oppCreateDto.getStatus());
      foundOpportunity.setJobType(oppCreateDto.getJobType());
      foundOpportunity.setPostName(oppCreateDto.getPostName());
      foundOpportunity.setCompanyName(oppCreateDto.getCompanyName());

      return opportunityRepository.save(foundOpportunity);

    } else {
      throw new Exception("Could not find the opportunity of id " + oppId);
    }

  }

  // Delete an opportunity
  public String deleteOpportunity(Long oppId) throws Exception {
    Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(oppId);

    if (optionalOpportunity.isPresent()) {
      opportunityRepository.deleteById(oppId);
      return "Opportunity deleted successfully";
    } else {
      throw new Exception("Could not find the opportunity of id " + oppId);
    }

  }

  // Turn an opportunity into a Job
  public Job turnOpportunityToJob(Long oppId) throws Exception {
    Optional<Opportunity> optionalOpportunity = opportunityRepository.findById(oppId);

    if (optionalOpportunity.isPresent()) {
      Opportunity originalOpp = optionalOpportunity.get();
      Job createdJob = new Job();

      createdJob.setCompanyName(originalOpp.getCompanyName());
      createdJob.setCity(originalOpp.getCity());
      createdJob.setEmail(originalOpp.getEmail());
      createdJob.setPhone(originalOpp.getPhone());
      createdJob.setJobType(originalOpp.getJobType());
      createdJob.setPostName(originalOpp.getPostName());
      createdJob.setCreator(originalOpp.getCreator());

      // Delete the opportunity
      opportunityRepository.deleteById(originalOpp.getId());

      return jobRepository.save(createdJob); 


    } else {
      throw new Exception("Could not find the opportunity of id " + oppId);
    }

  }
}
