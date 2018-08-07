package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CANDIDATE_JOBS")
public class CandidateJobs implements Serializable{
	
	private static final long serialVersionUID = -9099762020638508310L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JOB_ID")
	private Long jobId;
	
	@Column(name="CANDIDATE_ID")
	private Long candidateId;
	
	public CandidateJobs() {
		super();
	}

	public CandidateJobs(Long jobId, Long candidateId) {
		super();
		this.jobId = jobId;
		this.candidateId = candidateId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
}
