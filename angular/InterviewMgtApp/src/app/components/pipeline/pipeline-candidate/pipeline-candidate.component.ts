import { Component, OnInit, Input } from '@angular/core';
import { Candidate } from '../dto/candidate';

@Component({
  selector: 'app-pipeline-candidate',
  templateUrl: './pipeline-candidate.component.html',
  styleUrls: ['./pipeline-candidate.component.scss']
})
export class PipelineCandidateComponent implements OnInit {

  @Input() candidate:Candidate;

  constructor() { }

  ngOnInit() {
  }

}
