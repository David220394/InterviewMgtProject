import { Component, OnInit } from '@angular/core';
import { AddJobService } from './providers/add-job.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.scss']
})
export class AddJobComponent implements OnInit {

  constructor(private addJobService: AddJobService, private http: HttpClient) { }

  file: File;

  ngOnInit() {
  }


  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  createJob(createJobForm) {

    if (this.file != null) {
      const job = createJobForm.value;

    //convert to array
    job.assignTo = job.assignTo.split(",");

    //delete file field
    delete job.file;

    console.log(job);

    this.http.post('http://localhost:8082/api/jobs/createJob', job, this.httpOptions)
    .subscribe((data: any) => {
      console.log(data);

      const uploadData = new FormData();
      uploadData.append('jobId', data.jobId);
      uploadData.append('file', this.file);

      this.http.post('http://localhost:8082/api/jobs/upload', uploadData)
      .subscribe((updatedJob: any) => {
        console.log(updatedJob);
      });
    })
    }
  }

  onUpload($event) {
    this.file = $event.target.files[0];
  }


}
