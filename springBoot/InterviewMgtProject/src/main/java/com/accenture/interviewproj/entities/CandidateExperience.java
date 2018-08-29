package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TABLE_CANDIDATE_EXPERIENCE")
public class CandidateExperience implements Serializable {
	
	private static final long serialVersionUID = -1531993904282123130L;

	@Id
	@Column(name="EXPERIENCE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long experienceId;
	
	@Column(name="EXPERIENCE_NAME")
	private String experienceName;
	
	@Column(name="DURATION")
	private Integer duration;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CANDIDATE_ID")
	@JsonIgnore
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

