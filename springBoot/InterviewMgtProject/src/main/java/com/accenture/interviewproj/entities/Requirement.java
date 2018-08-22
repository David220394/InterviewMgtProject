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
@Table(name = "TABLE_REQUIREMENT")
public class Requirement implements Serializable {
	private static final long serialVersionUID = -1335276957840542825L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REQUIREMENT_ID")
	private Long requirementId;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DURATION")
	private Integer duration;

	@Column(name = "MINIMUM_REQUIREMENT")
	private Double minRequirement;

	@ManyToOne
	@JoinColumn(name = "JOB_ID", insertable = false, updatable = false)
	private Job job;
	
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
