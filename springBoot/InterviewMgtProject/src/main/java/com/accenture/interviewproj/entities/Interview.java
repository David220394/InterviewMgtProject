package com.accenture.interviewproj.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="INTERVIEW")
public class Interview {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INTERVIEW_ID")
	private Long interviewId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SCHEDULING")
	private Date scheduling;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="INTERVIEWER_ID")
	private Long InterviewerId;
	
	public Interview() {
		super();
	}

	public Interview(Long interviewId, Date scheduling, String location, Long interviewerId) {
		super();
		this.interviewId = interviewId;
		this.scheduling = scheduling;
		this.location = location;
		InterviewerId = interviewerId;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public Date getScheduling() {
		return scheduling;
	}

	public void setScheduling(Date scheduling) {
		this.scheduling = scheduling;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getInterviewerId() {
		return InterviewerId;
	}

	public void setInterviewerId(Long interviewerId) {
		InterviewerId = interviewerId;
	}
}
