import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Candidate } from '../dto/candidate';
import { Observable } from 'rxjs';
import { map, finalize } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import { Job } from '../dto/job';
import { element } from 'protractor';

@Injectable({
  providedIn: 'root'
})
export class PipelineCandidateService {

  constructor(private httpClient:HttpClient) { }

  candidatesApplied: Candidate[];
  candidatesInterviewScheduled: Candidate[];
  candidatesInterviewInProgress: Candidate[];
  candidatesCompleted: Candidate[];
  candidatesRejected: Candidate[];

  public getAlljob() : Observable<any>{
    return new Observable( observer => {
      this.httpClient.get(environment.url+'/jobs/')
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
        let jobs : Job[] = [];

        data.forEach(job => {
          jobs.push({
            id : job.jobId,
            name : job.jobName
          });
        });
        observer.next(jobs);
      },
      error =>{
        observer.error(error);
      }
    )});
  }


  /*public getallcandidates():Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/job/all/")
    .pipe(map((contents:any):void => {
      contents.forEach((element:any)=> {
        this.candidates.push({
          rating:element.rating,
          name: element.name,
          picture:element.picture,
          title:element.title,
          education:element.education
        })
      });
    }))
  }*/


  public getallcandidatesbyjob(jobid: number):Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/"+ jobid)
    .pipe(map((contents:any):void => {
      this.candidatesApplied = [];
      this.candidatesInterviewScheduled = [];
      this.candidatesInterviewInProgress =[];
      this.candidatesCompleted = [];
      this.candidatesRejected = [];
      let candidates : Candidate[] = [];
      contents.forEach((element:any)=> {
        candidates.push({
          id : element.candidateId,
          rating:element.score,
          name: element.candidateName,
          picture:null,
          title:element.candidateExperience.experienceName,
          education:element.education.programStrudy,
          status : element.status.statusName
        })
      });

      candidates.forEach((element:Candidate)=>{
        switch(element.status){
          case "Applied":{
            this.candidatesApplied.push(element);
            break;
          }
          case "Scheduled Interview":{
            this.candidatesInterviewScheduled.push(element);
            break;
          }
          case "Interview In progress":{
            this.candidatesInterviewInProgress.push(element);
            break;
          }
          case "Completed":{
            this.candidatesCompleted.push(element);
            break;
          }
          case "Rejected":{
            this.candidatesRejected.push(element);
            break;
          }
        }
      });


    }))
  }


}
