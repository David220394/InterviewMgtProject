import { Component, OnInit, Input } from '@angular/core';
import { Interview } from '../../dtos/interview';
import { Candidate } from '../../dtos/candidate';
import { FormGroup, FormBuilder, FormArray, FormControl, ValidatorFn, Validators } from '@angular/forms';
import { AfterInterview } from '../../dtos/afterInterview';
import { InterviewService } from '../../providers/interview.service';

export interface Question {
  number : number;
  question: string;
  point?: number;
  comment? : string;
}

@Component({
  selector: 'app-hr-interview',
  templateUrl: './hr-interview.component.html',
  styleUrls: ['./hr-interview.component.scss']
})
export class HrInterviewComponent implements OnInit {

  questionForm: FormGroup;
  questions : Question[] =[
    {number :1 , question : 'Was the candidate prepared for the interview? (Research company, dressed appropiately, arrived on time?)'},
    {number :2 , question : 'Does their experience appear to match what’s needed? (Work experience, life experience or volunteer work?)'},
    {number :3 , question : 'Do they have some or all of the required credentials? (For example, education, licenses, certifications?)'},
    {number :4 , question : 'How are their interpersonal skills? (Friendly, smiling, outgoing, kind, fun, interactive?)'},
    {number :5 , question : 'How good are their communication skills? (Written skills, i.e. resume, application, as well as verbal skills)'},
    {number :6 , question : 'How well do their technical skills match job requirements? (Specific technical tools, approaches, examples?)'},
    {number :7 , question : 'How well did they answer teamwork job related questions? (Likes working with others, good rapport?)'},
    {number :8 , question : 'How well did they answer customer service related questions? (Customer focused, good listener, problem solver?)'},
    {number :9 , question : 'How open did they appear to be when learning new things? (Willing to learn, attend training, accept feedback?)'},
    {number :10 , question : 'How interested did the candidate seem in getting the job? (In the job, they pay, the work requirements?)'},
  ]


  @Input() interview : Interview;

  candidate : Candidate;

  constructor(private fb : FormBuilder,private  interviewService : InterviewService) {
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
    console.log("Reactive Form submitted: ", this.questionForm);
    let afterInterview : AfterInterview;
    let score : number =0;
    let maxScore = (this.questions.length * 10);
    for(var i=0; i < this.questions.length; i++ ){
      this.questions[i].point = parseInt(questionForm.value.questions[i].point);
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
