import { Injectable } from '@angular/core';
import { Tracking } from '../../dtos/tracking';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { catchError, map, finalize } from 'rxjs/operators';
import { Contact } from '../../dtos/contact';

@Injectable({
  providedIn: 'root'
})
export class TrackingService {

  constructor(private http : HttpClient) { }

  public insertTracking(contact : Contact){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    this.http.post<Contact>(environment.url + '/tracking/',contact, httpOptions).subscribe((data : any)=>{
        console.log(data);
    }
    );
  }

  public findTrackingByJobIdAndCandidateId(jobId : string, candidateId : string): Observable<Tracking[]>{
    return new Observable( observer => {
      this.http.get(environment.url + '/tracking/'+jobId+'/'+candidateId)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
      let trackings : Tracking[]=[];
      data.forEach((element:any)=> {
        trackings.push({
          candidateName : element.candidate.candidateName,
          employeeName : element.employee.employeeName,
          jobName : element.job.jobName,
          creationDate : element.creationDate,
          comment : element.comment
        })
      });
      console.log(trackings);
      observer.next(trackings);
    },
    error =>{
      observer.error(error);
      });
    });
  }

}
