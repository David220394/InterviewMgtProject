package com.accenture.interviewproj.utilities;

import com.accenture.interviewproj.dtos.JobDto;
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
		job.setNoOfVacancy(jobDto.getNoOfVacancy());
		job.setClosingDate(jobDto.getClosingDate());
		job.setCreationDate(jobDto.getCreationDate());
		job.setActiveJob(true);
		job.setField(jobDto.getField());
		job.setRequirements(jobDto.getRequirements());
		job.setAssignTo(jobDto.getAssignTo());
		return job;
	}
	
	/*public static Requirement convertRequirementDtoToDto(RequirementDto requirementDto) {
		Requirement requirement = new Requirement();
		requirement.setName(requirementDto.getName());
		requirement.setDuration(requirementDto.getDuration());
		requirement.setMinRequirement(requirementDto.getMinRequirement());
		return requirement;
	}*/

}
