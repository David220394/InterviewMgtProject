package com.accenture.interviewproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.interviewproj.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
