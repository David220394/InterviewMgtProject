package com.accenture.interviewproj.batch;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.utilities.CandidateUtility;
import com.accenture.interviewproj.utilities.JobUtility;

/**
 *Custom Processor class 
 *To read output(Row) from the reader() process
 *To write (CandidateDto) to the writer() process
 */
@Component
public class CustomProcessorJob implements ItemProcessor<Row, JobDto>{
	
	
	public CustomProcessorJob() {	}
	
	

	@Override
	public JobDto process(Row row) throws Exception {
		JobDto jobDto = JobUtility.getJobFromExcel(row);
		return jobDto;
	}

}
