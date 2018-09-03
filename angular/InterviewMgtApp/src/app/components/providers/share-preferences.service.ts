import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharePreferencesService {

  candidateId : number;
  jodId : number;

  constructor() { }

  setCandidateId(candidateId : number){
     localStorage.setItem('candidateId', candidateId.toString() )
  }

  getCandidateId(): string{
    return localStorage.getItem('candidateId');
  }

  setJobId(jobId : number){
    localStorage.setItem('jobId',jobId.toString())
  }

  getJobId(): string{
    return localStorage.getItem('jobId');
  }









}


