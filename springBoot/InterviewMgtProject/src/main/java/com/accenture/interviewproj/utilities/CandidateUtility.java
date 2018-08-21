package com.accenture.interviewproj.utilities;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.entities.Candidate;

public class CandidateUtility {
	
	public static CandidateDto convertCandidateToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setCandidateAddress(candidate.getCandidateAddress());
		candidateDto.setCandidateCv(candidate.getCandidateCv());
		candidateDto.setCandidateExperiences(candidate.getCandidateExperiences());
		candidateDto.setCandidateName(candidate.getCandidateName());
		candidateDto.setCandidatePhones(candidate.getCandidatePhones());
		candidateDto.setAvailability(candidate.getAvailability());
		candidateDto.setScore(candidate.getScore());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setCoverLetter(candidate.getCoverLetter());
		candidateDto.setSkills(candidate.getSkills());
		candidateDto.setInterviews(candidate.getInterviews());
		candidateDto.setJob(candidate.getJob());
		candidateDto.setStatus(candidate.getStatus());
		candidateDto.setTrackings(candidate.getTrackings());
		return candidateDto;
		
	}

}
