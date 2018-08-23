import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Candidate } from '../dto/candidate';
import { Observable } from 'rxjs';
import { map, finalize } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import { Job } from '../dto/job';

@Injectable({
  providedIn: 'root'
})
export class PipelineCandidateService {

  constructor(private httpClient:HttpClient) { }

  private candidates: Candidate[] = [];

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


  get candidatesList():Candidate[]{
    return this.candidates;
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


  public getallcandidatesbyjob(jobid: string):Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/"+ jobid)
    .pipe(map((contents:any):void => {
      this.candidates = [];
      contents.forEach((element:any)=> {
        let experience : string = "";
        let description : string = "";
        this.candidates.push({
          rating:element.score,
          name: element.candidateName,
          picture:null,
          title:element.candidateExperience.experienceName,
          education:element.education.programStrudy
        })
      });
    }))
  }


}
