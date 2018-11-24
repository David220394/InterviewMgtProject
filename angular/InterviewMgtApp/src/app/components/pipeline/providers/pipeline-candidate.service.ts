import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Candidate } from '../dto/candidate';
import { Observable, observable } from 'rxjs';
import { map, finalize } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import { Job } from '../dto/job';
import { element } from 'protractor';
import { LoginService } from '../../login-dialog/providers/login.service';

@Injectable({
  providedIn: 'root'
})
export class PipelineCandidateService {

  constructor(private httpClient: HttpClient,private loginService : LoginService) { }
  candidates: Candidate[];
  pipelineCandidate : Candidate[];
  candidatesApplied: Candidate[];
  candidatesInterviewScheduled: Candidate[];
  candidatesInterviewInProgress: Candidate[];
  candidatesCompleted: Candidate[];
  candidatesRejected: Candidate[];
  suggestedCandidates : Candidate[];

  public getAlljob(): Observable<any> {
    return new Observable(observer => {
      this.httpClient.get(environment.url + '/api/jobs/activeJob/'+this.loginService.getUsernameFromLocalStorage())
        .pipe(finalize(() => { observer.complete(); }))
        .subscribe((data: any) => {
          let jobs: Job[] = [];

          data.forEach(job => {
            jobs.push({
              id: job.jobId,
              name: job.jobName
            });
          });
          observer.next(jobs);
        },
          error => {
            observer.error(error);
          }
        )
    });
  }


  public getSuggestedcandidatesbyJob(jobId: number):Observable<any>{
    return this.httpClient.get(environment.url+"/candidate/suggest/"+jobId)
    .pipe(map((contents:any):void => {
        this.suggestedCandidates =[];
          let candidates: Candidate[] = [];
        contents.forEach((element: any) => {
          let skills : string[] = []
            element.skills.forEach(skill => {
              skills.push(skill.description)
            });
            candidates.push({
              id: element.candidateId,
              rating: element.score,
              name: element.candidateName,
              address : element.candidateAddress,
              picture: null,
              title: element.candidateExperience.experienceName,
              education: element.education.programStudy + " at " + element.education.institutionName,
              completeApplication: element.completeApplication,
              internalApplication: element.internalApplication,
              rehire: element.rehire,
              skills : skills,
              skillScore : element.skillScore,
              status: element.status
            })
        });
        this.suggestedCandidates=candidates;
    }))
  }


  public getallcandidatesbyjob(jobid: number): Observable<any> {
    return this.httpClient.get(environment.url + "/candidate/" + jobid)
      .pipe(map((contents: any): void => {
        console.log(contents);
        this.pipelineCandidate = [];
        this.candidatesApplied = [];
        this.candidatesInterviewScheduled = [];
        this.candidatesInterviewInProgress = [];
        this.candidatesCompleted = [];
        this.candidatesRejected = [];
        let candidates: Candidate[] = [];
        contents.forEach((element: any) => {
          let skills : string[] = []
            element.skills.forEach(skill => {
              skills.push(skill.description)
            });
            candidates.push({
              id: element.candidateId,
              rating: element.score,
              name: element.candidateName,
              address : element.candidateAddress,
              picture: null,
              title: element.candidateExperience.experienceName,
              education: element.education.programStudy + " at " + element.education.institutionName,
              completeApplication: element.completeApplication,
              internalApplication: element.internalApplication,
              rehire: element.rehire,
              skills : skills,
              skillScore : element.skillScore,
              status: element.status
            })
        });
        this.pipelineCandidate = candidates;
        candidates.forEach((element: Candidate) => {
          switch (element.status) {
            case "Applied": {
              this.candidatesApplied.push(element);
              break;
            }
            case "Scheduled Interview": {
              this.candidatesInterviewScheduled.push(element);
              break;
            }
            case "Interview In progress": {
              this.candidatesInterviewInProgress.push(element);
              break;
            }
            case "Completed": {
              this.candidatesCompleted.push(element);
              break;
            }
            case "Rejected": {
              this.candidatesRejected.push(element);
              break;
            }
          }
        });


      }))
  }

  public getallcandidates(): Observable<any> {
    return new Observable(observer => {
      this.httpClient.get(environment.url + '/candidate/')
        .pipe(finalize(() => { observer.complete(); }))
        .subscribe((data: any) => {
          console.log(data);
          this.candidates = [];
          data.forEach((element: any) => {
            let skills : string[] = []
            element.skills.forEach(skill => {
              skills.push(skill.description)
            });
            this.candidates.push({
              id: element.candidateId,
              rating: element.score,
              name: element.candidateName,
              address : element.candidateAddress,
              picture: null,
              title: element.candidateExperience.experienceName,
              education: element.education.programStudy + " at " + element.education.institutionName,
              completeApplication: element.completeApplication,
              internalApplication: element.internalApplication,
              rehire: element.rehire,
              skills : skills,
              skillScore : element.skillScore
            })
          });
          observer.next(this.candidates);
        },
          (err: any) => {
            observer.error(err);
          })
    })
  }


}
