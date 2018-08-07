package com.accenture.interviewproj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="JOB_REQUIREMENTS")
public class JobRequirements {
	
	@Column(name="JOB_ID")
	private Long jobId;
	
	@Column(name="REQUIREMENT_ID")
	private Long requirementId;
	
	public JobRequirements() {
		super();
	}

	public JobRequirements(Long jobId, Long requirementId) {
		super();
		this.jobId = jobId;
		this.requirementId = requirementId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}
}
