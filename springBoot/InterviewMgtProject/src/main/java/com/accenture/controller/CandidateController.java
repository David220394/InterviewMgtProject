package com.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.Candidate;
import com.accenture.service.CandidateService;

@RestController
@RequestMapping("/interviews")
@CrossOrigin("*")
public class CandidateController {
	
	@Autowired
	private CandidateService service;
	
	@GetMapping("/all")	
	public List<Candidate> findall(){
		return service.findall();		
	}
	
	@GetMapping("/")	
	public List<Candidate> findAllByJobId(@RequestParam String jobid){
		return service.findAllByJobId(jobid);		
	}
	
	
	

}
