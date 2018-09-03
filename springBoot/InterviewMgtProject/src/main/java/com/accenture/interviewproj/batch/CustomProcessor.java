package com.accenture.interviewproj.batch;



import java.io.File;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.utilities.CandidateUtility;

@Component
public class CustomProcessor implements ItemProcessor<Row, CandidateDto>{
	
	private String path;
	
	public CustomProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomProcessor(String path) {
		this.path = path;
	}

	@Override
	public CandidateDto process(Row row) throws Exception {
		CandidateDto candidateDto = CandidateUtility.getCandidateFromExcel(row);
		File cv = new File(this.path+"/cv/"+row.getCell(1).getStringCellValue()+".pdf");
		candidateDto.setCandidateCv(cv);
		return candidateDto;
		
		
	}

}
