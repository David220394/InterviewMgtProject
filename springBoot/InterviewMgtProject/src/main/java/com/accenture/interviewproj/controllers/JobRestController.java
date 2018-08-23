package com.accenture.interviewproj.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.RequirementDto;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.entities.Requirement;
import com.accenture.interviewproj.exceptions.JobNameAlreadyExistsException;
import com.accenture.interviewproj.exceptions.JobNotFoundException;
import com.accenture.interviewproj.services.JobService;
import com.accenture.interviewproj.utilities.JobUtility;

@RestController
@RequestMapping("/jobs")
@CrossOrigin
public class JobRestController {

	private final JobService jobService;

	public JobRestController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/")
	public ResponseEntity<?> createJob(@RequestBody JobDto jobDto) {
		try {
			Set<RequirementDto> requirementDtos = jobDto.getRequirements();
			
			Job job = JobUtility.convertJobDtoToJob(jobDto);
			
			for(RequirementDto r: requirementDtos) {
				Requirement requirement = JobUtility.convertRequirementDtoToDto(r);
				requirement.setJob(job);
				job.getRequirements().add(requirement);
			}
			
			return ResponseEntity.ok(jobService.insertJob(job));
		} catch (JobNameAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Job name already exists");
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<?>> getAllJobs() {
		return ResponseEntity.ok(jobService.findAll());
	}

	@GetMapping("/{jobId}")
	public ResponseEntity<?> findJobByName(@PathVariable("jobId") Long jobId) {
		Job job = jobService.findByJobId(jobId);
		if (job != null) {
			return ResponseEntity.ok(job);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{jobName}")
	public HttpEntity<String> deleteIncident(@PathVariable("jobName") String jobName) {
		try {
			jobService.deleteJob(jobName);
			return ResponseEntity.ok("DELETED!!");
		} catch (JobNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/")
	public ResponseEntity<?> updateIncident(@RequestBody Job job) {
		try {
			jobService.updateJob(job);
			return ResponseEntity.ok("Updated");
		} catch (JobNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
