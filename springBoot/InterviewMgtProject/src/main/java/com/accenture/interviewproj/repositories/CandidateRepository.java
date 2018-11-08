package com.accenture.interviewproj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.interviewproj.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	@Query(value="SELECT tc.* FROM TABLE_CANDIDATE tc LEFT JOIN TABLE_JOB_CANDIDATE tjc ON tc.CANDIDATE_ID = tjc.CANDIDATE_ID WHERE tjc.JOB_ID=?", nativeQuery=true)
	List<Candidate> findByJobId(Long jobId);
	
	@Query(value="SELECT tc.* FROM TABLE_CANDIDATE tc LEFT JOIN TABLE_JOB_CANDIDATE tjc ON tc.CANDIDATE_ID = tjc.CANDIDATE_ID WHERE tjc.JOB_ID=? AND tc.CANDIDATE_ID=?", nativeQuery=true)
	Candidate findByJobIdAndCandidateId(Long jobId, Long cid);
	
	@Query(value="SELECT * FROM TABLE_CANDIDATE tc LEFT JOIN TABLE_JOB_CANDIDATE tjc ON tc.CANDIDATE_ID = tjc.CANDIDATE_ID WHERE tjc.JOB_ID=? AND tc.CANDIDATE_NAME=?", nativeQuery=true)
	List<Candidate> findByJobIdAndCandidateName(Long jobId, String candidateName);

	Candidate findByCandidateName(String candidateName);
}
