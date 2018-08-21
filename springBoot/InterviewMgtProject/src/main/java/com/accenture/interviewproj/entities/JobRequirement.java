package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_JOB_REQUIREMENT")
public class JobRequirement implements Serializable{
	private static final long serialVersionUID = 2314206078380893513L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_REQUIREMENT_ID")
	private Long jobRequirementId;

	@Column(name = "DURATION")
	private Integer duration;

	@Column(name = "MINIMUM_REQUIREMENT")
	private Double minRequirement;

	@ManyToOne
	@JoinColumn(name = "REQUIREMENT_ID", insertable = false, updatable = false)
	private Requirement requirement;

	@ManyToOne
	@JoinColumn(name = "JOB_ID", insertable = false, updatable = false)
	private Job job;

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Long getJobRequirementId() {
		return jobRequirementId;
	}

	public void setJobRequirementId(Long jobRequirementId) {
		this.jobRequirementId = jobRequirementId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getMinRequirement() {
		return minRequirement;
	}

	public void setMinRequirement(Double minRequirement) {
		this.minRequirement = minRequirement;
	}
}
