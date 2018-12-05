package com.accenture.interviewproj.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.JobWithIdDto;
import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.entities.Job;

public class JobUtility {
	
	/**
	 * 
	 * @param jobDto
	 * Convert jobDto to job
	 */
	public static Job convertJobDtoToJob(JobDto jobDto) {
		Job job = new Job();
		job.setJobName(jobDto.getProjectName());
		job.setPosition(jobDto.getPosition());
		job.setLocation(jobDto.getLocation());
		job.setClosingDate(jobDto.getClosingDate());
		job.setCreationDate(jobDto.getCreationDate());
		job.setNoOfVecancy(jobDto.getNoOfVacancy());
		job.setRemaining(jobDto.getNoOfVacancy());
		job.setActiveJob(true);
		job.setAssignTo(jobDto.getAssignTos());
		return job;
	}
	
	public static JobDto getJobFromExcel(Row row) {
		List<String> requirements = new ArrayList<>();
		List<String> assignTos = new ArrayList<>();
		JobDto jobDto = new JobDto();
		jobDto.setProjectName(row.getCell(0).getStringCellValue());
		jobDto.setPosition(row.getCell(1).getStringCellValue());
		jobDto.setLocation(row.getCell(2).getStringCellValue());
		assignTos.add(row.getCell(3).getStringCellValue());
		assignTos.add(row.getCell(4).getStringCellValue());
		assignTos.add(row.getCell(5).getStringCellValue());
		jobDto.setAssignTos(assignTos);
		int i = 6;
		while(row.getCell(i) != null) {
			requirements.add(row.getCell(i).getStringCellValue());
			i++;
		}
		jobDto.setRequirements(requirements);
		System.out.println(jobDto.getAssignTos());
		return jobDto;
	}
	
	public static QuestionDto  getquestionFromExcel(Row row) {
		List<String> answers = new ArrayList<>();
		QuestionDto questionDto = new QuestionDto();
		questionDto.setQuestion(row.getCell(0).getStringCellValue());
		questionDto.setCorrectAnswer(row.getCell(1).getStringCellValue());
		int i=2;
		while(row.getCell(i) != null) {
			answers.add(row.getCell(i).getStringCellValue());
			i++;
		}
		questionDto.setAnswers(answers);
		return questionDto;
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
	
	public static List<QuestionDto> convertExcelToQuestionDto(InputStream file) throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<QuestionDto> questionDtos = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		if(!rowIterator.hasNext()) {
			return null;
		}
		rowIterator.next();
		DataFormatter formatter = new DataFormatter();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			QuestionDto questionDto = new QuestionDto();
			questionDto.setQuestion(row.getCell(0).getStringCellValue());
			questionDto.setCorrectAnswer(formatter.formatCellValue(row.getCell(1)));
			questionDto.setMark((int)row.getCell(2).getNumericCellValue());
			List<String> possibleAns = new ArrayList<>();
			for(int i=3;i<row.getLastCellNum();i++) {	
				possibleAns.add(formatter.formatCellValue(row.getCell(i)));
			}
			questionDto.setAnswers(possibleAns);
			questionDtos.add(questionDto);
		}
		return questionDtos;
	}

}
