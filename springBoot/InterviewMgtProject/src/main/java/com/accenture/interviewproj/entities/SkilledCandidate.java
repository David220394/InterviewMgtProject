package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SKILLED_CANDIDATE")
public class SkilledCandidate implements Serializable{
	
	private static final long serialVersionUID = -6760620980569807053L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SKILL_ID")
	private Long skillId;
	
	@Column(name="CANDIDATE_ID")
	private Long candidateId;
	
	public SkilledCandidate() {
		super();
	}
	
	public SkilledCandidate(Long skillId, Long candidateId) {
		super();
		this.skillId = skillId;
		this.candidateId = candidateId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	
	
	

}
