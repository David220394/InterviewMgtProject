package com.accenture.interviewproj.services;

import org.springframework.stereotype.Service;

import com.accenture.interviewproj.dtos.AfterAssessmentDto;
import com.accenture.interviewproj.dtos.AfterAssessmentQuestionDto;
import com.accenture.interviewproj.dtos.QuestionDto;
import com.accenture.interviewproj.dtos.QuizDto;
import com.accenture.interviewproj.entities.AssessmentQuiz;
import com.accenture.interviewproj.entities.Interview;
import com.accenture.interviewproj.entities.QuizQuestion;
import com.accenture.interviewproj.repositories.AssessmentQuizRepositorty;
import com.accenture.interviewproj.repositories.InterviewRepository;
import com.accenture.interviewproj.repositories.QuizQuestionRepository;

@Service
public class AssessmentQuizService {
	
	
	private final AssessmentQuizRepositorty assessmentQuizRepositorty;
	
	private final QuizQuestionRepository questionRepository;
	
	private final InterviewRepository interviewRepository;
	
	public AssessmentQuizService(AssessmentQuizRepositorty assessmentQuizRepositorty,
			QuizQuestionRepository questionRepository, InterviewRepository interviewRepository) {
		this.assessmentQuizRepositorty = assessmentQuizRepositorty;
		this.questionRepository = questionRepository;
		this.interviewRepository = interviewRepository;
	}
	
	public AssessmentQuiz insertAssessmentQuiz(QuizDto quizDto) {
		AssessmentQuiz quiz = new AssessmentQuiz();
		quiz.setQuizName(quizDto.getQuizName());
		assessmentQuizRepositorty.save(quiz);
		for (QuestionDto questionDto : quizDto.getQuestions()) {
			QuizQuestion question = new QuizQuestion();
			question.setQuestion(questionDto.getQuestion());
			question.setAssessmentQuiz(quiz);
			question.setCorrectAnswer(questionDto.getCorrectAnswer());
			question.setMark(questionDto.getMark());
			question.setPossibleAnswers(questionDto.getAnswers());
			questionRepository.save(question);
		}
		return quiz;
	}
	
	public Interview saveAssessmentScore(AfterAssessmentDto assessmentDto) {
		double score = 0;
		Interview interview = interviewRepository.findByLink(assessmentDto.getInterviewLink());
		AssessmentQuiz assessmentQuiz = assessmentQuizRepositorty.findByQuizName(assessmentDto.getQuizName());
		for (AfterAssessmentQuestionDto questionDto : assessmentDto.getAfterAssessmentQuestionDtos()) {
			for(QuizQuestion question : assessmentQuiz.getQuizQuestions()) {
				if(question.getQuestion().equals(questionDto.getQuestion()) && question.getCorrectAnswer().equals(questionDto.getAnswer())) {
					score += question.getMark();
				}
			}
		}
		interview.setScore(score);
		interview.setMaxScore(assessmentDto.getMaxScore());
		interview.setCompleted(true);
		interviewRepository.save(interview);
		return interview;
	}

}
