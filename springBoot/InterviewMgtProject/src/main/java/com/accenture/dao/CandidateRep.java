package com.accenture.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.entity.Candidate;


public interface CandidateRep extends JpaRepository<Candidate, String> {
	
	List<Candidate> findByJobId(String jobId);
	
	

}
