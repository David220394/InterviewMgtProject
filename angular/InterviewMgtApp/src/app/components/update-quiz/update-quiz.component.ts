import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { LoginService } from '../login-dialog/providers/login.service';
import { UpdateQuizService } from './provider/update-quiz.service';
import { UpdateQuiz } from './dto/update-quiz';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.scss']
})
export class UpdateQuizComponent implements OnInit {

  myForm: FormGroup;
  updateQuizs : UpdateQuiz[];
  selected?: number;


  constructor(private loginService: LoginService,private _fb: FormBuilder, private updateQuizService : UpdateQuizService) { }

  ngOnInit() {

    this.loginService.isValidUser();
    this.updateQuizService.getAllQuiz().subscribe((data:any)=>{
      this.updateQuizs=data;
    });

}

changeView(): void{
  console.log(this.selected);
  this.myForm = this._fb.group({
    quizName: ['', [Validators.required, Validators.minLength(5)]],
    questions: this._fb.array([
    ])
});
  this.updateQuizService.getQuizById(this.selected).subscribe((data:any)=>{
    this.myForm = this._fb.group({
      quizName: [data.quizName, [Validators.required, Validators.minLength(5)]],
      questions: this._fb.array([
      ])
  });
    data.questions.forEach(question => {
      const control = <FormArray>this.myForm.controls['questions'];
        control.push(this.initQuestions(question));
    });
  });

}

initQuestions(question) {
  let quesForm = this._fb.group({
      question: [question.question, Validators.required],
      correctAnswer: [question.correctAnswer, Validators.required],
      answers: this._fb.array([
      ])
  });
  question.answers.forEach(ans => {
    const control = <FormArray>quesForm.controls['answers'];
        control.push(this.initAnswers(ans));
  });

  return quesForm;
}

initAnswers(ans) {
return this._fb.group({
  answer: [ans, Validators.required]
})
}

initQuestion() {
  return this._fb.group({
      question: ['', Validators.required],
      correctAnswer: ['', Validators.required],
      answers: this._fb.array([
        this.initAnswer()
      ])
  });
}

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

save(formData) {
if(formData.valid){
let quiz = formData.value;

quiz.questions.forEach(question => {
    let answers :string[] =[];
    question.answers.forEach(element => {
      answers.push(element.answer);
    });
    question.answers = answers;
});
console.log(quiz);
this.updateQuizService.updateQuiz(quiz);
}
}

}
