package com.accenture.interviewproj.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.exceptions.JobNameAlreadyExistsException;
import com.accenture.interviewproj.exceptions.JobNotFoundException;
import com.accenture.interviewproj.repositories.JobRepository;

@Service
public class JobService {
	
	private final JobRepository jobRepository;
	
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	/**
	 * 
	 * @param job
	 * @throws JobNameAlreadyExistsException
	 * Insert a job
	 */
	public Job insertJob(Job job) throws JobNameAlreadyExistsException {
		if(jobRepository.findByJobName(job.getJobName()) == null) {
			return jobRepository.save(job);
		}else {
			throw new JobNameAlreadyExistsException("This job name already exists");
		}	
	}
	
	/**
	 * 
	 * @param jobId
	 * Find a job by id
	 */
	public Job findByJobId(Long jobId) {
		return jobRepository.findByJobId(jobId);
	}
	
	/**
	 * 
	 * @param jobName
	 * Find a job by name
	 */
	public Job findByJobName(String jobName) {
		return jobRepository.findByJobName(jobName);
	}
	
	/**
	 * 
	 * Find all jobs
	 */
	public List<Job> findAll(){
		return jobRepository.findAll();
	}
	
	/**
	 * 
	 * @param jobName
	 * @throws JobNotFoundException
	 * Delete a job by its job name
	 */
	public void deleteJob(String jobName) throws JobNotFoundException {
		 Job findJob = jobRepository.findByJobName(jobName);
		if( findJob != null) {
			jobRepository.delete(findJob);
			return;
		}else {
			throw new JobNotFoundException("Delete failed. No job found with the job Name:" + jobName);
		}
	}
	
	/**
	 * 
	 * @param job
	 * @throws JobNotFoundException
	 * Update a job
	 * search the job by id first and update
	 */
	public Job updateJob(Job job) throws JobNotFoundException {
		Job searchJob = jobRepository.findByJobId(job.getJobId());
		if(searchJob != null) {
			return jobRepository.save(job);
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}
	
	
	public Job updateJob(Long jobId, byte[] assessmentFile) throws JobNotFoundException {
		Job searchJob = jobRepository.findByJobId(jobId);
		if(searchJob != null) {
			searchJob.setAssessmentFile(assessmentFile);
			return jobRepository.save(searchJob);
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}

}
