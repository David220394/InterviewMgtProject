package com.accenture.interviewproj.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Row;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.entities.Candidate;
import com.accenture.interviewproj.enums.Gender;

public class CandidateUtility {
	
	private CandidateUtility() {}
	
/*	public static CandidateDto convertCandidateToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setCandidateAddress(candidate.getCandidateAddress());
		candidateDto.setCandidateCv(candidate.getCandidateCv());
		candidateDto.setCandidateExperience(candidate.getCandidateExperience());
		candidateDto.setCandidateName(candidate.getCandidateName());
		candidateDto.setCandidatePhone(candidate.getCandidatePhone());
		candidateDto.setAvailability(candidate.getAvailability());
		candidateDto.setScore(candidate.getScore());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setCoverLetter(candidate.getCoverLetter());
		candidateDto.setSkills(candidate.getSkills());
		candidateDto.setInterviews(candidate.getInterviews());
		candidateDto.setJob(candidate.getJob());
		candidateDto.setStatus(candidate.getStatus());
		candidateDto.setTrackings(candidate.getTrackings());
		candidateDto.setApplicationDate(candidate.getApplicationDate());
		candidateDto.setCompleteApplication(candidate.getCompleteApplication());
		candidateDto.setDob(candidate.getDob());
		candidateDto.setEducation(candidate.getEducation());
		candidateDto.setGender(candidate.getGender());
		candidateDto.setRehire(candidate.getRehire());
		candidateDto.setInternalApplication(candidate.getInternalApplication());
		return candidateDto;
		
	}*/
	
	public static CandidateDto getCandidateFromExcel(Row row) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setJobId((long) row.getCell(0).getNumericCellValue());
		candidateDto.setCandidateName(row.getCell(1).getStringCellValue());
		candidateDto.setCandidateAddress(row.getCell(2).getStringCellValue()+" "+row.getCell(3).getStringCellValue());
		candidateDto.setGender(Gender.valueOf(row.getCell(4).getStringCellValue().toUpperCase()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		candidateDto.setDob(LocalDate.parse(row.getCell(5).getStringCellValue(),formatter));
		candidateDto.setEmail(row.getCell(6).getStringCellValue());
		candidateDto.setCandidatePhone((long) row.getCell(7).getNumericCellValue());
		candidateDto.setApplicationDate(LocalDate.parse(row.getCell(8).getStringCellValue(),formatter).atStartOfDay());
		candidateDto.setCompleteApplication(row.getCell(9).getBooleanCellValue());
		candidateDto.setInternalApplication(row.getCell(10).getBooleanCellValue());
		candidateDto.setRehire(row.getCell(11).getBooleanCellValue());
		candidateDto.setStatus(row.getCell(12).getStringCellValue());
		candidateDto.setSpecialty(row.getCell(14).getStringCellValue());
		candidateDto.setLastJobRole(row.getCell(15).getStringCellValue());
		candidateDto.setEmployer(row.getCell(16).getStringCellValue());
		candidateDto.setInstitution(row.getCell(17).getStringCellValue());
		candidateDto.setProgramStudy(row.getCell(18).getStringCellValue());
		candidateDto.setGrade(row.getCell(19).getNumericCellValue());
		return candidateDto;
	}
	
	public static Candidate convertCandidateDtoToCandidate(CandidateDto candidateDto) {
		Candidate candidate = new Candidate();
		candidate.setCandidateName(candidateDto.getCandidateName());
		candidate.setCandidateAddress(candidateDto.getCandidateAddress());
		candidate.setAvailability(true);
		candidate.setEmail(candidateDto.getEmail());
		candidate.setCoverLetter("Dear .. . .");
		candidate.setCandidatePhone(candidateDto.getCandidatePhone());
		candidate.setDob(candidateDto.getDob());
		candidate.setGender(candidateDto.getGender());
		candidate.setInternalApplication(candidateDto.getInternalApplication());
		candidate.setCompleteApplication(candidateDto.getCompleteApplication());
		candidate.setRehire(candidateDto.getRehire());
		candidate.setScore(0);
		return candidate;
	}

}