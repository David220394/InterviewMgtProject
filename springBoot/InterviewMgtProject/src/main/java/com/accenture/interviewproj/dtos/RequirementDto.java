package com.accenture.interviewproj.dtos;

import javax.validation.constraints.NotNull;

public class RequirementDto {
	
	@NotNull
	private String name;
	
	private Integer duration;

	private Double minRequirement;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getMinRequirement() {
		return minRequirement;
	}

	public void setMinRequirement(Double minRequirement) {
		this.minRequirement = minRequirement;
	}

}
