package com.accenture.interviewproj.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class JobDto {
	
	private String jobName;
	
	private String position;
	
	private String location;
	
	private Integer noOfVacancy;
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime closingDate;

	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime creationDate;
	
	private List<String> assignTo = new ArrayList<>();
	
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


	public Integer getNoOfVacancy() {
		return noOfVacancy;
	}

	public void setNoOfVacancy(Integer noOfVacancy) {
		this.noOfVacancy = noOfVacancy;
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
