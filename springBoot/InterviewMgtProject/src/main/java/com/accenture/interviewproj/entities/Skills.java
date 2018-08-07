package com.accenture.interviewproj.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SKILL_TABLE")
public class Skills implements Serializable{
	
	private static final long serialVersionUID = 4534726326232929755L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SKILL_ID")
	private Long skillId;
	
	@Column(name="TAG")
	private String tag;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="YEARS")
	@Temporal(TemporalType.TIMESTAMP)
	private Integer years;
	
	public Skills() {
		super();
	}
	
	public Skills(Long skillId, String tag, String description, Integer years) {
		super();
		this.skillId = skillId;
		this.tag = tag;
		this.description = description;
		this.years = years;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}	
}
