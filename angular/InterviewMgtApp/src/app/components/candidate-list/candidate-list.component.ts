import { Component, OnInit } from '@angular/core';
import { PipelineCandidateService } from '../pipeline/providers/pipeline-candidate.service';
import { Candidate } from '../pipeline/dto/candidate';

@Component({
  selector: 'app-candidate-list',
  templateUrl: './candidate-list.component.html',
  styleUrls: ['./candidate-list.component.scss']
})
export class CandidateListComponent implements OnInit {

  candidates : Candidate[];
  constructor(private pipelineService : PipelineCandidateService) { }

  ngOnInit() {
    this.pipelineService.getallcandidates().subscribe((data:any)=>{
      this.candidates = data;
    })
  }

}
