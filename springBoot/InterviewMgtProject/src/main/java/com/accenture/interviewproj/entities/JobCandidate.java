package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TABLE_JOB_CANDIDATE")
public class JobCandidate implements Serializable {

	private static final long serialVersionUID = 5847356437954582740L;

	@Id
	@Column(name="JOB_CANDIDATE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long jobCandidateId;
	
	@Column(name = "CANDIDATE_SCORE")
	private Integer score;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "jobCandidate")
	private Status status;
	
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	@JsonIgnore
	private Job job;
	
	@ManyToOne
	@JoinColumn(name="CANDIDATE_ID")
	@JsonIgnore
	private Candidate candidate;

	public Long getJobCandidateId() {
		return jobCandidateId;
	}

	public void setJobCandidateId(Long jobCandidateId) {
		this.jobCandidateId = jobCandidateId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
	

}
