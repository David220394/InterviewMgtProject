package com.accenture.interviewproj.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.entities.CandidateExperience;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.entities.Skill;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.repositories.CandidateExperienceRepository;
import com.accenture.interviewproj.repositories.CandidateRepository;
import com.accenture.interviewproj.repositories.JobRepository;
import com.accenture.interviewproj.repositories.SkillRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private CandidateExperienceRepository candidateExperienceRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	public Candidate findCandidateByJobIdAndCandidateId(Long jobId, Long cid) throws IdNotFoundException {
		Candidate candidate = candidateRepository.findByJobIdAndCandidateId(jobId,cid);
		if(candidate != null) {
		return candidate;
		}else {
			throw new IdNotFoundException("Candidate ID not Found For this Job");
		}
	}
	
	public List<Candidate> findCandidateByJobId(Long jobId){
		return candidateRepository.findByJobId(jobId);
	}
	
	public void insertCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}
	
	@PostConstruct
	public void init() {
		if(candidateRepository.findAll().isEmpty()) {
			
		Job job = new Job();
		job.setActiveJob(true);
		job.setJobName("Accenture Academy");
		job.setField("Technology");
		job.setLocation("NexTracom");
		job.setNoOfVacancy(4);
		job.setPosition("Java Developer");
		jobRepository.save(job);
		
		Job job1 = new Job();
		job1.setActiveJob(true);
		job1.setJobName("SAP Development");
		job1.setField("Technology");
		job1.setLocation("NexTracom");
		job1.setNoOfVacancy(3);
		job1.setPosition("SAP Developer");
		jobRepository.save(job1);
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("Brandon");
		candidate.setCandidateAddress("Beau Bassin");
		candidate.setAvailability(true);
		candidate.setEmail("brandondavid220394@gmail.com");
		candidate.setCoverLetter("Dear .. . .");
		Set<Long> phones = new HashSet<>();
		phones.add((long) 57565279);
		candidate.setCandidatePhones(phones);
		candidate.setJob(job);
		candidateRepository.save(candidate);
		
		Candidate candidate1 = new Candidate();
		candidate1.setCandidateName("Hemanta");
		candidate1.setCandidateAddress("Triolet");
		candidate1.setAvailability(true);
		candidate1.setEmail("hemantahuril220394@gmail.com");
		candidate1.setCoverLetter("Hi .. . .");
		Set<Long> phones1 = new HashSet<>();
		phones1.add((long) 5754363);
		candidate1.setJob(job);
		candidate1.setCandidatePhones(phones1);
		candidateRepository.save(candidate1);
		
		Candidate candidate2 = new Candidate();
		candidate2.setCandidateName("Khaushik");
		candidate2.setCandidateAddress("Pheonix");
		candidate2.setAvailability(true);
		candidate2.setEmail("l.jankee@gmail.com");
		candidate2.setCoverLetter("Dear .. . .");
		Set<Long> phones2 = new HashSet<>();
		phones2.add((long) 5723452);
		candidate2.setJob(job1);
		candidate2.setCandidatePhones(phones2);
		candidateRepository.save(candidate2);

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
