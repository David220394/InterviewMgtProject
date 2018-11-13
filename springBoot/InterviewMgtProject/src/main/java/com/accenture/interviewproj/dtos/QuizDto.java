package com.accenture.interviewproj.dtos;

import java.util.List;

public class QuizDto {
	
	String quizName;
	
	List<QuestionDto> questionDtos;

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public List<QuestionDto> getQuestionDtos() {
		return questionDtos;
	}

	public void setQuestionDtos(List<QuestionDto> questionDtos) {
		this.questionDtos = questionDtos;
	}
	
	

}
