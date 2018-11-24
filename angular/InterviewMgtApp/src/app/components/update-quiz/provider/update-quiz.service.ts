import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UpdateQuizService {

  constructor(private _http:HttpClient) { }

  public getAllQuiz(): Observable<any>{
    return new Observable(observer =>{
      this._http.get('http://localhost:8082/interview/findAllQuiz/')
      .pipe(finalize(()=>{observer.complete();}))
      .subscribe((data:any)=>{
        observer.next(data);
      })
    })
  }

  public getQuizById(id): Observable<any>{
    return new Observable(observer =>{
      this._http.get('http://localhost:8082/interview/findQuiz/'+id)
      .pipe(finalize(()=>{observer.complete();}))
      .subscribe((data:any)=>{
        console.log(data);
        observer.next(data);
      })
    })
  }

  public updateQuiz(quiz){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    this._http.post(environment.url + '/interview/updateQuiz/',quiz, httpOptions).subscribe((data : any)=>{
      console.log(data);
  });
  }
}
