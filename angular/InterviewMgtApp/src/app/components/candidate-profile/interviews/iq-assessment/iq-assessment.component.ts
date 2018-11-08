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

  questions : Question[];

  questionForm : FormGroup;
  @Input() interview : Interview;

  constructor(private fb : FormBuilder, private interviewService : InterviewService) { }

  ngOnInit() {
    this.questionForm = this.fb.group({
      questions : this.fb.array([

      ])
    });
    this.interviewService.quizQuestions(this.interview.jobId).subscribe(data =>{
      this.questions = data;
      console.log(data);



      this.questions.forEach((question)=>{
        const control = <FormArray>this.questionForm.controls['questions'];
        control.push(this.initQuestions(question));
      })
    });

  }

  initQuestions(question : Question){
    return this.fb.group({
      question : question.question,
      mark : question.mark,
      answer : ['']
    });
  }

  save(questionForm){
    console.log("Reactive Form submitted: ", this.questionForm);
  }

}
