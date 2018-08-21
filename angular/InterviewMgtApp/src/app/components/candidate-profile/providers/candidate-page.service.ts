import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { Experience } from '../dtos/experience';
import { Candidate } from '../dtos/candidate';
import { Skill } from '../dtos/skill';

@Injectable({
  providedIn: 'root'
})
export class CandidatePageService {

  constructor(private _httpClient : HttpClient) { }

  public getCandidateById(id : number): Observable<Candidate> {
    return new Observable( observer => {
      this._httpClient.get('http://localhost:8082/candidate/'+id)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {

        let candidate : Candidate;
        const experiences: Experience[] = [];
        const skills : Skill[] = [];
        const phones : number[] = [];

        data.candidateExperiences.forEach( experience => {
          experiences.push({
            name : experience.experienceName,
            duration : experience.duration
          });
        });

        data.skills.forEach(skill => {
          skills.push({
            description : skill.description,
            grade : skill.grade,
            location : skill.location
          })
        });

        data.candidatePhones.forEach(phone => {
          phones.push(phone);
        });

        candidate ={
          name : data.candidateName,
          address : data.candidateAddress,
          email : data.email,
          availability : data.availability,
          score : data.score,
          cover : data.coverLetter,
          phones : phones,
          status : data.status,
          experiences : experiences,
          skills : skills
        }
        observer.next(candidate);
      },
      error =>{
        observer.error(error);
      });
    });
  }
}
