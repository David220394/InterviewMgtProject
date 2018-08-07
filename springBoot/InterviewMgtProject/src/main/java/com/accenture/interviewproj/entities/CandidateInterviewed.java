package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CANDIDATE_INTERVIEWED")
public class CandidateInterviewed implements Serializable{

	private static final long serialVersionUID = -8177631348761439504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INTERVIEW_ID")
	private Long interviewId;
	
	@Column(name="CANDIDATE_ID")
	private Long candidateId;

	public CandidateInterviewed() {
		super();
	}

	public CandidateInterviewed(Long interviewId, Long candidateId) {
		super();
		this.interviewId = interviewId;
		this.candidateId = candidateId;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
}
