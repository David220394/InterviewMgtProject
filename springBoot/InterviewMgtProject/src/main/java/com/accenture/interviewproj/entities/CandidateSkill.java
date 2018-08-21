package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_CANDIDATE_SKILL")
public class CandidateSkill implements Serializable {

	private static final long serialVersionUID = 3870058895655396000L;

	@Id
	@Column(name="CANDIDATE_SKILL_ID")
	private Long candidateSkillId;
	
	@Column(name="DURATION")
	private Integer duration;
	
	@Column(name="GRADE")
	private Double grade;
	
	@ManyToOne
	@JoinColumn(name = "SKILL_ID")
	private Skill skill;
	
	@ManyToOne
	@JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;

	public Long getCandidateSkillId() {
		return candidateSkillId;
	}

	public void setCandidateSkillId(Long candidateSkillId) {
		this.candidateSkillId = candidateSkillId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
