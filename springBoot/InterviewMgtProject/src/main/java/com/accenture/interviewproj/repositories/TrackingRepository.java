package com.accenture.interviewproj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.interviewproj.entities.Tracking;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
	
	@Query(value="SELECT * FROM TABLE_TRACKING WHERE CANDIDATE_ID = ?", nativeQuery=true)
	public List<Tracking> findByCandidateId(Long candidateId);

}
