package com.accenture.interviewproj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.interviewproj.entities.HRInterview;
import com.accenture.interviewproj.entities.Interview;

public interface HRInterviewRepository extends JpaRepository<HRInterview, Long> {
	
	HRInterview findByLink(String link);

	
}
