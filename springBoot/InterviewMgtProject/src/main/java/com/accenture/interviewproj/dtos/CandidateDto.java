package com.accenture.interviewproj.dtos;

import java.io.File;
import java.util.Set;

import com.accenture.interviewproj.entities.CandidateExperience;
import com.accenture.interviewproj.entities.Interview;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.entities.Skill;
import com.accenture.interviewproj.entities.Tracking;
import com.accenture.interviewproj.enums.CandidateStatus;

public class CandidateDto {

	private String candidateName;

	private String candidateAddress;

	private File candidateCv;

	private CandidateStatus status;
	
	private Integer score;
	
	private String email;
	
	private Boolean availability;
	
	private String coverLetter;
	
	private Set<Long> candidatePhones;
	
	private Set<CandidateExperience> candidateExperiences;
	
	private Set<Skill> skills;

	private Set<Interview> interviews;
	
	private Set<Tracking> trackings;
	
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(String candidateAddress) {
		this.candidateAddress = candidateAddress;
	}

	public File getCandidateCv() {
		return candidateCv;
	}

	public void setCandidateCv(File candidateCv) {
		this.candidateCv = candidateCv;
	}

	public CandidateStatus getStatus() {
		return status;
	}

	public void setStatus(CandidateStatus status) {
		this.status = status;
	}

	public Set<Long> getCandidatePhones() {
		return candidatePhones;
	}

	public void setCandidatePhones(Set<Long> candidatePhones) {
		this.candidatePhones = candidatePhones;
	}

	public Set<CandidateExperience> getCandidateExperiences() {
		return candidateExperiences;
	}

	public void setCandidateExperiences(Set<CandidateExperience> candidateExperiences) {
		this.candidateExperiences = candidateExperiences;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public Set<Tracking> getTrackings() {
		return trackings;
	}

	public void setTrackings(Set<Tracking> trackings) {
		this.trackings = trackings;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	private Job job;

}
