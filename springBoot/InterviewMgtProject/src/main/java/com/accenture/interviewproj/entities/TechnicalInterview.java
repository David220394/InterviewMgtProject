package com.accenture.interviewproj.entities;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TECHNICAL_INTERVIEW")
public class TechnicalInterview extends Interview{
	
	@Id
	@Column(name="FEEDBACK")
	private String feedback;
	
	@Column(name="COMPLETE")
	private boolean complete;
	
	@Column(name="FORM")
	private File form;
	
	public TechnicalInterview() {
		super();
	}
	
	public TechnicalInterview(String feedback, boolean complete, File form) {
		super();
		this.feedback = feedback;
		this.complete = complete;
		this.form = form;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public File getForm() {
		return form;
	}

	public void setForm(File form) {
		this.form = form;
	}

}
