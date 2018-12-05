import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { InterviewService } from '../candidate-profile/providers/interview.service';
import { LoginService } from '../login-dialog/providers/login.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit {

  myForm: FormGroup;
  errMsg : string;

  constructor(private loginService: LoginService,private _fb: FormBuilder, private interviewService : InterviewService) { }

  ngOnInit() {
    this.loginService.isValidUser();
    this.myForm = this._fb.group({
      quizName: ['', [Validators.required, Validators.minLength(5)]],
      questions: this._fb.array([// Initialise a form array
          this.initQuestion(), //Initialise 1 form group for question in the array
      ],)
  });
}
/**
 * Create a form group representing a question
 */
initQuestion() {
  return this._fb.group({
      question: ['', Validators.required],
      correctAnswer: ['', Validators.required],
      answers: this._fb.array([
        this.initAnswer(), //Initialise 1 form group for answer in the array
        this.initAnswer()
      ])
  });
}
/**
 * Create a form group representing an answer
 */
initAnswer() {
return this._fb.group({
  answer: ['', Validators.required]
})
}

addQuestion() {
const control = <FormArray>this.myForm.controls['questions'];
control.push(this.initQuestion());
}

removeQuestion(i: number) {
const control = <FormArray>this.myForm.controls['questions'];
control.removeAt(i);
}

addAnswer(ques): void {
const control = <FormArray>ques.controls.answers;
control.push(this.initAnswer());
}

removeAnswer(ques,j: number) {
const control = <FormArray>ques.controls.answers;
control.removeAt(j);
}

/*ageRangeValidator(correctAnswer: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
      if (control.value !== undefined && (isNaN(control.value) || control.value < min || control.value > max)) {
          return { 'ageRange': true };
      }
      return null;
  };
}*/



save(formData) {
if(formData.valid){
let quiz = formData.value;
let check : boolean[]=[];
let valid = true;
quiz.questions.forEach(ques=>{
  let ansCheck = false;
  ques.answers.forEach(ans=>{
    if(ques.correctAnswer == ans.answer){
      ansCheck = true;
    }
  })
  check.push(ansCheck);
})
check.forEach(c=>{
  if(!c){
    valid = false;
  }
})
if(valid){
quiz.questions.forEach(question => {
    let answers :string[] =[];
    question.answers.forEach(element => {
      answers.push(element.answer);
    });
    question.answers = answers;
});
console.log(quiz);
this.interviewService.createQuiz(quiz);
}else{
  this.errMsg = "Answers and Correct Answers does not match";
}
}
}

}
