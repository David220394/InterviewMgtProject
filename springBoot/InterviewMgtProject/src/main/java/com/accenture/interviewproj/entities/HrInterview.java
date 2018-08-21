package com.accenture.interviewproj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HrInterview extends Interview{
	
	private static final long serialVersionUID = -4402467118774494768L;
	
	@Column(name="HR_INTERVIEW_FEEDBACK")
	private String feedback;

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	

}
