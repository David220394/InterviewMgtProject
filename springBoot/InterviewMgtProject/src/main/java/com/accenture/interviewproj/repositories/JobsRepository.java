package com.accenture.interviewproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.interviewproj.entities.Job;

public interface JobsRepository extends JpaRepository<Job, Long>{
	
	public Job findByJobId(Long jobId);
	
	public Job findByJobName(String jobName);

}
