package com.accenture.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.dao.CandidateRep;
import com.accenture.entity.Candidate;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRep repo;
	
	public List<Candidate> findall (){
		return repo.findAll();
	}
	
	public List<Candidate> findAllByJobId (String jobId){
		return repo.findByJobId(jobId);
	}
	
	
	@PostConstruct
	public void init() {
		Candidate c = new Candidate();
		c.setId("101");
		c.setJobId("AB000");
		c.setName("a");
		c.setPicture("unknown");
		c.setEducation("NaN");
		c.setRating(3.0);
		c.setTitle("don");
		repo.save(c);
		
		Candidate c1 = new Candidate();
		c1.setId("202");
		c1.setJobId("AB111");
		c1.setName("b");
		c1.setPicture("unknown");
		c1.setEducation("NaN");
		c1.setRating(3.0);
		c1.setTitle("don");
		repo.save(c1);
		
		Candidate c2 = new Candidate();
		c2.setId("303");
		c2.setJobId("AB000");
		c2.setName("c");
		c2.setPicture("unknown");
		c2.setEducation("NaN");
		c2.setRating(3.0);
		c2.setTitle("don");
		repo.save(c2);
		
		
	}
	
	

}
