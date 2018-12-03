import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpEventType, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { Interview } from '../dto/interview';
import { JobStat } from '../dto/jobStat';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  public totalCandidate : number;
  public jobStats : JobStat[];

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

/**
 * Get All interview Date and Time
 */
public getInterviewDate():Observable<any>{
  return new Observable(observer => {
    this.http.get('https://graph.microsoft.com/v1.0/me/events?$select=Subject,Body,Start,End,Attendees')
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {//List of appointments
      let interviews : Interview[]= [];
      let isInterview : RegExp = new RegExp('Invitation for an Interview for.*');//Regex to check if interview
      data.value.forEach(element => {//for each appointment
        if(isInterview.test(element.subject)){//If interview contain match the Regex Expression
          let status : string=element.attendees[0].status.reponse;//Get the reponse of the candidate
          if(!status){
            status = 'Not Response';
          }
          interviews.push({
            jobId : element.attendees[0].emailAddress.name[0],
            candidateId : element.attendees[0].emailAddress.name[2],//Obtain in the subject of the mail
            responseStatus : status,
            jobName : element.subject.substring(32),//Get job Name in the Subject of the mail
            candidateName : element.body.content.match('(?<=Dear )(.*)(?=<br>.*)')[0],//Get candidate Name using Regex
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



public getAllCandidates():Observable<any>{
return new Observable(observer => { this.http.get(environment.url + '/candidate/')
.pipe( finalize(() => { observer.complete(); }))
.subscribe( (candidates: any) => {
  console.log(candidates);
  this.totalCandidate = candidates.length;
  observer.next(candidates);
})});
}
/**
 * Obtain all Job Statistic
 */
public getAllJobs():Observable<any>{
  return new Observable(observer => {
    this.http.get(environment.url + '/api/jobs/')
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {//Get all job
     this.jobStats = [];
    data.forEach(element => { //for each job
      let candidateNumber = element.jobCandidates.length; //get candidate No.
      let candidatePercentage = ((candidateNumber/this.totalCandidate)*100); //get percentage candidate
      this.jobStats.push({//add to list of jobStats
        jobName : element.jobName,
        candidatePercentage : candidatePercentage,
        candidateNumber : candidateNumber
      })
    });
     observer.next(data);
    },(err:any)=>{
     observer.error(err);
    })
  });
}



















/**
 * Example on how to make a simple GET request
 * Observable wait to received the response
 * before sending result. (For async call).
 */
getExample():Observable<any>{
  return new Observable(observer => {
    this.http.get('https://localhost:8082/getExample')
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {
      /*
        data object represent the response received from the API
        if the status is Okay (status between 200 and 299)
      */
     observer.next(data);
    },(err:any)=>{
      /*
        err object represent the response received from the API
        if the status is not Okay (status between 300 and above)
      */
     observer.error(err);
    })
  });
}

/**
 * Example on how to make a simple POST request
 */
postExample():Observable<any>{
  //Represent the data to send via the post request.
  //It can be values entered from a form for example
  let resquestBody : any;

  return new Observable(observer => {
    this.http.post('https://localhost:8082/postExample',resquestBody)
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {
      /*
        data object represent the response received from the API
        if the status is Okay (status between 200 and 299)
      */
     observer.next(data);
    },(err:any)=>{
      /*
        err object represent the response received from the API
        if the status is not Okay (status between 300 and above)
      */
     observer.error(err);
    })
  });
}

/**
 * Example on how to make a simple PUT request
 */
putExample():Observable<any>{
  //Represent the data to send via the post request.
  //It can be values entered from a form for example
  let resquestBody : any;

  return new Observable(observer => {
    this.http.put('https://localhost:8082/putExample',resquestBody)
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {
      /*
        data object represent the response received from the API
        if the status is Okay (status between 200 and 299)
      */
     observer.next(data);
    },(err:any)=>{
      /*
        err object represent the response received from the API
        if the status is not Okay (status between 300 and above)
      */
     observer.error(err);
    })
  });
}

/**
 * Example on how to make a simple DELETE request
 */
deleteExample():Observable<any>{
  //Represent the data to send via the post request.
  //It can be values entered from a form for example
  let resquestBody : any;

  return new Observable(observer => {
    this.http.delete('https://localhost:8082/deleteExample',resquestBody)
    .pipe( finalize(() => { observer.complete(); }))
    .subscribe( (data: any) => {
      /*
        data object represent the response received from the API
        if the status is Okay (status between 200 and 299)
      */
     observer.next(data);
    },(err:any)=>{
      /*
        err object represent the response received from the API
        if the status is not Okay (status between 300 and above)
      */
     observer.error(err);
    })
  });
}























}
