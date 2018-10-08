package com.accenture.interviewproj.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.entities.Job;
import com.accenture.interviewproj.exceptions.JobNameAlreadyExistsException;
import com.accenture.interviewproj.exceptions.JobNotFoundException;
import com.accenture.interviewproj.repositories.JobsRepository;

@Service
public class JobService {
	
	private final JobsRepository jobRepository;
	
	public JobService(JobsRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	/**
	 * 
	 * @param job
	 * @throws JobNameAlreadyExistsException
	 * Insert a job
	 */
	public Job insertJob(Job job) throws JobNameAlreadyExistsException {
		if(jobRepository.findByJobName(job.getJobName()) == null) {
			return jobRepository.save(job);
		}else {
			throw new JobNameAlreadyExistsException("This job name already exists");
		}	
	}
	
	/**
	 * 
	 * @param jobId
	 * Find a job by id
	 */
	public Job findByJobId(Long jobId) {
		return jobRepository.findByJobId(jobId);
	}
	
	/**
	 * 
	 * @param jobName
	 * Find a job by name
	 */
	public Job findByJobName(String jobName) {
		return jobRepository.findByJobName(jobName);
	}
	
	/**
	 * 
	 * Find all jobs
	 */
	public List<Job> findAll(){
		return jobRepository.findAll();
	}
	
	/**
	 * 
	 * @param jobName
	 * @throws JobNotFoundException
	 * Delete a job by its job name
	 */
	public void deleteJob(String jobName) throws JobNotFoundException {
		 Job findJob = jobRepository.findByJobName(jobName);
		if( findJob != null) {
			jobRepository.delete(findJob);
			return;
		}else {
			throw new JobNotFoundException("Delete failed. No job found with the job Name:" + jobName);
		}
	}
	
	/**
	 * 
	 * @param job
	 * @throws JobNotFoundException
	 * Update a job
	 * search the job by id first and update
	 */
	public Job updateJob(Job job) throws JobNotFoundException {
		Job searchJob = jobRepository.findByJobId(job.getJobId());
		if(searchJob != null) {
			return jobRepository.save(job);
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}
	
	
	public Job updateJob(Long jobId, MultipartFile file) throws JobNotFoundException, IllegalStateException, IOException {
		Job searchJob = jobRepository.findByJobId(jobId);
		if(searchJob != null) {
		
			
			 File convFile = new File(file.getOriginalFilename());
			    convFile.createNewFile();
			    FileOutputStream fos = new FileOutputStream(convFile); 
			    fos.write(file.getBytes());
			    fos.close(); 
			    
			searchJob.setAssessmentFile(file.getOriginalFilename());
			return jobRepository.save(searchJob);
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}
	
	public List<QuestionDto> findQuiz(long jobId) throws JobNotFoundException, EncryptedDocumentException, InvalidFormatException, IOException {
		List<QuestionDto> questionDtos = new ArrayList<>();
		Job searchJob = jobRepository.findByJobId(jobId);
		if(searchJob != null) {
			InputStream file = new FileInputStream(searchJob.getAssessmentFile());
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
				questionDto.setPossibleAnswers(possibleAns);
				questionDtos.add(questionDto);
			}
			return questionDtos;
		}else {
			throw new JobNotFoundException("Failed to update job");
		}
	}
	
	public List<Job> findActiveJobs(String eid) throws JobNotFoundException
	{
		List<BigInteger> jobIds = jobRepository.fingActiveJob(eid);
		
		if (jobIds.isEmpty())
		{
			throw new JobNotFoundException("Active Job Not found");
		}
		
		List<Job> allJobs = new ArrayList<>();
		for(BigInteger jobId : jobIds)
		{
			allJobs.add(jobRepository.findByJobId(jobId.longValue()));
		}
		return allJobs;
	}
	

}
