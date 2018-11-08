import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { Experience } from '../dtos/experience';
import { Candidate } from '../dtos/candidate';
import { Skill } from '../dtos/skill';
import { environment } from '../../../../environments/environment';
import { Education } from '../dtos/education';

@Injectable({
  providedIn: 'root'
})
export class CandidatePageService {

  constructor(private http : HttpClient) { }



  /**
   * obtain a candidate from server using jobId and candidateId
   * @param jobId
   * @param cid
   */
  public getCandidateById(jobId : string,cid : string): Observable<Candidate> {
    console.log(jobId+','+cid);
    return new Observable( observer => {
      this.http.get(environment.url+'/candidate/'+jobId+'/'+cid)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {

        let candidate : Candidate;
        const experience: Experience  = {
          name : data.candidateExperience.experienceName,
          specialty : data.candidateExperience.specialty,
          location : data.candidateExperience.location
        };
        const education : Education = {
          institutionName : data.education.institutionName,
          grade : data.education.grade,
          programStudy : data.education.programStudy
        };

        candidate ={
          name : data.candidateName,
          address : data.candidateAddress,
          gender : data.gender,
          dob : data.dob,
          applicationDate : data.applicationDate,
          education : education,
          email : data.email,
          completeApplication : data.completeApplication,
          internalApplication : data.internalApplication,
          rehire : data.rehire,
          availability : data.availability,
          score : data.score,
          cover : data.coverLetter,
          phone : data.candidatePhone,
          status : data.status,
          experience : experience,
          skills : null,
          jobName : data.jobName
        }
        observer.next(candidate);
      },
      error =>{
        observer.error(error);
      });
    });
  }

  /**
   * obtain a candidate from server using jobId and candidateId
   * @param jobId
   * @param cid
   */
  public getCandidateByCandidateId(cid : string): Observable<Candidate> {
    console.log(cid);
    return new Observable( observer => {
      this.http.get(environment.url+'/candidate/candidatePage/'+cid)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
        console.log(data)
        let candidate : Candidate;
        const experience: Experience  = {
          name : data.candidateExperience.experienceName,
          specialty : data.candidateExperience.specialty,
          location : data.candidateExperience.location
        };
        const education : Education = {
          institutionName : data.education.institutionName,
          grade : data.education.grade,
          programStudy : data.education.programStudy
        };

        candidate ={
          name : data.candidateName,
          address : data.candidateAddress,
          gender : data.gender,
          dob : data.dob,
          applicationDate : data.applicationDate,
          education : education,
          email : data.email,
          completeApplication : data.completeApplication,
          internalApplication : data.internalApplication,
          rehire : data.rehire,
          availability : data.availability,
          cover : data.coverLetter,
          phone : data.candidatePhone,
          experience : experience,
          skills : null
        }
        observer.next(candidate);
      },
      error =>{
        observer.error(error);
      });
    });
  }

  addJob(jobId : number, cid : string) : Observable<any>{
    return new Observable( observer => {
      this.http.get(environment.url+'/candidate/addJob/'+jobId+'/'+cid)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe((data:any)=>{
        observer.next(data);
      },(err:any)=>{
        observer.error(err);
      })
    });
  }
}
