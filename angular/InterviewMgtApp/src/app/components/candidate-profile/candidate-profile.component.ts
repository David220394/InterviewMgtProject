import { Component, OnInit, Input } from '@angular/core';
import { CandidatePageService } from './providers/candidate-page.service';
import { Candidate } from './dtos/candidate';
import { Skill } from './dtos/skill';
import { MatDialog } from '@angular/material';
import { TrackDialogComponent } from './track-dialog/track-dialog.component';
import { ContactDialogComponent } from './contact-dialog/contact-dialog.component';
import { SharePreferencesService } from '../providers/share-preferences.service';
import { TrackingService } from './providers/tracking/tracking.service';
import { Contact } from './dtos/contact';
import { InterviewDialogComponent } from './interview-dialog/interview-dialog.component';
import { AfterInterview } from './dtos/afterInterview';
import { InterviewService } from './providers/interview.service';
import { Score } from './dtos/score';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Job } from '../pipeline/dto/job';
import { PipelineCandidateService } from '../pipeline/providers/pipeline-candidate.service';
import { startWith, map } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from '../login-dialog/providers/login.service';

@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.scss']
})
export class CandidateProfileComponent implements OnInit {

  @Input() candidate: Candidate = null;
  skills: Skill[];
  availability: string;
  features: string[];
  interviewFlag: boolean = false;
  afterInterviews: AfterInterview[];
  interviewScore: Score;
  overallScore: number;
  is_job: boolean;
  jobCtrl = new FormControl();
  filteredJobs: Observable<Job[]>;
  jobs : Job[];
  jobSelected : Job;
  errMsg: string;
  cvContent : any;
  cvContent1 : any;
  pdf: File;

  constructor(private loginService : LoginService, private domSanitizer : DomSanitizer,private pipelineService:PipelineCandidateService,private route: ActivatedRoute, private interviewService: InterviewService, private trackingService: TrackingService, private candidatePageService: CandidatePageService, public dialog: MatDialog, private sharePreference: SharePreferencesService) { }

  ngOnInit() {
    let jobId: string = this.route.snapshot.paramMap.get('jobId');
    if (jobId) {
      this.is_job = true;
      this.candidatePageService.getCandidateById(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        this.candidate = data;
        this.candidateParameters();
      });
    } else {
      this.is_job = false;
      this.candidatePageService.getCandidateByCandidateId(this.sharePreference.getCandidateId()).subscribe((data: any) => {
        this.candidate = data;
        this.candidateParameters();
      });
      this.pipelineService.getAlljob().subscribe((data: any)=>{
        this.jobs = data;
        this.filteredJobs = this.jobCtrl.valueChanges
      .pipe(
        startWith(''),
        map(job => job.length >= 1 ? this._filterJobs(job) : null)
      );
      });
    }
    this.features = [];
    if (this.candidate === null) {
      this.interviewFlag = true;


      this.interviewService.getCompletedInterview(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        console.log(data);
        this.afterInterviews = data;
      });

      this.interviewService.getScore(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        console.log(data);
        this.interviewScore = data;
        this.overallScore = (this.interviewScore.aveScore / 100) * 5;
      });
    } else {
      this.candidateParameters();
    }
  }

  private _filterJobs(value: string): Job[] {
    const filterValue = value.toLowerCase();

    return this.jobs.filter(job =>job.name.toLowerCase().includes(filterValue));
  }

  candidateParameters() {
    console.log(this.candidate);
    this.skills = this.candidate.skills;

    if (this.candidate.availability) {
      this.availability = "Free 24/7";
    } else {
      this.availability = "Currently being employed";
    }

    if (!this.candidate.completeApplication && this.candidate.completeApplication != null) {
      this.features.push("Incomplete Application");
    }
    if (this.candidate.internalApplication && this.candidate.internalApplication != null) {
      this.features.push("Internal Application");
    }
    if (this.candidate.rehire && this.candidate.rehire != null) {
      this.features.push("Rehired");
    }
  }

  openTracking(): void {
    const dialogRef = this.dialog.open
      (TrackDialogComponent, {
        width: '50%',
        data: { user: this.loginService.getUsernameFromLocalStorage(), name: this.candidate.name }
      });

    dialogRef.backdropClick().subscribe(_ => {
      dialogRef.close();
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log("Closed")
    })
  }

  openContact(): void {
    const dialogRef = this.dialog.open
      (ContactDialogComponent, {
        width: '50%',
        data: { user: this.loginService.getUsernameFromLocalStorage(), phone: this.candidate.phone, email: this.candidate.email }
      });

    dialogRef.afterClosed().subscribe(result => {
      console.log("Closed")
      if (result) {
        console.log(result);
        let contact: Contact = {
          candidateId: parseInt(this.sharePreference.getCandidateId()),
          jobId: parseInt(this.sharePreference.getJobId()),
          employeeId: this.loginService.getUsernameFromLocalStorage(),
          comment: result.comment,
          trackingType: result.type
        };
        this.trackingService.insertTracking(contact);
        this.trackingService.changeCandidateStatus(parseInt(this.sharePreference.getJobId()),
        parseInt(this.sharePreference.getCandidateId()),
        result.changeStatus,this.candidate.status).subscribe();
      }
    })
  }

  openInterview(interviewType: string): void {
    console.log(interviewType);
    const dialogRef = this.dialog.open(
      InterviewDialogComponent,
      { width: '75%', data: { interviewType: interviewType } }
    );
  }

  selectedJob(job){
    this.jobSelected = job;
  }

  addJob(){
    this.candidatePageService.addJob(this.jobSelected.id,this.sharePreference.getCandidateId()).subscribe((data:any)=>{
      this.errMsg = null;
      console.log(data);
    },(err:any)=>{
      if(err.status === 409){
        this.errMsg = err.error;
      }
      console.log(err);
    })
  }

  getCandidateCV(){
    this.candidatePageService.getCVByCandidateId(this.sharePreference.getCandidateId()).subscribe((data:any)=>{
     var file = new Blob([data], {type: 'application/pdf'});
     this.pdf = new File([file], "x.pdf");
     console.log(this.pdf);
      var fileURL = URL.createObjectURL(file);
      this.cvContent = this.domSanitizer.bypassSecurityTrustResourceUrl(fileURL);
      console.log(this.cvContent);
    });
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       this.cvContent1 = reader.result;
    }, false);
    if (image) {
       reader.readAsDataURL(image);
    }
   }

   getImageFromService() {
       this.candidatePageService.getCVByCandidateId(this.sharePreference.getCandidateId()).subscribe((data:any)=>{
         this.createImageFromBlob(data);
       });
   }


}
