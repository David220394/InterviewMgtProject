package com.accenture.interviewproj.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "CLOSING_DATE", nullable = false)
	private LocalDateTime closingDate;

	@Column(name = "CREATION_DATE", nullable = false)
	private LocalDateTime creationDate;

	@Column(name = "ASSESSMENT_FILE")
	private String assessmentFile;

	@Column(name = "ACTIVE_JOB")
	private Boolean activeJob;
	
	@Column(name = "REQUIREMENTS")
	private String requirements;

	@ElementCollection
	private List<String> assignTo = new ArrayList<>();

	@ManyToMany(mappedBy = "jobs", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employee;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private Set<Interview> interviews;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private Set<Candidate> candidates;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private Set<Tracking> trakings;

	
	public Boolean getActiveJob() {
		return activeJob;
	}

	public void setActiveJob(Boolean activeJob) {
		this.activeJob = activeJob;
	}

	public Set<Tracking> getTrakings() {
		return trakings;
	}

	public void setTrakings(Set<Tracking> trakings) {
		this.trakings = trakings;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
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

	public String getAssessmentFile() {
		return assessmentFile;
	}

	public void setAssessmentFile(String assessmentFile) {
		this.assessmentFile = assessmentFile;
	}
	
	@PrePersist
	public void initCreatedDate() {
		this.creationDate = LocalDateTime.now();
		this.closingDate = LocalDateTime.of(LocalDate.now().getYear(), (LocalDate.now().getMonthValue() + 2), 1, 0, 0);
	}

	public List<String> getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(List<String> assignTo) {
		this.assignTo = assignTo;
	}

}
