package com.accenture.interviewproj.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.QuizDto;
import com.accenture.interviewproj.services.AssessmentQuizService;
import com.accenture.interviewproj.services.CandidateService;
import com.accenture.interviewproj.services.JobService;

/**
 * Custom Writer class To read the output(CandidateDto) from processor Stored
 * the candidate in the database.
 */
@Component
public class CustomWriterQuiz implements ItemWriter<QuizDto> {

	@Autowired
	private AssessmentQuizService assessmentQuizService;

	@Override
	public void write(List<? extends QuizDto> items) throws Exception {
		for (QuizDto quizDto : items) {
			assessmentQuizService.insertAssessmentQuiz(quizDto);
		}
	}

}
