import { Component, OnInit, Input } from '@angular/core';
import { Interview } from '../../dtos/interview';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { InterviewService } from '../../providers/interview.service';
import { Question } from '../../dtos/question';

export interface Quiz{
  id : number;
  question : string;
  possibleAnswer : string[];
  answer : string;
}

@Component({
  selector: 'app-iq-assessment',
  templateUrl: './iq-assessment.component.html',
  styleUrls: ['./iq-assessment.component.scss']
})
export class IqAssessmentComponent implements OnInit {

  questions : Question[];//Represent the list of question in the quiz
  quizName : string;
  questionForm : FormGroup; //Object Representation of the quiz form
  @Input() interview : Interview;//Interview Object obtain when accessing the link

  constructor(private fb : FormBuilder, private interviewService : InterviewService) { }

  ngOnInit() {
    //Initiallize quiz form
    this.questionForm = this.fb.group({
      questions : this.fb.array([])
    });
    //Obtain the Quiz Question and Quiz name from the REST API
    this.interviewService.quizQuestions(this.interview.jobId).subscribe(data =>{
      this.questions = data.questions;
      this.quizName = data.quizName;
      console.log(data);
      this.questions.forEach((question)=>{ //For each question; Generate a form control object
        const control = <FormArray>this.questionForm.controls['questions'];
        control.push(this.initQuestions(question));
      })
    });
  }
  //Initialize the form control obj with a question, the mark and the ans of the candidate
  initQuestions(question : Question){
    return this.fb.group({
      question : question.question,
      mark : question.mark,
      answer : ['']
    });
  }

  save(questionForm){
    this.interviewService.updateAssessmentInterview(this.quizName,questionForm.value,this.interview.link);
    console.log("Reactive Form submitted: ", this.questionForm);
  }

}
