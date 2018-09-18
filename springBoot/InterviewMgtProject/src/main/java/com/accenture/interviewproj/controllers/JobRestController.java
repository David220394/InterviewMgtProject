package com.accenture.interviewproj.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.exceptions.JobNameAlreadyExistsException;
import com.accenture.interviewproj.exceptions.JobNotFoundException;
import com.accenture.interviewproj.services.JobService;
import com.accenture.interviewproj.utilities.JobUtility;

@RestController
@RequestMapping("api/jobs")
@CrossOrigin
public class JobRestController {

	private final JobService jobService;

	public JobRestController(JobService jobService) {
		this.jobService = jobService;
	}

	/**
	 * 
	 * @param jobDto
	 * Creating a job
	 * checks if job already exist in database
	 */
	@PostMapping("/createJob")
	public ResponseEntity<?> createJob(@RequestBody JobDto jobDto) {
		try {
			
			Job job = JobUtility.convertJobDtoToJob(jobDto);
			return ResponseEntity.ok(jobService.insertJob(job));
		} catch (JobNameAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Job name already exists");
		}
	}
	
	/**
	 * 
	 * @param file
	 * @param jobId
	 * @throws JobNameAlreadyExistsException
	 * Upload the assessment file 
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("jobId") Long jobId) throws JobNameAlreadyExistsException {
		
		Job updatedJob;
		try {
			updatedJob = jobService.updateJob(jobId, file);
			return ResponseEntity.ok(updatedJob);
		} catch (JobNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Get the list of all jobs 
	 */
	@GetMapping("/")
	public ResponseEntity<List<?>> getAllJobs() {
		return ResponseEntity.ok(jobService.findAll());
	}
	
	/**
	 * 
	 * @param jobId
	 * Find a job by its id 
	 */
	@GetMapping("/{jobId}")
	public ResponseEntity<?> findJobByName(@PathVariable("jobId") Long jobId) {
		Job job = jobService.findByJobId(jobId);
		if (job != null) {
			return ResponseEntity.ok(job);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 
	 * @param jobName
	 * Delete a job by its name
	 */
	@DeleteMapping("/{jobName}")
	public HttpEntity<String> deleteJob(@PathVariable("jobName") String jobName) {
		try {
			jobService.deleteJob(jobName);
			return ResponseEntity.ok("DELETED!!");
		} catch (JobNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param job
	 * Update a job
	 */
	@PutMapping("/")
	public ResponseEntity<?> updateJob(@RequestBody Job job) {
		try {
			jobService.updateJob(job);
			return ResponseEntity.ok("Updated");
		} catch (JobNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/quiz/{jobId}")
	public ResponseEntity<?> findQuiz(@PathVariable("jobId") long jobId){
		try {
			List<QuestionDto> questionDtos = jobService.findQuiz(jobId);
			return ResponseEntity.ok(questionDtos);
		} catch (EncryptedDocumentException | InvalidFormatException | JobNotFoundException | IOException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
