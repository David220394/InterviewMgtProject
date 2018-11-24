import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserListService {

  constructor(private _http:HttpClient) { }

  public getAllJobs(): Observable<any>{
    return new Observable(observer =>{
      this._http.get('http://localhost:8082/api/employee/')
      .pipe(finalize(()=>{observer.complete();}))
      .subscribe((data:any)=>{
        observer.next(data);
      })
    })
  }

  public updateRole(user): Observable<any>{
    return new Observable(observer => {
      this._http.put('https://localhost:8082/api/employee/',user.employeeId)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
       console.log(data);
       observer.next(data);
      },(err:any)=>{

       observer.error(err);
      })
    });
  }

  public deleteUser(user): Observable<any>{
    return new Observable(observer => {
      this._http.delete('https://localhost:8082/api/employee/',user.employeeName)
      .pipe( finalize(() => { observer.complete(); }))
      .subscribe( (data: any) => {
       console.log(data);
       observer.next(data);
      },(err:any)=>{

       observer.error(err);
      })
    });
  }
}
