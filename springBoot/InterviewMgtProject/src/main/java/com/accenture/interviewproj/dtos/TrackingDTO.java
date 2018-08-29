package com.accenture.interviewproj.dtos;

public class TrackingDTO {
	
	private String trackingType;

	private String comment;

	private String employee;
	
	private Long candidate;

	public String getTrackingType() {
		return trackingType;
	}

	public void setTrackingType(String trackingType) {
		this.trackingType = trackingType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Long getCandidate() {
		return candidate;
	}

	public void setCandidate(Long candidate) {
		this.candidate = candidate;
	}
	
	

}
