package com.accenture.interviewproj.utilities;

import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.RequirementDto;
import com.accenture.interviewproj.entities.Requirement;
import com.accenture.interviewproj.entities.Job;

public class JobUtility {
	
	public static Job convertJobDtoToJob(JobDto jobDto) {
		Job job = new Job();
		job.setJobName(jobDto.getJobName());
		job.setPosition(jobDto.getPosition());
		job.setLocation(jobDto.getLocation());
		job.setNoOfVacancy(jobDto.getNoOfVancancy());
		job.setClosingDate(jobDto.getClosingDate());
		job.setCreationDate(jobDto.getCreationDate());
		job.setAssessmentFile(jobDto.getAssessmentFile());
		job.setActiveJob(true);
		job.setAssignTo(jobDto.getAssignTo());
		job.setField(jobDto.getField());
		return job;
	}
	
	public static Requirement convertRequirementDtoToDto(RequirementDto requirementDto) {
		Requirement requirement = new Requirement();
		requirement.setName(requirementDto.getName());
		requirement.setDuration(requirementDto.getDuration());
		requirement.setMinRequirement(requirementDto.getMinRequirement());
		return requirement;
	}

}
