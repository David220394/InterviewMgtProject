package com.accenture.interviewproj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.services.CandidateService;
import com.accenture.interviewproj.utilities.CandidateUtility;

@CrossOrigin
@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping("/{jobId}")
	public ResponseEntity<?> findCandidateByJodId(@PathVariable Long jobId){
		List<CandidateDto> candidateDtos = new ArrayList<>();
		List<Candidate> cnadidates = candidateService.findCandidateByJobId(jobId);
		for (Candidate candidate : cnadidates) {
			candidateDtos.add(CandidateUtility.convertCandidateToDto(candidate));
		}
		return ResponseEntity.ok(candidateDtos);
	}
	
	@GetMapping("/{jobId}/{cid}")
	public ResponseEntity<?> findCandidateByJobIdAndId(@PathVariable Long jobId,@PathVariable Long cid){
		try {
			CandidateDto candidateDto = CandidateUtility.convertCandidateToDto(candidateService.findCandidateByJobIdAndCandidateId(jobId,cid));
			return ResponseEntity.ok(candidateDto);
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
