import { Component, OnInit } from '@angular/core';
import { PipelineCandidateService } from '../pipeline/providers/pipeline-candidate.service';
import { Candidate } from '../pipeline/dto/candidate';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';

@Component({
  selector: 'app-candidate-list',
  templateUrl: './candidate-list.component.html',
  styleUrls: ['./candidate-list.component.scss']
})
export class CandidateListComponent implements OnInit {

  candidates : Candidate[];
  filteredCandidates : Candidate[];
  errMsg : string;
  selected : string;
  myForm: FormGroup;
  constructor(private _fb: FormBuilder,private pipelineService : PipelineCandidateService) { }

  ngOnInit() {
    this.myForm = this._fb.group({
      search: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]]
    })
    this.pipelineService.getallcandidates().subscribe((data:any)=>{
      this.candidates = data;
      this.filteredCandidates = this.candidates;
    })
  }

  search(myForm){
    if(myForm.valid){
      if(this.selected=='name'){
        this.filteredCandidates = this.candidates.filter(candidate =>candidate.name.toLowerCase().includes(myForm.value.search.toLowerCase()));
      }else if(this.selected=='skill'){
        this.filteredCandidates = [];
        this.filteredCandidates = this.candidates.filter(candidate =>candidate.skills.indexOf(myForm.value.search.toLowerCase()) != -1);
      }else if((this.selected=='location')){
        this.filteredCandidates = this.candidates.filter(candidate =>candidate.address.toLowerCase().includes(myForm.value.search.toLowerCase()));
      }
      if(this.filteredCandidates.length <1){
        this.errMsg="No candidate Found"
      }else{
        this.errMsg = null;
      }
    }
  }

}
