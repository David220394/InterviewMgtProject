package com.accenture.interviewproj.entities;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_JOB")
public class Job implements Serializable {
	private static final long serialVersionUID = -2450298674198837000L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_ID")
	private Long jobId;

	@Column(name = "JOB_NAME", unique = true)
	private String jobName;

	@Column(name = "POSITION")
	private String position;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "NUMBER_OF_VACANCY")
	private Integer noOfVacancy;

	@Column(name = "CLOSING_DATE", nullable = false)
	private LocalDateTime closingDate;

	@Column(name = "CREATION_DATE", nullable = false)
	private LocalDateTime creationDate;

	@Column(name = "ASSESSMENT_FILE")
	private File assessmentFile;

	@Column(name = "ACTIVE_JOB")
	private Boolean activeJob;

	@Column(name = "FIELD")
	private String field;

	@ElementCollection
	@CollectionTable(name="TABLE_JOB_ASSIGN_TO")
	private Set<String> assignTo;

	@ManyToMany(mappedBy = "jobs", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employee;

	@OneToMany(mappedBy = "job")
	private Set<Requirement> requirements = new HashSet<>();
	
	@OneToMany(mappedBy = "job")
	private Set<Interview> interviews;
	
	@OneToMany(mappedBy = "job")
	private Set<Candidate> candidates;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Boolean getActiveJob() {
		return activeJob;
	}

	public void setActiveJob(Boolean activeJob) {
		this.activeJob = activeJob;
	}

	public Set<String> getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(Set<String> assignTo) {
		this.assignTo = assignTo;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public Set<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
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

	public Integer getNoOfVancancy() {
		return noOfVacancy;
	}

	public void setNoOfVancancy(Integer noOfVancancy) {
		this.noOfVacancy = noOfVancancy;
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

	public boolean isActiveJob() {
		return activeJob;
	}

	public void setActiveJob(boolean activeJob) {
		this.activeJob = activeJob;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
