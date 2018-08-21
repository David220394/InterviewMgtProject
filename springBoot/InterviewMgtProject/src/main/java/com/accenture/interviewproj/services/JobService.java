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

	public Job insertJob(Job job) throws JobNameAlreadyExistsException {
		if(jobRepository.findByJobName(job.getJobName()) == null) {
			return jobRepository.save(job);
		}else {
			throw new JobNameAlreadyExistsException("This job name already exists");
		}	
	}
	
	public Job searchByJobId(Long jobId) {
		return jobRepository.findByJobId(jobId);
	}
	
	public Job searchByJobName(String jobName) {
		return jobRepository.findByJobName(jobName);
	}
	
	public List<Job> displayListOfJobs(){
		return jobRepository.findAll();
	}
	
	public void deleteJob(String jobName) throws JobNotFoundException {
		 Job findJob = jobRepository.findByJobName(jobName);
		if( findJob != null) {
			jobRepository.delete(findJob);
			return;
		}else {
			throw new JobNotFoundException("Delete failed. No job found with the job Name:" + jobName);
		}
	}
	
	public Job updateJob(Job job) throws JobNotFoundException {
		Job searchJob = jobRepository.findByJobId(job.getJobId());
		if(searchJob != null) {
			return jobRepository.save(job);
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}

}
