import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpEventType, HttpClient } from '@angular/common/http';

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
}
