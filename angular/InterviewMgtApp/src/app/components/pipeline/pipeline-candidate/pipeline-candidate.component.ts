import { Component, OnInit, Input } from '@angular/core';
import { Candidate } from '../dto/candidate';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pipeline-candidate',
  templateUrl: './pipeline-candidate.component.html',
  styleUrls: ['./pipeline-candidate.component.scss']
})
export class PipelineCandidateComponent implements OnInit {

  @Input() candidate:Candidate;

  constructor(private router : Router) { }

  ngOnInit() {
  }

  onClickRedirect(): void{
    this.router.navigateByUrl('/candidatePage');
  }

}
