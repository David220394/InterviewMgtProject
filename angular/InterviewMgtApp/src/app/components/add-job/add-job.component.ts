import { Component, OnInit } from '@angular/core';
import { AddJobService } from './providers/add-job.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { LoginService } from '../login-dialog/providers/login.service';

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.scss']
})
export class AddJobComponent implements OnInit {

  myForm: FormGroup;
  minDate = new Date(new Date().setDate(new Date().getDate()-1))


  //maxDate = new Date();
  maxDate =   new Date(2200, 0, 1);
  constructor(private loginService: LoginService, private _fb: FormBuilder,private addJobService: AddJobService, private http: HttpClient, private router : Router) { }

  file: File;
  err : string;

  ngOnInit() {
    this.loginService.isValidUser();
    this.myForm = this._fb.group({
      projectName: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      position: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      location: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      creationDate: ['', [Validators.required]],
      closingDate : ['', [Validators.required]],
      assignTos: this._fb.array([this.initAssignTo(),]),
      requirements : this._fb.array([this.initRequirement()]),
      file: ['', ],
  });
  }

  initAssignTo() {
    return this._fb.group({
      assignTo : ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
    });
  }

  initRequirement() {
    return this._fb.group({
      requirement : ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
    });
  }

  addAssignTo() {
    console.log("assigne")
    const control = <FormArray>this.myForm.controls['assignTos'];
    control.push(this.initAssignTo());
    }

    removeAssignTo(i: number) {
    const control = <FormArray>this.myForm.controls['assignTos'];
    control.removeAt(i);
    }

    addRequirement() {
      const control = <FormArray>this.myForm.controls['requirements'];
      control.push(this.initRequirement());
      }

    removeRequirement(i: number) {
      const control = <FormArray>this.myForm.controls['requirements'];
      control.removeAt(i);
      }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  createJob(createJobForm) {


    if (this.file != null) {
      const job = createJobForm.value;
      let assignTos : string[] = [];
      let requirements : string[] = [];

      job.assignTos.forEach(element => {
        assignTos.push(element.assignTo);
      });
    job.requirements.forEach(element => {
      requirements.push(element.requirement);
    });

    job.assignTos = assignTos;
    job.requirements = requirements;
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

    this.router.navigateByUrl('/dashboard');

    }else {
        this.err = "Insert File";
    }
  }

  onUpload($event) {
    this.file = $event.target.files[0];
  }

  createJobs(myForm){
    console.log(myForm.value);
  }
}
