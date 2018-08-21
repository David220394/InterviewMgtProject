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
@Table(name="TABLE_SKILL")
public class Skill implements Serializable {
	
	private static final long serialVersionUID = 4124043367420129135L;

	@Id
	@Column(name="SKILL_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long skillId;
	
	@Column(name="SKILL_DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy = "skill")
	private Set<CandidateSkill> candidatesSkills;

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

	public Set<CandidateSkill> getCandidatesSkills() {
		return candidatesSkills;
	}

	public void setCandidatesSkills(Set<CandidateSkill> candidatesSkills) {
		this.candidatesSkills = candidatesSkills;
	}

}
