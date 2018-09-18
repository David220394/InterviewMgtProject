import { Component, OnInit, Inject } from '@angular/core';
import { CandidateProfileComponent } from '../candidate-profile.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Interview } from '../dtos/interview';
import { SharePreferencesService } from '../../providers/share-preferences.service';
import { InterviewService } from '../providers/interview.service';


export interface DialogData {
  interviewType: string;
}

@Component({
  selector: 'app-interview-dialog',
  templateUrl: './interview-dialog.component.html',
  styleUrls: ['./interview-dialog.component.scss']
})
export class InterviewDialogComponent implements OnInit {

  linkCode: string;
  link: string;
  type: string;
  user: string = "sylvio.brandon.david"
  message: string;
  interviewer: string;
  interview: Interview;

  constructor(private interviewService: InterviewService, private sharedPreferences: SharePreferencesService, public dialogRef: MatDialogRef<CandidateProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit() {
    this.linkCode = this.generateLink();
    this.link = "http://localhost:4200/interview/" + this.linkCode;
    this.type = this.data.interviewType;
    if (this.type === 'hr') {
      this.interviewer = this.user;
    }
  }

  copyToClickboard(inputElement) {
    if (this.message != 'Copied!!') {
      if (this.interviewer != null || this.type === 'iq') {
        inputElement.select();
        document.execCommand('copy');
        inputElement.setSelectionRange(0, 0);
        this.message = 'Copied!!';
        this.interview = {
          candidateId: parseInt(this.sharedPreferences.getCandidateId()),
          jobId: +this.sharedPreferences.getJobId(),
          interviewer: this.interviewer,
          type: this.type,
          link: this.linkCode
        }
        this.interviewService.createInterview(this.interview);
      } else {
        this.message = 'Please enter an Interviewer';
      }
    }
  }

  generateLink(): string {
    let characterArray: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
      'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
      'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0',
      '1', '2', '3', '4', '5', '6', '7', '8', '9']

    let sequence: string = "";

    for (let i = 0; i < 100; i++) {
      sequence += characterArray[Math.floor(Math.random() * characterArray.length)];
    }
    return sequence;
  }

}
