import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { InterviewService } from '../candidate-profile/providers/interview.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit {

  myForm: FormGroup;

  constructor(private _fb: FormBuilder, private interviewService : InterviewService) { }

  ngOnInit() {
    this.myForm = this._fb.group({
      quizName: ['', [Validators.required, Validators.minLength(5)]],
      questions: this._fb.array([
          this.initQuestion(),
      ])
  });
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
this.interviewService.createQuiz(quiz);
}
}

}
