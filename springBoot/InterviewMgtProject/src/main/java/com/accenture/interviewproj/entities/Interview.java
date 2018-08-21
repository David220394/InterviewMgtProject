package com.accenture.interviewproj.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_INTERVIEW")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INTERVIEW_TYPE")
public class Interview implements Serializable {
	
	private static final long serialVersionUID = 9044017024222964347L;

	@Id
	@Column(name="INTERVIEW_ID")
	private Long interviewId;
	
	@Column(name="INTERVIEW_LOCATION")
	private String location;
	
	@Column(name="COMPLETED")
	private Boolean completed;
	
	@Column(name="SCHEDULE_DATE_TIME")
	private LocalDateTime scheduleDateTime;
	
	@Column(name="SCORE")
	private Double score;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name="CANDIDATE_ID")
	private Candidate candidate;

	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public LocalDateTime getScheduleDateTime() {
		return scheduleDateTime;
	}

	public void setScheduleDateTime(LocalDateTime scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
}
