package com.accenture.interviewproj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REQUIREMENT")
public class Requirement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REQUIREMENT_ID")
	private Long requirementId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MIN_REQUIREMENT")
	private Double minRequirement;
	
	public Requirement() {
		super();
	}

	public Requirement(Long requirementId, String name, Double minRequirement) {
		super();
		this.requirementId = requirementId;
		this.name = name;
		this.minRequirement = minRequirement;
	}

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinRequirement() {
		return minRequirement;
	}

	public void setMinRequirement(Double minRequirement) {
		this.minRequirement = minRequirement;
	}	

}
