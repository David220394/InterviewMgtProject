package com.accenture.interviewproj.entities;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HR_INTERVIEW")
public class HRInterview extends Interview{
	@Column(name="FEEDBACK")
	private String feedback;
	
	@Column(name="FORM")
	private File form;
	
	@Column(name="COMPLETE")
	private boolean complete;
	
	public HRInterview() {
		super();
	}

	public HRInterview(String feedback, File form, boolean complete) {
		super();
		this.feedback = feedback;
		this.form = form;
		this.complete = complete;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public File getForm() {
		return form;
	}

	public void setForm(File form) {
		this.form = form;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}
