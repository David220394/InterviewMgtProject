import { Component, OnInit } from '@angular/core';

import {Job} from './dto/job';
import { Candidate } from './dto/candidate';
import { PipelineCandidateService } from './providers/pipeline-candidate.service';
import { FormGroup } from '@angular/forms';
import { SharePreferencesService } from '../providers/share-preferences.service';

@Component({
  selector: 'app-pipeline',
  templateUrl: './pipeline.component.html',
  styleUrls: ['./pipeline.component.scss']
})
export class PipelineComponent implements OnInit {

  constructor(private service:PipelineCandidateService, private sharePreferences : SharePreferencesService) { }

    jobs: Job[];

    selected?: number;

    candidatesApplied: Candidate[];
    candidatesInterviewScheduled: Candidate[];
    candidatesInterviewInProgress: Candidate[];
    candidatesCompleted: Candidate[];
    candidatesRejected: Candidate[];



  ngOnInit(): void {

    this.service.getAlljob().subscribe((data: any)=>{
      this.jobs = data;
    }
    )

  }

  changeView(): void{
    this.sharePreferences.setJobId(this.selected);
    this.service.getallcandidatesbyjob(this.selected).subscribe(
      () => {
        this.candidatesApplied = this.service.candidatesApplied,
        this.candidatesInterviewScheduled = this.service.candidatesInterviewScheduled,
        this.candidatesInterviewInProgress = this.service.candidatesInterviewInProgress,
        this.candidatesCompleted = this.service.candidatesCompleted,
        this.candidatesRejected = this.service.candidatesRejected
        },error => {console.error(error)}
    );
  }

}



