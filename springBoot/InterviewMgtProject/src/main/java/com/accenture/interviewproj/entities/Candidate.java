package com.accenture.interviewproj.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.accenture.interviewproj.enums.CandidateStatus;


@Entity
@Table(name="TABLE_CANDIDATE")
public class Candidate implements Serializable {

	private static final long serialVersionUID = -7687587900738745856L;

	@Id
	@Column(name="CANDIDATE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long candidateId;
	
	@Column(name="CANDIDATE_NAME")
	private String candidateName;
	
	@Column(name="CANDIDATE_ADDRESS")
	private String candidateAddress;
	
	@Column(name="CANDIDATE_CV")
	private File candidateCv;
	
	@Column(name="CANDIDATE_STATUS")
	@Enumerated(EnumType.STRING)
	private CandidateStatus status;
	
	@ElementCollection
	@CollectionTable(name="TABLE_CANDIDATE_PHONE")
	private Set<Long> candidatePhones;
	
	@OneToMany(mappedBy="candidate")
	private Set<CandidateExperience> candidateExperiences;
	
	@OneToMany(mappedBy = "candidate")
	private Set<CandidateSkill> candidatesSkills;

	@OneToMany(mappedBy = "candidate")
	private Set<Interview> interviews;
	
	@OneToMany(mappedBy = "candidate")
	private Set<Tracking> trackings;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private Job job;
	
	public CandidateStatus getStatus() {
		return status;
	}

	public void setStatus(CandidateStatus status) {
		this.status = status;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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

	public File getCandidateCv() {
		return candidateCv;
	}

	public void setCandidateCv(File candidateCv) {
		this.candidateCv = candidateCv;
	}

	public Set<Long> getCandidatePhones() {
		return candidatePhones;
	}

	public void setCandidatePhone(Set<Long> candidatePhones) {
		this.candidatePhones = candidatePhones;
	}

	public Set<CandidateExperience> getCandidateExperiences() {
		return candidateExperiences;
	}

	public void setCandidateExperiences(Set<CandidateExperience> candidateExperiences) {
		this.candidateExperiences = candidateExperiences;
	}

	public Set<CandidateSkill> getCandidatesSkills() {
		return candidatesSkills;
	}

	public void setCandidatesSkills(Set<CandidateSkill> candidatesSkills) {
		this.candidatesSkills = candidatesSkills;
	}

}