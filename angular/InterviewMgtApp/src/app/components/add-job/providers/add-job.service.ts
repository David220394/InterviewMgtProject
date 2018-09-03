import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/Http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddJobService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private _httpClient: HttpClient) { }

  public register(job):Observable<any>{
    return new Observable(observer => {
      this._httpClient.post("http://localhost:8082/jobs/", job, this.httpOptions)
      .pipe(finalize(() => { observer.complete(); } ))
      .subscribe((data: any) => {
        observer.next(data);
      }, (err: any) => {
        observer.next(null);
      });
    })
  }
}
