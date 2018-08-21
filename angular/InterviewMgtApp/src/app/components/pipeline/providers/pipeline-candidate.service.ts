import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Candidate } from '../dto/candidate';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PipelineCandidateService {

  constructor(private httpClient:HttpClient) { }

  private candidates: Candidate[] = [];

  get candidatesList():Candidate[]{
    return this.candidates;
  }

  private getall():HttpParams{
    return new HttpParams();
  }

  private getbyjobid(jobid: string): HttpParams{
    return new HttpParams()
    .set("jobid", jobid.toString());
  }

  public getallcandidates():Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/job/all/", {params: this.getall()})
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
  }


  public getallcandidatesbyjob(jobid: string):Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/job/"+jobid)
    .pipe(map((contents:any):void => {
      this.candidates = [];
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
  }


}
