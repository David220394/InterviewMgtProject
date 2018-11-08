import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpEventType, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { Interview } from '../dto/interview';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http : HttpClient) { }

    /**
   * uploadCandidate
   */
  public uploadCandidate(file : File){

    const status = {};
    const formData : FormData = new FormData();
    formData.append('file', file, file.name);
    this.http.post(environment.url + '/candidate/',formData,{
      reportProgress : true,
      observe : 'events'
    }).subscribe(event =>{
      if(event.type === HttpEventType.UploadProgress){
        console.log('Upload Progress: ' + Math.round(event.loaded / event.total * 100) + '%')
      }else if(event.type === HttpEventType.Response){
        console.log(event);
      }
    });
}

public getInterviewDate():Observable<any>{
  return new Observable(observer => {
    this.http.get('https://graph.microsoft.com/v1.0/me/events?$select=Subject,Body,Start,End,Attendees')
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {
      let interviews : Interview[]= [];
      let isInterview : RegExp = new RegExp('Invitation for an Interview for.*');
      data.value.forEach(element => {
        if(isInterview.test(element.subject)){
          let status : string=element.attendees[0].status.reponse;
          if(!status){
            status = 'Not Response';
          }
          interviews.push({
            jobId : element.attendees[0].emailAddress.name[0],
            candidateId : element.attendees[0].emailAddress.name[2],
            responseStatus : status,
            jobName : element.subject.substring(32),
            candidateName : element.body.content.match('(?<=Dear )(.*)(?=<br>.*)')[0],
            startDateTime : new Date(element.start.dateTime),
            endDateTime : new Date(element.end.dateTime)
          })
        }
      });
      observer.next(interviews);
    },(err:any)=>{

    })
  })
}

}
