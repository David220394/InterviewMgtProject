package com.accenture.interviewproj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TechnicalInterview extends Interview {
	
	private static final long serialVersionUID = -7268031684349052656L;
	
	@Column(name="TECHNICAL_INTERVIEW_FEEDBACK")
	private String feedback;

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	

}
