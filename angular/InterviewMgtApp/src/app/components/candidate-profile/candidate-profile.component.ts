import { Component, OnInit } from '@angular/core';
import { CandidatePageService } from './providers/candidate-page.service';
import { Candidate } from './dtos/candidate';
import { Experience } from './dtos/experience';
import { Skill } from './dtos/skill';
import { MatDialog } from '@angular/material';
import { TrackDialogComponent } from './track-dialog/track-dialog.component';
import { ContactDialogComponent } from './contact-dialog/contact-dialog.component';

@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.scss']
})
export class CandidateProfileComponent implements OnInit {

  candidate : Candidate;
  email : string;
  experiences : Experience[];
  skills : Skill[];
  user : string = "David";
  phone : number;
  skill : Skill;
  experience : Experience;
  availability : string;


  constructor(private candidatePageService : CandidatePageService, public dialog : MatDialog) { }

  ngOnInit() {
    this.candidatePageService.getCandidateById(4).subscribe((data: any) => {
      this.candidate = data;
      console.log(this.candidate)
      this.phone = this.candidate.phones[0];
      this.email = this.candidate.email;
      this.experience = this.candidate.experiences[0];
      this.skill = this.candidate.skills[0];
      if(this.candidate.availability){
        this.availability = "Free 24/7";
      }else{
        this.availability = "Currently being employed";
      }
      if(this.candidate.score === null){
        this.candidate.score = 10;
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
    data : {user : this.user, phone : this.phone, email : this.candidate.email}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log("Closed")
  })
  }

}
