import { Component, OnInit, Input } from '@angular/core';
import { AfterInterview } from '../dtos/afterInterview';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

  @Input() afterInterview: AfterInterview;
  score: number;
  type: string;

  constructor() { }

  ngOnInit() {
    this.score = ((this.afterInterview.score / this.afterInterview.maxScore) * 100);
    if (this.afterInterview.type === 'HR') {
      this.type = 'HR Interviewer';
    } else if (this.afterInterview.type === 'TECH'){
      this.type = 'Technical Interviewer';
    } else {
      this.type = 'IQ Assessment Score'
    }
  }

}
