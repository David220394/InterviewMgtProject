import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { finalize } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { LoginService } from '../login-dialog/providers/login.service';

@Injectable({
  providedIn: 'root'
})
export class SharePreferencesService {

  candidateId : number;
  jodId : number;
  code : string;

  private LOCALSTORAGE_CANDIDATE_ID = 'candidateId';
  private LOCALSTORAGE_JOB_ID = 'jobId';
  private LOCALSTORAGE_CANDIDATE_NAME = 'candidateName';
  private LOCALSTORAGE_JOB_NAME = 'jobName';
  private LOCALSTORAGE_CALENDER = 'calender';
  private LOCALSTORAGE_INT_EXPIRE_DATE = 'intExpDate';
  private LOCALSTORAGE_INT_LINK = 'intLink';

  constructor(private http : HttpClient, private authenticationService : LoginService) { }

  setCandidateId(candidateId : number){
     localStorage.setItem(this.LOCALSTORAGE_CANDIDATE_ID, candidateId.toString() )
  }

  setIntExpDate(expDate : Date){
    localStorage.removeItem(this.LOCALSTORAGE_INT_EXPIRE_DATE);
    localStorage.setItem(this.LOCALSTORAGE_INT_EXPIRE_DATE, expDate.toString())
 }

 setIntLink(link : string){
  localStorage.removeItem(this.LOCALSTORAGE_INT_LINK);
  localStorage.setItem(this.LOCALSTORAGE_INT_LINK, link)
}

  setCandidateName(candidateName : string){
    localStorage.setItem(this.LOCALSTORAGE_CANDIDATE_NAME, candidateName )
 }

 getCandidateName(): string{
  return localStorage.getItem(this.LOCALSTORAGE_INT_EXPIRE_DATE);
}

getIntExpDate(): Date{
   return new Date(localStorage.getItem(this.LOCALSTORAGE_INT_EXPIRE_DATE));
}

getIntLink(): string{
  return localStorage.getItem(this.LOCALSTORAGE_INT_LINK);
}



  getCandidateId(): string{
    return localStorage.getItem(this.LOCALSTORAGE_CANDIDATE_ID);
  }

  setJobId(jobId : number){
    localStorage.setItem(this.LOCALSTORAGE_JOB_ID,jobId.toString())
  }

  getJobId(): string{
    return localStorage.getItem(this.LOCALSTORAGE_JOB_ID);
  }

  setJobName(jobName : string){
    localStorage.setItem(this.LOCALSTORAGE_JOB_NAME,jobName)
  }

  getJobName(): string{
    return localStorage.getItem(this.LOCALSTORAGE_JOB_NAME);
  }

  getCalenderKey(){
    return localStorage.getItem(this.LOCALSTORAGE_CALENDER);
  }


  isCalender(){
    if(localStorage.getItem(this.LOCALSTORAGE_CALENDER)){
      return true
    }else{
     this.getAllCalender().subscribe(()=>{
       return true
     })
    }
  }

  getAllCalender() : Observable<any>{
    return new Observable(observer => {
      this.http.get('https://graph.microsoft.com/v1.0/me/calendars')
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
        data.value.forEach(element => {
          if(element.name.includes('InterviewCal')){
            localStorage.setItem(this.LOCALSTORAGE_CALENDER,element.id)
          }
        });
        if(localStorage.getItem(this.LOCALSTORAGE_CALENDER) == null){
          this.getCanlederKey().subscribe()
        }
      },
      (err : any)=>{
        console.log(err)
      })
    })
  }

  private getCanlederKey() : Observable<any>{
    let name = {'Name' : 'InterviewCal'}
    return new Observable(observer => {
    this.http.post('https://graph.microsoft.com/v1.0/me/calendars',name)
    .pipe(finalize(() => { observer.complete(); } ))
  .subscribe((data: any) => {
      localStorage.setItem(this.LOCALSTORAGE_CALENDER,data.id)
      observer.next(data);
    },(err : any) =>{
      console.log(err);
        observer.error(err)
  })
})
  }









}


