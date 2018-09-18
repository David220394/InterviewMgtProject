import { Component, OnInit } from '@angular/core';
import { InterviewService } from '../providers/interview.service';
import { ActivatedRoute } from '@angular/router';
import { Interview } from '../dtos/interview';
import { Candidate } from '../dtos/candidate';

@Component({
  selector: 'app-interviews',
  templateUrl: './interviews.component.html',
  styleUrls: ['./interviews.component.scss']
})
export class InterviewsComponent implements OnInit {
  link : string;
  interview : Interview;

  constructor(private interviewService : InterviewService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.link = this.route.snapshot.paramMap.get('link');
    this.interviewService.accessInterviewPage(this.link).subscribe((data:any)=>{
      this.interview = data;
    });

  }

}
