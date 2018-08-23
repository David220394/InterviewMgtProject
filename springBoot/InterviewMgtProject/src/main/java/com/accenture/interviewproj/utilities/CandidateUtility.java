package com.accenture.interviewproj.utilities;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.entities.Candidate;

public class CandidateUtility {
	
	private CandidateUtility() {}
	
	public static CandidateDto convertCandidateToDto(Candidate candidate) {
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
		
	}

}
