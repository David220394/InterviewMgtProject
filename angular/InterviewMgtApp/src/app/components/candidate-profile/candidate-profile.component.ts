import { Component, OnInit, Input } from '@angular/core';
import { CandidatePageService } from './providers/candidate-page.service';
import { Candidate } from './dtos/candidate';
import { Experience } from './dtos/experience';
import { Skill } from './dtos/skill';
import { Tracking } from './dtos/tracking';
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

@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.scss']
})
export class CandidateProfileComponent implements OnInit {

  @Input() candidate: Candidate = null;
  skills: Skill[];
  user: string = "sylvio.brandon.david";
  availability: string;
  features: string[];
  interviewFlag : boolean = false;
  afterInterviews : AfterInterview[];
  interviewScore : Score;
  overallScore : number;


  constructor(private interviewService : InterviewService, private trackingService: TrackingService, private candidatePageService: CandidatePageService, public dialog: MatDialog, private sharePreference: SharePreferencesService) { }

  ngOnInit() {
    this.features = [];
    if (this.candidate === null) {
      this.interviewFlag = true;

      this.candidatePageService.getCandidateById(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        this.candidate = data;
        this.candidateParameters();
      });
      this.interviewService.getCompletedInterview(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        console.log(data);
        this.afterInterviews = data;
      });

      this.interviewService.getScore(this.sharePreference.getJobId(), this.sharePreference.getCandidateId()).subscribe((data: any) => {
        console.log(data);
        this.interviewScore = data;
        this.overallScore = (this.interviewScore.aveScore /100)*5;
      });
    }else{
      this.candidateParameters();
    }
  }

  candidateParameters(){
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
        data: { user: this.user, name: this.candidate.name }
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
        data: { user: this.user, phone: this.candidate.phone, email: this.candidate.email }
      });

    dialogRef.afterClosed().subscribe(result => {
      console.log("Closed")
      if (result != null) {
        let contact: Contact = {
          candidateId: parseInt(this.sharePreference.getCandidateId()),
          jobId: parseInt(this.sharePreference.getJobId()),
          employeeId: this.user,
          comment: result.comment,
          trackingType: result.type
        };
        this.trackingService.insertTracking(contact);
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



}
