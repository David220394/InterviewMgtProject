import { Component, OnInit, Input } from '@angular/core';
import { Interview } from '../../dtos/interview';
import { Validators, FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Candidate } from '../../dtos/candidate';
import { InterviewFeedback } from '../../dtos/interviewFeedback';
import { InterviewService } from '../../providers/interview.service';
import { AfterInterview } from '../../dtos/afterInterview';


@Component({
  selector: 'app-tech-interview',
  templateUrl: './tech-interview.component.html',
  styleUrls: ['./tech-interview.component.scss']
})
export class TechInterviewComponent implements OnInit {


  questionForm: FormGroup;
  questions : InterviewFeedback[] =[
    { question : 'Was the candidate prepared for the interview? (Research company, dressed appropiately, arrived on time?)'},
    { question : 'Does their experience appear to match what’s needed? (Work experience, life experience or volunteer work?)'},
    { question : 'Do they have some or all of the required credentials? (For example, education, licenses, certifications?)'},
    {question : 'How are their interpersonal skills? (Friendly, smiling, outgoing, kind, fun, interactive?)'},
    { question : 'How good are their communication skills? (Written skills, i.e. resume, application, as well as verbal skills)'},
    {question : 'How well do their technical skills match job requirements? (Specific technical tools, approaches, examples?)'},
    { question : 'How well did they answer teamwork job related questions? (Likes working with others, good rapport?)'},
    { question : 'How well did they answer customer service related questions? (Customer focused, good listener, problem solver?)'},
    {question : 'How open did they appear to be when learning new things? (Willing to learn, attend training, accept feedback?)'},
    { question : 'How interested did the candidate seem in getting the job? (In the job, they pay, the work requirements?)'},
  ]


  @Input() interview : Interview;

  candidate : Candidate;

  constructor(private fb : FormBuilder, private interviewService : InterviewService) {
   }



  ngOnInit() {
    this.candidate = this.interview.candidate;
    this.questionForm = this.fb.group({
      questions : this.fb.array([

      ]),
      feedback : ['']
    });

    this.questions.forEach(()=>{
      const control = <FormArray>this.questionForm.controls['questions'];
    control.push(this.initQuestions());
    })
  }

  initQuestions(){
    return this.fb.group({
      point : ['',Validators.required],
      comment : ['']
    });
  }

  save(questionForm){
    console.log("Reactive Form submitted: ", this.questionForm.value);
    let afterInterview : AfterInterview;
    let score : number =0;
    let maxScore = (this.questions.length * 10);
    for(var i=0; i < this.questions.length; i++ ){
      this.questions[i].mark = parseInt(questionForm.value.questions[i].point);
      score += parseInt(questionForm.value.questions[i].point);
      this.questions[i].comment = questionForm.value.questions[i].comment;
    }
    afterInterview ={
      link : this.interview.link,
      type : this.interview.type,
      feedback : questionForm.value.feedback,
      maxScore : maxScore,
      questions : this.questions,
      score : score
    }
      this.interviewService.updateInterview(afterInterview);
  }
}
