package com.accenture.interviewproj.batch;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.dtos.QuizDto;
import com.accenture.interviewproj.utilities.CandidateUtility;
import com.accenture.interviewproj.utilities.JobUtility;

/**
 *Custom Processor class 
 *To read output(Row) from the reader() process
 *To write (CandidateDto) to the writer() process
 */
@Component
public class CustomProcessorQuiz implements ItemProcessor<File, QuizDto>{
	
	
	public CustomProcessorQuiz() {	}
	
	

	@Override
	public QuizDto process(File file) throws Exception {
		List<Row> rows = new ArrayList<>();
		List<QuestionDto> questionDtos = new ArrayList<>();
		QuizDto quizDto = new QuizDto();
		Workbook workbook =  WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		Iterator<Row> rowIterator = sheet.rowIterator();
		quizDto.setQuizName(rowIterator.next().getCell(0).getStringCellValue());
		rowIterator.next();
		while(rowIterator.hasNext()) {
			rows.add(rowIterator.next());
		}
		for (Row row : rows) {
			questionDtos.add(JobUtility.getquestionFromExcel(row));
		}
		quizDto.setQuestions(questionDtos);
		return quizDto;
	}

}
