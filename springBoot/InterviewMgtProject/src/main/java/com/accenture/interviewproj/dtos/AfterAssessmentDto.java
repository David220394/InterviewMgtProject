package com.accenture.interviewproj.dtos;

import java.util.List;

public class AfterAssessmentDto {
	
	private Long quizId;
	
	private String interviewLink;
	
	private List<AfterAssessmentQuestionDto> afterAssessmentQuestionDtos;

	public List<AfterAssessmentQuestionDto> getAfterAssessmentQuestionDtos() {
		return afterAssessmentQuestionDtos;
	}

	public void setAfterAssessmentQuestionDtos(List<AfterAssessmentQuestionDto> afterAssessmentQuestionDtos) {
		this.afterAssessmentQuestionDtos = afterAssessmentQuestionDtos;
	}


	public String getInterviewLink() {
		return interviewLink;
	}

	public void setInterviewLink(String interviewLink) {
		this.interviewLink = interviewLink;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}
	
	

}
