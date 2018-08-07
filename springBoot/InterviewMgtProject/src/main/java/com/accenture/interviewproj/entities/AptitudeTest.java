package com.accenture.interviewproj.entities;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="APTITUDE_TEST")
public class AptitudeTest extends Interview{
	@Column(name="FORM")
	private File form;
	
	@Column(name="SCORE")
	private Double score;
	
	@Column(name="COMPLETE")
	private boolean complete;
	
	public AptitudeTest() {
		super();
	}

	public AptitudeTest(File form, Double score, boolean complete) {
		super();
		this.form = form;
		this.score = score;
		this.complete = complete;
	}

	public File getForm() {
		return form;
	}

	public void setForm(File form) {
		this.form = form;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
