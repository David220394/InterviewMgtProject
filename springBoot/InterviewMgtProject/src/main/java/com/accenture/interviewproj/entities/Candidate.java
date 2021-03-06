package com.accenture.interviewproj.entities;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.accenture.interviewproj.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TABLE_CANDIDATE")
public class Candidate implements Serializable {

	private static final long serialVersionUID = -7687587900738745856L;

	@Id
	@Column(name = "CANDIDATE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long candidateId;

	@Column(name = "CANDIDATE_NAME", nullable = false)
	private String candidateName;

	@Column(name = "CANDIDATE_ADDRESS")
	private String candidateAddress;

	@Column(name = "CANDIDATE_GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "BIRTH_DATE")
	private LocalDate dob;

	@Column(name = "APPLICATION_DATE")
	private LocalDateTime applicationDate;

	@Column(name = "CANDIDATE_EMAIL")
	private String email;

	@Column(name = "APPLICATION_COMPLETED")
	private Boolean completeApplication;

	@Column(name = "INTERNAL_APPLICATION")
	private Boolean internalApplication;

	@Column(name = "REHIRE")
	private Boolean rehire;

	@Column(name = "CANDIDATE_CV")
	private String candidateCv;

	@Column(name = "CANDIDATE_AVAILABILITY")
	private Boolean availability;

	@Column(name = "CANDIDATE_COVER_LETTER")
	private String coverLetter;

	@Column(name = "CANDIDATE_PHONE")
	private Long candidatePhone;

	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "candidate")
	private CandidateExperience candidateExperience;

	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "candidate")
	private Education education;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TABLE_SKILL_CANDIDATE", 
	joinColumns = { @JoinColumn(name = "CANDIDATE_ID")},
	inverseJoinColumns = { @JoinColumn(name = "SKILL_ID")})
	private Set<Skill> skills;

	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private Set<Interview> interviews;

	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private Set<Tracking> trackings;
	
	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<JobCandidate> jobCandidates;
	
	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<CandidateSkillScore> candidateSkillScores;
	
	public void setCandidatePhone(Long candidatePhone) {
		this.candidatePhone = candidatePhone;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Boolean getCompleteApplication() {
		return completeApplication;
	}

	public void setCompleteApplication(Boolean completeApplication) {
		this.completeApplication = completeApplication;
	}

	public Boolean getInternalApplication() {
		return internalApplication;
	}

	public void setInternalApplication(Boolean internalApplication) {
		this.internalApplication = internalApplication;
	}

	public Boolean getRehire() {
		return rehire;
	}

	public void setRehire(Boolean rehire) {
		this.rehire = rehire;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}


	public List<JobCandidate> getJobCandidates() {
		return jobCandidates;
	}

	public void setJobCandidates(List<JobCandidate> jobCandidates) {
		this.jobCandidates = jobCandidates;
	}

	public Set<Tracking> getTrackings() {
		return trackings;
	}

	public void setTrackings(Set<Tracking> trackings) {
		this.trackings = trackings;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(String candidateAddress) {
		this.candidateAddress = candidateAddress;
	}

	public String getCandidateCv() {
		return candidateCv;
	}

	public void setCandidateCv(String candidateCv) {
		this.candidateCv = candidateCv;
	}

	public Long getCandidatePhone() {
		return candidatePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	

	public CandidateExperience getCandidateExperience() {
		return candidateExperience;
	}

	public void setCandidateExperience(CandidateExperience candidateExperience) {
		this.candidateExperience = candidateExperience;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	
	
	public List<CandidateSkillScore> getCandidateSkillScores() {
		return candidateSkillScores;
	}

	public void setCandidateSkillScores(List<CandidateSkillScore> candidateSkillScores) {
		this.candidateSkillScores = candidateSkillScores;
	}

	@PrePersist
	public void initCreatedDate() {
		this.applicationDate = LocalDateTime.now();
	}


}
