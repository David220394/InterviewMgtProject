package com.accenture.interviewproj.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.CandidateDto;
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
import com.accenture.interviewproj.repositories.JobsRepository;
import com.accenture.interviewproj.repositories.SkillRepository;
import com.accenture.interviewproj.repositories.StatusRepository;
import com.accenture.interviewproj.utilities.CandidateUtility;

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
		if(candidateRepository.findByJobIdAndCandidateName(candidateDto.getJobId(), candidateDto.getCandidateName()) == null) {
		Job job = jobRepository.findByJobId(candidateDto.getJobId());
		candidate = CandidateUtility.convertCandidateDtoToCandidate(candidateDto);
		candidate.setJob(job);
		candidateRepository.save(candidate);
		
		Status status = new Status();
		status.setStatusName(candidateDto.getStatus());
		status.setCandidate(candidate);
		statusRepository.save(status);
		
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
		}
		return candidate;
	}
	

	/**
	 * 
	 * @param jobId
	 * @param cid
	 * @throws IdNotFoundException
	 * find a candidate by job id and candidate id
	 */
	public Candidate findCandidateByJobIdAndCandidateId(Long jobId, Long cid) throws IdNotFoundException {
		Candidate candidate = candidateRepository.findByJobIdAndCandidateId(jobId,cid);
		if(candidate != null) {
		return candidate;
		}else {
			throw new IdNotFoundException("Candidate ID not Found For this Job");
		}
	}
	
	/**
	 * 
	 * @param jobId
	 * Find a candidate by job id
	 */
	public List<Candidate> findCandidateByJobId(Long jobId){
		return candidateRepository.findByJobId(jobId);
	}
	
	/**
	 * 
	 * @param candidate
	 * Insert a candidate
	 */
	public void insertCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}
	
	
	public void pythonTest() {
		//String x = "Hiiiii";
		String[] arry = {"Hello", "World"};
		PythonInterpreter interp = new PythonInterpreter();
		interp.set("a", new PyInteger(42));
		interp.exec("print (a)");
		interp.exec("x = 2+2");
		PyObject x = interp.get("x");

		System.out.println("x: "+x);
		
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Details for job and candidate
	 */
	@PostConstruct
	public void init() {
		pythonTest();
		if(candidateRepository.findAll().isEmpty()) {
		Job job = new Job();
		job.setActiveJob(true);
		job.setJobName("Accenture Academy");
		job.setLocation("NexTracom");
		job.setAssessmentFile("Test1.xlsx");
		job.setPosition("Java Developer");
		jobRepository.save(job);
		
		Job job1 = new Job();
		job1.setActiveJob(true);
		job1.setJobName("SAP Development");
		job1.setLocation("NexTracom");
		job1.setAssessmentFile("Test1.xlsx");
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
		candidate.setCompleteApplication(false);
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
		candidate1.setCompleteApplication(true);
		candidate1.setInternalApplication(false);
		candidate1.setRehire(true);
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
		candidate2.setCompleteApplication(true);
		candidate2.setInternalApplication(true);
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

		Skill skill = new Skill();
		skill.setDescription("HSC");
		skill.setGrade(19.0);
		skill.setLocation("St Andrew's School");
		skill.setCandidate(candidate);
		skillRepository.save(skill);
		}
		
	}

}
