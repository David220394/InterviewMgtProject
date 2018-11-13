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
  @Input() selected : number;

  constructor(private router : Router, private sharePreferences : SharePreferencesService) { }

  ngOnInit() {
  }

  onClickRedirect(): void{

    this.sharePreferences.setCandidateId(this.candidate.id);
    this.sharePreferences.setCandidateName(this.candidate.name);
    if(this.selected){
      console.log(this.selected);
    this.router.navigateByUrl('/candidatePage/'+this.selected);
    }else{
      this.router.navigateByUrl('/candidatePage');
    }

  }

}
