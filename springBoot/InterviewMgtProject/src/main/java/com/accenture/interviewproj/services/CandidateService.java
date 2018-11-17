package com.accenture.interviewproj.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.dtos.DisplayCandidateDto;
import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.dtos.QuizDto;
import com.accenture.interviewproj.entities.AssessmentQuiz;
import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.entities.CandidateExperience;
import com.accenture.interviewproj.entities.CandidateSkillScore;
import com.accenture.interviewproj.entities.Education;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.entities.JobCandidate;
import com.accenture.interviewproj.entities.Requirement;
import com.accenture.interviewproj.entities.Skill;
import com.accenture.interviewproj.entities.Status;
import com.accenture.interviewproj.enums.Gender;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.exceptions.JobNameAlreadyExistsException;
import com.accenture.interviewproj.repositories.CandidateExperienceRepository;
import com.accenture.interviewproj.repositories.CandidateRepository;
import com.accenture.interviewproj.repositories.CandidateSkillScoreRepository;
import com.accenture.interviewproj.repositories.EducationRepository;
import com.accenture.interviewproj.repositories.JobCandidateRepository;
import com.accenture.interviewproj.repositories.JobsRepository;
import com.accenture.interviewproj.repositories.RequirementRepository;
import com.accenture.interviewproj.repositories.SkillRepository;
import com.accenture.interviewproj.repositories.StatusRepository;
import com.accenture.interviewproj.utilities.CandidateUtility;
import com.accenture.interviewproj.utilities.JobUtility;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private CandidateExperienceRepository candidateExperienceRepository;
	
	@Autowired
	private JobsRepository jobRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private RequirementRepository requirementRepository;
	
	@Autowired
	private JobCandidateRepository jobCandidateRepository;
	
	@Autowired
	private AssessmentQuizService assessmentQuizService;
	
	@Autowired
	private CandidateSkillScoreRepository candidateSkillScoreRepository;
	

	public Candidate createCandidate(MultipartFile candidateFile) {
		try {
			Workbook workbook = WorkbookFactory.create(candidateFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			if(!rowIterator.hasNext()) {
				return null;
			}
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				CandidateDto candidateDto = CandidateUtility.getCandidateFromExcel(row);
				createCandidateFromDto(candidateDto);
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) { }
		
		return null;
	}
	
	public Candidate createCandidateFromDto(CandidateDto candidateDto) {
		Candidate candidate = null;
		Job job =null;
		
		if(candidateRepository.findByCandidateName(candidateDto.getCandidateName()) == null) {
			Set<Skill> skills = new HashSet<>();
			for (String skillValue : candidateDto.getSkills()) {
				Skill skill = skillRepository.findByDescription(skillValue);
				if(skill == null) {
					skill = new Skill();
					skill.setDescription(skillValue);
					skillRepository.save(skill);
				}
				skills.add(skill);
			}
			
			candidate = CandidateUtility.convertCandidateDtoToCandidate(candidateDto);
			candidate.setSkills(skills);
			candidateRepository.save(candidate);
			if(candidateDto.getJobName() != null) {
				job = jobRepository.findByJobName(candidateDto.getJobName());
				
				JobCandidate jobCandidate = new JobCandidate();
				jobCandidate.setCandidate(candidate);
				jobCandidate.setJob(job);
				jobCandidate.setScore(0);
				jobCandidateRepository.save(jobCandidate);
				
				Status status = new Status();
				status.setStatusName(candidateDto.getStatus());
				status.setCandidate(jobCandidate);
				statusRepository.save(status);
			}
			Education education = new Education();
			education.setInstitutionName(candidateDto.getInstitution());
			education.setProgramStudy(candidateDto.getProgramStudy());
			education.setGrade(candidateDto.getGrade());
			education.setCandidate(candidate);
			educationRepository.save(education);
			
			CandidateExperience candidateExperience = new CandidateExperience();
			candidateExperience.setExperienceName(candidateDto.getLastJobRole());
			candidateExperience.setSpecialty(candidateDto.getSpecialty());
			candidateExperience.setLocation(candidateDto.getEmployer());
			candidateExperience.setCandidate(candidate);
			candidateExperienceRepository.save(candidateExperience);
			
			List <Job> jobs = jobRepository.findAll();
			for (Job job2 : jobs) {
				CandidateSkillScore candidateSkillScore = new CandidateSkillScore();
				candidateSkillScore.setCandidate(candidate);
				candidateSkillScore.setJob(job2);
				candidateSkillScoreRepository.save(candidateSkillScore);
			}
		}
		return candidate;
	}
	
	/**
	 * 
	 * @param cid
	 * @throws IdNotFoundException
	 * find a candidate by candidate id
	 */
	public Candidate findCandidateByCandidateId(Long cid) throws IdNotFoundException {
		if( candidateRepository.findById(cid).isPresent()) {
		return  candidateRepository.findById(cid).get();
		}else {
			throw new IdNotFoundException("Candidate ID not Found For this Job");
		}
	}
	
	/**
	 * 
	 * Find all candidate
	 */
	public List<Candidate> findAll(){
		
		return candidateRepository.findAll();
	}
	

	/**
	 * 
	 * @param jobId
	 * @param cid
	 * @throws IdNotFoundException
	 * find a candidate by job id and candidate id
	 */
	public DisplayCandidateDto findCandidateByJobIdAndCandidateId(Long jobId, Long cid) throws IdNotFoundException {
		Candidate candidate = candidateRepository.findByJobIdAndCandidateId(jobId,cid);
		if(candidate != null) {
				DisplayCandidateDto candidateDto = CandidateUtility.convertCandidateToDto(candidate);
				candidateDto.setStatus(jobCandidateRepository.findStatusByCandidateIdAndJobId(cid, jobId).get(0));
				candidateDto.setScore(jobCandidateRepository.findScoreByCandidateIdAndJobId(cid, jobId).get(0));
				candidateDto.setJobName(jobCandidateRepository.findJobNameByCandidateIdAndJobId(cid, jobId));
				candidateDto.setSkillScore(candidateSkillScoreRepository.findCandidateSkillScoreByJobIdAndCandidateId(jobId, cid));
		return candidateDto;
		}else {
			throw new IdNotFoundException("Candidate ID not Found For this Job");
		}
	}
	
	/**
	 * 
	 * @param jobId
	 * Find a candidate by job id
	 */
	public List<DisplayCandidateDto> findCandidateByJobId(Long jobId){
		List<DisplayCandidateDto> candidateDtos = new ArrayList<>();
		List<Candidate> candidates = candidateRepository.findByJobId(jobId);
		for (Candidate candidate : candidates) {
			DisplayCandidateDto candidateDto = CandidateUtility.convertCandidateToDto(candidate);
			candidateDto.setStatus(jobCandidateRepository.findStatusByCandidateIdAndJobId(candidate.getCandidateId(), jobId).get(0));
			candidateDto.setScore(jobCandidateRepository.findScoreByCandidateIdAndJobId(candidate.getCandidateId(), jobId).get(0));
			candidateDto.setJobName(jobCandidateRepository.findJobNameByCandidateIdAndJobId(candidate.getCandidateId(), jobId));
			candidateDto.setSkillScore(candidateSkillScoreRepository.findCandidateSkillScoreByJobIdAndCandidateId(jobId, candidate.getCandidateId()));
			candidateDtos.add(candidateDto);
		}
		return candidateDtos;
	}
	
	
	public Candidate addJobToCandidate(Long cid, Long jobId) throws JobNameAlreadyExistsException {
		if(candidateRepository.findByJobIdAndCandidateId(jobId, cid) == null) {	
		Job job = jobRepository.findByJobId(jobId);
		Candidate candidate = candidateRepository.findById(cid).get();
		JobCandidate jobCandidate = new JobCandidate();
		jobCandidate.setCandidate(candidate);
		jobCandidate.setJob(job);
		jobCandidate.setScore(0);
		jobCandidateRepository.save(jobCandidate);
		
		Status status = new Status();
		status.setStatusName("Applied");
		status.setCandidate(jobCandidate);
		statusRepository.save(status);
		
		return candidate;
		}else {
			throw new JobNameAlreadyExistsException("This candidate is already assigned to the job");
		}
	}
	
	
	/**
	 * Change status of candidate
	 */
	public Status updateCandidateStatus(long candidateId, long jobId, String is_change, String oldStatus) {
		List<String> status = new ArrayList<>();
		Status savedStatus=null;
		status.add("Applied");
		status.add("Scheduled Interview");
		status.add("Interview In progress");
		status.add("Completed");
		status.add("Rejected");
		
		if(!is_change.equals("NOTHING")) {
			Candidate candidate = candidateRepository.findById(candidateId).get();
			Job job = jobRepository.findByJobId(jobId);
			JobCandidate jobCandidate = jobCandidateRepository.findByCandidateAndJob(candidate, job);
			String newStatus;
			if(is_change.equals("CHANGE")) {
				newStatus = status.get(status.indexOf(oldStatus)+1);
			}else {
				newStatus = "Rejected";
			}
			savedStatus = new Status();
			savedStatus.setStatusName(newStatus);
			savedStatus.setCandidate(jobCandidate);
			return statusRepository.save(savedStatus);
		}
		return savedStatus;
	}
	
	
	
		
	/**
	 * Details for job and candidate
	 */
	@PostConstruct
	public void init() {
		if(candidateRepository.findAll().isEmpty()) {
			
		Set<Requirement> requirements = new HashSet<>();	
		Set<Requirement> requirements1 = new HashSet<>();
		Requirement requirement = new Requirement();
		requirement.setName("Java");
		requirementRepository.save(requirement);
		requirements.add(requirement);
		requirements1.add(requirement);
		
		Requirement requirement1 = new Requirement();
		requirement1.setName(".NET");
		requirementRepository.save(requirement1);
		requirements.add(requirement1);
		
		Requirement requirement2 = new Requirement();
		requirement2.setName("Shell Scripting");
		requirementRepository.save(requirement2);
		requirements1.add(requirement2);
		AssessmentQuiz assessmentQuiz = null;
		try {
			FileInputStream file = new FileInputStream("Test1.xlsx");
			List<QuestionDto> dtos = JobUtility.convertExcelToQuestionDto(file);
			QuizDto quizDto = new QuizDto();
			quizDto.setQuizName("Test1");
			quizDto.setQuestions(dtos);
			assessmentQuiz = assessmentQuizService.insertAssessmentQuiz(quizDto);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			
		}

		Job job = new Job();
		job.setActiveJob(true);
		job.setJobName("Accenture Academy");
		job.setLocation("NexTracom");
		job.setAssessmentQuiz(assessmentQuiz);
		job.setPosition("Java Developer");
		job.setRequirements(requirements);
		jobRepository.save(job);

		Job job1 = new Job();
		job1.setActiveJob(true);
		job1.setJobName("SAP Development");
		job1.setLocation("NexTracom");
		job1.setAssessmentQuiz(assessmentQuiz);
		job1.setPosition("SAP Developer");
		job1.setRequirements(requirements1);
		jobRepository.save(job1);
		
		Set<Skill> skills = new HashSet<>();
		Set<Skill> skills1 = new HashSet<>();
		Skill skill = new Skill();
		skill.setDescription("Java");
		skillRepository.save(skill);
		skills.add(skill);
		skills1.add(skill);
		
		Skill skill1 = new Skill();
		skill1.setDescription(".NET");
		skillRepository.save(skill1);
		skills.add(skill1);
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("Brandon");
		candidate.setCandidateAddress("Beau Bassin");
		candidate.setAvailability(true);
		candidate.setEmail("brandondavid220394@gmail.com");
		candidate.setCoverLetter("Dear .. . .");
		candidate.setCandidatePhone((long) 57565279);
		candidate.setSkills(skills);
		candidate.setDob(LocalDate.of(1994, 3, 22));
		candidate.setGender(Gender.MALE);
		candidate.setCompleteApplication(false);
		candidate.setInternalApplication(false);
		candidate.setRehire(false);
		candidateRepository.save(candidate);
		
		Candidate candidate1 = new Candidate();
		candidate1.setCandidateName("Hemanta");
		candidate1.setCandidateAddress("Triolet");
		candidate1.setAvailability(true);
		candidate1.setEmail("hemantahuril220394@gmail.com");
		candidate1.setCoverLetter("Hi .. . .");
		candidate1.setSkills(skills);
		candidate1.setCandidatePhone((long) 5754363);
		candidate1.setDob(LocalDate.of(1994, 3, 22));
		candidate1.setGender(Gender.FEMALE);
		candidate1.setCompleteApplication(true);
		candidate1.setInternalApplication(false);
		candidate1.setRehire(true);
		candidateRepository.save(candidate1);
		
		Candidate candidate2 = new Candidate();
		candidate2.setCandidateName("Khaushik");
		candidate2.setCandidateAddress("Pheonix");
		candidate2.setAvailability(true);
		candidate2.setEmail("l.jankee@gmail.com");
		candidate2.setCoverLetter("Dear .. . .");
		candidate2.setSkills(skills1);
		candidate2.setCandidatePhone((long) 5723452);
		candidate2.setDob(LocalDate.of(1994, 3, 22));
		candidate2.setGender(Gender.MALE);
		candidate2.setCompleteApplication(true);
		candidate2.setInternalApplication(true);
		candidate2.setRehire(false);
		candidateRepository.save(candidate2);
		
		List <Job> jobs = jobRepository.findAll();
		
		JobCandidate jobCandidate = new JobCandidate();
		jobCandidate.setCandidate(candidate);
		jobCandidate.setJob(job);
		jobCandidate.setScore(0);
		jobCandidateRepository.save(jobCandidate);
		
		for (Job job2 : jobs) {
			CandidateSkillScore candidateSkillScore = new CandidateSkillScore();
			candidateSkillScore.setCandidate(candidate);
			candidateSkillScore.setJob(job2);
			candidateSkillScoreRepository.save(candidateSkillScore);
		}
		
		JobCandidate jobCandidate1 = new JobCandidate();
		jobCandidate1.setCandidate(candidate1);
		jobCandidate1.setJob(job);
		jobCandidate1.setScore(0);
		jobCandidateRepository.save(jobCandidate1);
		
		for (Job job2 : jobs) {
			CandidateSkillScore candidateSkillScore = new CandidateSkillScore();
			candidateSkillScore.setCandidate(candidate1);
			candidateSkillScore.setJob(job2);
			candidateSkillScoreRepository.save(candidateSkillScore);
		}
		
		JobCandidate jobCandidate2 = new JobCandidate();
		jobCandidate2.setCandidate(candidate2);
		jobCandidate2.setJob(job1);
		jobCandidate2.setScore(0);
		jobCandidateRepository.save(jobCandidate2);

		for (Job job2 : jobs) {
			CandidateSkillScore candidateSkillScore = new CandidateSkillScore();
			candidateSkillScore.setCandidate(candidate2);
			candidateSkillScore.setJob(job2);
			candidateSkillScoreRepository.save(candidateSkillScore);
		}
		
		Status status = new Status();
		status.setStatusName("Applied");
		status.setCandidate(jobCandidate);
		statusRepository.save(status);
		
		Status status1 = new Status();
		status1.setStatusName("Applied");
		status1.setCandidate(jobCandidate1);
		statusRepository.save(status1);
		
		Status status2 = new Status();
		status2.setStatusName("Applied");
		status2.setCandidate(jobCandidate2);
		statusRepository.save(status2);
		
		Education education = new Education();
		education.setInstitutionName("UOM");
		education.setProgramStudy("Mtx");
		education.setGrade(69.7);
		education.setCandidate(candidate);
		educationRepository.save(education);
		
		Education education1 = new Education();
		education1.setInstitutionName("Pune");
		education1.setProgramStudy("BSc Electronics");
		education1.setGrade(69.7);
		education1.setCandidate(candidate1);
		educationRepository.save(education1);
		
		Education education2 = new Education();
		education2.setInstitutionName("China Unicersity");
		education2.setProgramStudy("MBa");
		education2.setGrade(69.7);
		education2.setCandidate(candidate2);
		educationRepository.save(education2);
		
		CandidateExperience candidateExperience = new CandidateExperience();
		candidateExperience.setExperienceName("Trainee Engineer");
		candidateExperience.setSpecialty("Mechanical And Electronics");
		candidateExperience.setLocation("CMT");
		candidateExperience.setCandidate(candidate);
		candidateExperienceRepository.save(candidateExperience);
		
		CandidateExperience candidateExperience1 = new CandidateExperience();
		candidateExperience1.setExperienceName("Trainee");
		candidateExperience1.setSpecialty("Electronics");
		candidateExperience1.setLocation("Linkopia");
		candidateExperience1.setCandidate(candidate1);
		candidateExperienceRepository.save(candidateExperience1);
		
		CandidateExperience candidateExperience2 = new CandidateExperience();
		candidateExperience2.setExperienceName("Trainee");
		candidateExperience2.setSpecialty("Aviation");
		candidateExperience2.setLocation("SSR Airport");
		candidateExperience2.setCandidate(candidate2);
		candidateExperienceRepository.save(candidateExperience2);

		
		}
		
	}

}
