import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class JobListService {

  constructor(private _http:HttpClient) { }

  public getAllJobs(): Observable<any>{
    return new Observable(observer =>{
      this._http.get('http://localhost:8082/api/jobs/getAll')
      .pipe(finalize(()=>{observer.complete();}))
      .subscribe((data:any)=>{
        observer.next(data);
      })
    })
  }

}
