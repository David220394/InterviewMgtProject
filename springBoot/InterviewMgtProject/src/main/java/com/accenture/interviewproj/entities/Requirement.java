package com.accenture.interviewproj.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_REQUIREMENT")
public class Requirement implements Serializable {
	
	private static final long serialVersionUID = -1335276957840542825L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REQUIREMENT_ID")
	private Long requirementId;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "requirement")
	private Set<JobRequirement> jobRequirements;

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
}
