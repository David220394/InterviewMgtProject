package com.accenture.interviewproj.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class JobDto {
	
	private String jobName;
	
	private String position;
	
	private String location;
	
	private Integer noOfVancancy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime closingDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime creationDate;


	private String field;
	
	private List<String> assignTo;
	
	private String requirements;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getNoOfVancancy() {
		return noOfVancancy;
	}

	public void setNoOfVancancy(Integer noOfVancancy) {
		this.noOfVancancy = noOfVancancy;
	}

	public LocalDateTime getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDateTime closingDate) {
		this.closingDate = closingDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<String> getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(List<String> assignTo) {
		this.assignTo = assignTo;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
}
