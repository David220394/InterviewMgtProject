package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_CANDIDATE_EXPERIENCE")
public class CandidateExperience implements Serializable {
	
	private static final long serialVersionUID = -1531993904282123130L;

	@Id
	@Column(name="EXPERIENCE_ID")
	private Long experienceId;
	
	@Column(name="EXPERIENCE_NAME")
	private String experienceName;
	
	@Column(name="DURATION")
	private Integer duration;
	
	@ManyToOne
	@JoinColumn(name="CANDIDATE_ID")
	private Candidate candidate;

	public Long getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}

	public String getExperienceName() {
		return experienceName;
	}

	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
