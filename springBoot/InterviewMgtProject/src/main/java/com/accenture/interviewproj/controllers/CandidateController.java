package com.accenture.interviewproj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.exception.IdNotFoundException;
import com.accenture.interviewproj.service.CandidateService;
import com.accenture.interviewproj.utilities.CandidateUtility;

@CrossOrigin
@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findCandidateById(@PathVariable Long id){
		try {
			CandidateDto candidateDto = CandidateUtility.convertCandidateToDto(candidateService.findCandidateById(id));
			return ResponseEntity.ok(candidateDto);
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
