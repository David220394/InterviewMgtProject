package com.accenture.interviewproj.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOBS")
public class Jobs {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JOB_ID")
	private Long jobId;
	
	@Column(name="POSITION")
	private String position;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="NO_OF_VACANCY")
	private Integer noOfVacancy;
	
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="CLOSING_DATE")
	private Date closingDate;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="FIELD")
	private String field;
	
	public Jobs() {
		super();
	}
	
	public Jobs(Long jobId, String position, String location, Integer noOfVacancy, String employeeId, Date closingDate,
			Date creationDate, String field) {
		super();
		this.jobId = jobId;
		this.position = position;
		this.location = location;
		this.noOfVacancy = noOfVacancy;
		this.employeeId = employeeId;
		this.closingDate = closingDate;
		this.creationDate = creationDate;
		this.field = field;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
