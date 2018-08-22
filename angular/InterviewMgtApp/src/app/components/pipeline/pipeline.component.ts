import { Component, OnInit } from '@angular/core';

import {Job} from './dto/job';
import { Candidate } from './dto/candidate';
import { PipelineCandidateService } from './providers/pipeline-candidate.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-pipeline',
  templateUrl: './pipeline.component.html',
  styleUrls: ['./pipeline.component.scss']
})
export class PipelineComponent implements OnInit {

  constructor(private service:PipelineCandidateService) { }

    job: Job[] = [
      {id: 'AB000', name: 'HR ADMIN'},
      {id: 'AB111', name: 'ADMIN'}
    ];

    public selected: string  = this.job[0].id;

    candidates: Candidate[];


  ngOnInit(): void {

    this.service.getallcandidatesbyjob(this.selected).subscribe(
      () => {this.candidates = this.service.candidatesList,
        console.log(this.candidates)},error => {console.error(error)}
    );

  }

  changeView(): void{
    this.service.getallcandidatesbyjob(this.selected).subscribe(
      () => {this.candidates = this.service.candidatesList,
        console.log(this.candidates)},error => {console.error(error)}
    );
  }

}



