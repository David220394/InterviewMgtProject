import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/Http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegisterHrService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private _httpClient: HttpClient) { }

  public registerHR(register){
    return new Observable(observer => {
      this._httpClient.post("http://localhost:8082/api/employee/insertEmployee", register, this.httpOptions)
      .pipe(finalize(() => { observer.complete(); } ))
      .subscribe((data: any) => {
        observer.next(data);
      }, err => {
        observer.error(err);
      });
    })
  }
}
