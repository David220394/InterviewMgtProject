package com.accenture.interviewproj.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CANDIDATE_TABLE")
public class Candidates implements Serializable {

	private static final long serialVersionUID = -6760260333984275561L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CANDIDATE_ID")
	private Long candidateId;
	
	@Column(name="CANDIDATE_NAME")
	private String candidateName;
	
	@Column(name="CANDIDATE_ADDRESS")
	private String address;
	
	@Column(name="CANDIDATE_EXPERIENCE")
	private List<String> experience;
	
	@Column(name="CANDIDATE_PHONE")
	private List<Long> phone;
	
	@Column(name="CANDIDATE_EDUCATION")
	private List<String> education;
	
	
	public Candidates() {
		super();
	}
	
	public Candidates(Long candidateId, String candidateName, String address, List<String> experience, List<Long> phone,
			List<String> education) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.address = address;
		this.experience = experience;
		this.phone = phone;
		this.education = education;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getExperience() {
		return experience;
	}

	public void setExperience(List<String> experience) {
		this.experience = experience;
	}

	public List<Long> getPhone() {
		return phone;
	}

	public void setPhone(List<Long> phone) {
		this.phone = phone;
	}

	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}
	
}
