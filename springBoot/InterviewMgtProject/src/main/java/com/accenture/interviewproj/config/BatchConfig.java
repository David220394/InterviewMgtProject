package com.accenture.interviewproj.config;

import java.io.File;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.interviewproj.batch.CustomProcessor;
import com.accenture.interviewproj.batch.CustomProcessorJob;
import com.accenture.interviewproj.batch.CustomProcessorQuiz;
import com.accenture.interviewproj.batch.CustomReader;
import com.accenture.interviewproj.batch.CustomReaderJob;
import com.accenture.interviewproj.batch.CustomReaderQuiz;
import com.accenture.interviewproj.batch.CustomWriter;
import com.accenture.interviewproj.batch.CustomWriterJob;
import com.accenture.interviewproj.batch.CustomWriterQuiz;
import com.accenture.interviewproj.dtos.CandidateDto;
import com.accenture.interviewproj.dtos.JobDto;
import com.accenture.interviewproj.dtos.QuizDto;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private final String candidatePath = System.getProperty("user.dir") + "/src/main/resources/Candidate";
	private final String jobPath = System.getProperty("user.dir") + "/src/main/resources/Job";
	private final String quizPath = System.getProperty("user.dir") + "/src/main/resources/quiz";
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@StepScope
	@Bean
	public ItemReader<Row> reader() {
	    return new CustomReader(candidatePath);
	}
	
	@StepScope
	@Bean
	public ItemReader<Row> readerJob() {
	    return new CustomReaderJob(jobPath);
	}
	
	@StepScope
	@Bean
	public ItemReader<File> readerQuiz() {
	    return new CustomReaderQuiz(quizPath);
	}
	
	@StepScope
	@Bean
	public ItemProcessor<Row, CandidateDto> processor() {
	    return new CustomProcessor(candidatePath);
	}
	
	@StepScope
	@Bean
	public ItemProcessor<Row, JobDto> processorJob() {
	    return new CustomProcessorJob();
	}
	
	@StepScope
	@Bean
	public ItemProcessor<File, QuizDto> processorQuiz() {
	    return new CustomProcessorQuiz();
	}
	
	@StepScope
	@Bean
	public ItemWriter<CandidateDto> writer() {
	    return new CustomWriter();
	}
	
	@StepScope
	@Bean
	public ItemWriter<JobDto> writerJob() {
	    return new CustomWriterJob();
	}
	
	@StepScope
	@Bean
	public ItemWriter<QuizDto> writerQuiz() {
	    return new CustomWriterQuiz();
	}
	
	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(step1()).next(step2()).next(step3()).end().build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Row, CandidateDto>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Row, JobDto>chunk(10).reader(readerJob()).processor(processorJob())
				.writer(writerJob()).build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").<File, QuizDto>chunk(10).reader(readerQuiz()).processor(processorQuiz())
				.writer(writerQuiz()).build();
	}
	
	

}
