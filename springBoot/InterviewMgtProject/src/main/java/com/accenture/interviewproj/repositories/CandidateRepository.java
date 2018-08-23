package com.accenture.interviewproj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.interviewproj.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	@Query(value="SELECT * FROM TABLE_CANDIDATE WHERE JOB_ID=?", nativeQuery=true)
	List<Candidate> findByJobId(Long jobId);
	
	@Query(value="SELECT * FROM TABLE_CANDIDATE WHERE JOB_ID=? AND CANDIDATE_ID=?", nativeQuery=true)
	Candidate findByJobIdAndCandidateId(Long jobId, Long cid);

}
