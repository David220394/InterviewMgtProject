package com.accenture.interviewproj.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_SKILL")
public class Skill implements Serializable {
	
	private static final long serialVersionUID = 4124043367420129135L;

	@Id
	@Column(name="SKILL_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long skillId;
	
	@Column(name="SKILL_DESCRIPTION")
	private String description;

	@Column(name="SKILL_LOCATION")
	private String location;
	
	@Column(name="GRADE")
	private Double grade;
	
	@ManyToOne
	@JoinColumn(name = "CANDIDATE_ID")
	@JsonIgnore
	private Candidate candidate;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
