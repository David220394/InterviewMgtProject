import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.scss']
})
export class CandidateProfileComponent implements OnInit {

  candidate : Candidate;
  skills : Skill[];
  user : string = "sylvio.brandon.david";
  availability : string;
  features : string[];


  constructor(private trackingService: TrackingService,private candidatePageService : CandidatePageService, public dialog : MatDialog, private sharePreference: SharePreferencesService) { }

  ngOnInit() {
    this.features = [];
    console.log(this.sharePreference.getJobId() + " " + this.sharePreference.getCandidateId())
    this.candidatePageService.getCandidateById(this.sharePreference.getJobId(),this.sharePreference.getCandidateId()).subscribe((data: any) => {
      this.candidate = data;
      console.log(this.candidate)
      this.skills = this.candidate.skills;

      if(this.candidate.availability){
        this.availability = "Free 24/7";
      }else{
        this.availability = "Currently being employed";
      }
      if(this.candidate.score === null){
        this.candidate.score = 10;
      }

      if(!this.candidate.completeApplication && this.candidate.completeApplication != null){
        this.features.push("Incomplete Application");
      }
      if(this.candidate.internalApplication && this.candidate.internalApplication != null){
        this.features.push("Internal Application");
      }
      if(this.candidate.rehire && this.candidate.rehire != null){
        this.features.push("Rehired");
      }
    });
  }

  openTracking(): void{
    const dialogRef = this.dialog.open
    (TrackDialogComponent, {width : '50%',
    data : {user : this.user, name : this.candidate.name}
  });

  dialogRef.backdropClick().subscribe(_ => {
    dialogRef.close();
  })

  dialogRef.afterClosed().subscribe(result => {
    console.log("Closed")
  })
  }

  openContact(): void{
    const dialogRef = this.dialog.open
    (ContactDialogComponent, {width : '50%',
    data : {user : this.user, phone : this.candidate.phone, email : this.candidate.email}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log("Closed")
    if(result != null){
    let contact : Contact = {
      candidateId : parseInt(this.sharePreference.getCandidateId()),
      jobId : parseInt(this.sharePreference.getJobId()),
      employeeId : this.user,
      comment : result.comment,
      trackingType : result.type
    };
      this.trackingService.insertTracking(contact);
  }
  })
  }



}
