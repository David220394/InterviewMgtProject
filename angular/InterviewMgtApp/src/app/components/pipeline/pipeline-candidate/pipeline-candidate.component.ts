import { Component, OnInit, Input } from '@angular/core';
import { Candidate } from '../dto/candidate';
import { Router } from '@angular/router';
import { SharePreferencesService } from '../../providers/share-preferences.service';

@Component({
  selector: 'app-pipeline-candidate',
  templateUrl: './pipeline-candidate.component.html',
  styleUrls: ['./pipeline-candidate.component.scss']
})
export class PipelineCandidateComponent implements OnInit {

  @Input() candidate:Candidate;

  constructor(private router : Router, private sharePreferences : SharePreferencesService) { }

  ngOnInit() {
  }

  onClickRedirect(): void{
    this.sharePreferences.setCandidateId(this.candidate.id);
    this.router.navigateByUrl('/candidatePage');
  }

}
