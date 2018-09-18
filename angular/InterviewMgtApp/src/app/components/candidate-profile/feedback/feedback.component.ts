import { Component, OnInit, Input } from '@angular/core';
import { AfterInterview } from '../dtos/afterInterview';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

  @Input() afterInterview  :AfterInterview;

  constructor() { }

  ngOnInit() {
  }

}
