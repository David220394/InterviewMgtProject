package com.accenture.interviewproj.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.entities.CandidateExperience;
import com.accenture.interviewproj.entities.Skill;
import com.accenture.interviewproj.exception.IdNotFoundException;
import com.accenture.interviewproj.repositories.CandidateExperienceRepository;
import com.accenture.interviewproj.repositories.CandidateRepository;
import com.accenture.interviewproj.repositories.SkillRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private CandidateExperienceRepository candidateExperienceRepository;
	
	public Candidate findCandidateById(Long id) throws IdNotFoundException {
		Candidate candidate = candidateRepository.getOne(id);
		if(candidate != null) {
		return candidate;
		}else {
			throw new IdNotFoundException("Candidate ID not Found");
		}
	}
	
	public List<Candidate> findCandidateByJobId(Long jobId){
		return candidateRepository.findByJobId(jobId);
	}
	
	public void createCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}
	
	@PostConstruct
	public void init() {
		if(candidateRepository.findAll().isEmpty()) {
		Candidate candidate = new Candidate();
		candidate.setCandidateName("Brandon");
		candidate.setCandidateAddress("Beau Bassin");
		candidate.setAvailability(true);
		candidate.setEmail("brandondavid220394@gmail.com");
		candidate.setCoverLetter("Dear .. . .");
		Set<Long> phones = new HashSet<>();
		phones.add((long) 57565279);
		candidate.setCandidatePhones(phones);
		candidateRepository.save(candidate);

		Skill skill = new Skill();
		skill.setDescription("HSC");
		skill.setGrade(19.0);
		skill.setLocation("St Andrew's School");
		skill.setCandidate(candidate);
		skillRepository.save(skill);
		
		CandidateExperience candidateExperience = new CandidateExperience();
		candidateExperience.setExperienceName("Java");
		candidateExperience.setDuration(2);
		candidateExperience.setCandidate(candidate);
		candidateExperienceRepository.save(candidateExperience);
		
		}
		
	}

}
