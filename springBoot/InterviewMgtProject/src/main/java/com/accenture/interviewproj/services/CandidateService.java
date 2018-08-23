package com.accenture.interviewproj.services;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.entities.CandidateExperience;
import com.accenture.interviewproj.entities.Education;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.entities.Skill;
import com.accenture.interviewproj.entities.Status;
import com.accenture.interviewproj.enums.Gender;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.repositories.CandidateExperienceRepository;
import com.accenture.interviewproj.repositories.CandidateRepository;
import com.accenture.interviewproj.repositories.EducationRepository;
import com.accenture.interviewproj.repositories.JobRepository;
import com.accenture.interviewproj.repositories.SkillRepository;
import com.accenture.interviewproj.repositories.StatusRepository;

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
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
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
		candidate.setCandidatePhone((long) 57565279);
		candidate.setJob(job);
		candidate.setDob(LocalDate.of(1994, 3, 22));
		candidate.setGender(Gender.MALE);
		candidate.setInternalApplication(false);
		candidate.setRehire(false);
		candidate.setScore(0);
		candidateRepository.save(candidate);
		
		Candidate candidate1 = new Candidate();
		candidate1.setCandidateName("Hemanta");
		candidate1.setCandidateAddress("Triolet");
		candidate1.setAvailability(true);
		candidate1.setEmail("hemantahuril220394@gmail.com");
		candidate1.setCoverLetter("Hi .. . .");
		candidate1.setJob(job);
		candidate1.setCandidatePhone((long) 5754363);
		candidate1.setDob(LocalDate.of(1994, 3, 22));
		candidate1.setGender(Gender.FEMALE);
		candidate1.setInternalApplication(false);
		candidate1.setRehire(false);
		candidate1.setScore(0);
		candidateRepository.save(candidate1);
		
		Candidate candidate2 = new Candidate();
		candidate2.setCandidateName("Khaushik");
		candidate2.setCandidateAddress("Pheonix");
		candidate2.setAvailability(true);
		candidate2.setEmail("l.jankee@gmail.com");
		candidate2.setCoverLetter("Dear .. . .");
		candidate2.setJob(job1);
		candidate2.setCandidatePhone((long) 5723452);
		candidate2.setDob(LocalDate.of(1994, 3, 22));
		candidate2.setGender(Gender.MALE);
		candidate2.setInternalApplication(false);
		candidate2.setRehire(false);
		candidate2.setScore(0);
		candidateRepository.save(candidate2);
		
		Status status = new Status();
		status.setStatusName("Applied");
		status.setCandidate(candidate);
		statusRepository.save(status);
		
		Status status1 = new Status();
		status1.setStatusName("Applied");
		status1.setCandidate(candidate1);
		statusRepository.save(status1);
		
		Status status2 = new Status();
		status2.setStatusName("Applied");
		status2.setCandidate(candidate2);
		statusRepository.save(status2);
		
		Education education = new Education();
		education.setInstitutionName("UOM");
		education.setProgramStrudy("Mtx");
		education.setGrade(69.7);
		education.setCandidate(candidate);
		educationRepository.save(education);
		
		Education education1 = new Education();
		education1.setInstitutionName("Pune");
		education1.setProgramStrudy("BSc Electronics");
		education1.setGrade(69.7);
		education1.setCandidate(candidate1);
		educationRepository.save(education1);
		
		Education education2 = new Education();
		education2.setInstitutionName("China Unicersity");
		education2.setProgramStrudy("MBa");
		education2.setGrade(69.7);
		education2.setCandidate(candidate2);
		educationRepository.save(education2);
		
		CandidateExperience candidateExperience = new CandidateExperience();
		candidateExperience.setExperienceName("Java");
		candidateExperience.setDuration(2);
		candidateExperience.setCandidate(candidate);
		candidateExperienceRepository.save(candidateExperience);
		
		CandidateExperience candidateExperience1 = new CandidateExperience();
		candidateExperience1.setExperienceName("Java");
		candidateExperience1.setDuration(1);
		candidateExperience1.setCandidate(candidate1);
		candidateExperienceRepository.save(candidateExperience1);
		
		CandidateExperience candidateExperience2 = new CandidateExperience();
		candidateExperience2.setExperienceName("SAP");
		candidateExperience2.setDuration(2);
		candidateExperience2.setCandidate(candidate2);
		candidateExperienceRepository.save(candidateExperience2);

		Skill skill = new Skill();
		skill.setDescription("HSC");
		skill.setGrade(19.0);
		skill.setLocation("St Andrew's School");
		skill.setCandidate(candidate);
		skillRepository.save(skill);
		}
		
	}

}
