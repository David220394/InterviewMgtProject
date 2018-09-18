package com.accenture.interviewproj.utilities;

import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.JobWithIdDto;
import com.accenture.interviewproj.entities.Job;

public class JobUtility {
	
	/**
	 * 
	 * @param jobDto
	 * Convert jobDto to job
	 */
	public static Job convertJobDtoToJob(JobDto jobDto) {
		Job job = new Job();
		job.setJobName(jobDto.getJobName());
		job.setPosition(jobDto.getPosition());
		job.setLocation(jobDto.getLocation());
		job.setClosingDate(jobDto.getClosingDate());
		job.setCreationDate(jobDto.getCreationDate());
		job.setActiveJob(true);
		job.setRequirements(jobDto.getRequirements());
		job.setAssignTo(jobDto.getAssignTo());
		return job;
	}
	
	public static JobWithIdDto convertJobWithIdDtoToJob(Job job) {
		JobWithIdDto jobWithIdDto = new JobWithIdDto();
		jobWithIdDto.setJobId(job.getJobId());
		jobWithIdDto.setJobName(job.getJobName());
		jobWithIdDto.setPosition(job.getPosition());
		jobWithIdDto.setLocation(job.getLocation());
		jobWithIdDto.setClosingDate(job.getClosingDate());
		jobWithIdDto.setCreationDate(job.getCreationDate());
		jobWithIdDto.setActiveJob(true);
		jobWithIdDto.setAssignTo(job.getAssignTo());
		return  jobWithIdDto;
	}
	
	/*public static Requirement convertRequirementDtoToDto(RequirementDto requirementDto) {
		Requirement requirement = new Requirement();
		requirement.setName(requirementDto.getName());
		requirement.setDuration(requirementDto.getDuration());
		requirement.setMinRequirement(requirementDto.getMinRequirement());
		return requirement;
	}*/

}
