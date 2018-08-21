package com.accenture.interviewproj.dtos;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;


public class JobDto {
	
	private String JobName;
	
	private String position;
	
	private String location;
	
	private Integer noOfVancancy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime closingDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime creationDate;

	private File assessmentFile;

	private String field;
	
	private Set<String> assignTo;
	
	private Set<RequirementDto> requirements;

	public String getJobName() {
		return JobName;
	}

	public void setJobName(String jobName) {
		JobName = jobName;
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

	public File getAssessmentFile() {
		return assessmentFile;
	}

	public void setAssessmentFile(File assessmentFile) {
		this.assessmentFile = assessmentFile;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Set<String> getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(Set<String> assignTo) {
		this.assignTo = assignTo;
	}

	public Set<RequirementDto> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<RequirementDto> requirements) {
		this.requirements = requirements;
	}
}
