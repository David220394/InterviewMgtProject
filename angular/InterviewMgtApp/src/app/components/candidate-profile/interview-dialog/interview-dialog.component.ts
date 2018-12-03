import { Component, OnInit, Inject } from '@angular/core';
import { CandidateProfileComponent } from '../candidate-profile.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Interview } from '../dtos/interview';
import { SharePreferencesService } from '../../providers/share-preferences.service';
import { InterviewService } from '../providers/interview.service';
import { Candidate } from '../dtos/candidate';
import { LoginService } from '../../login-dialog/providers/login.service';


export interface DialogData {
  interviewType: string;
  candidate : Candidate;
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
  message: string;
  interviewer: string;
  interview: Interview;
  mailBody: string;

  constructor(private interviewService: InterviewService,private loginService : LoginService, private sharedPreferences: SharePreferencesService, public dialogRef: MatDialogRef<CandidateProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit() {
    this.linkCode = this.generateLink();
    this.link = "http://localhost:4200/interview/" + this.linkCode;//Generate a new link
    this.type = this.data.interviewType;
    if (this.type === 'hr') {
      this.interviewService.getHRInterview(this.sharedPreferences.getCandidateId(),this.sharedPreferences.getJobId(), this.interviewer).subscribe((data : any)=>{//Check if interview expired
        this.link = "http://localhost:4200/interview/"+data.link;
        window.open(this.link);// Open the link in a new tab
        this.dialogRef.close();
      },(err:any)=>{
        console.log(err);
        if(err.status == 404){
        this.createInterview();
        window.open(this.link);// Open the link in a new tab
        this.dialogRef.close();
        }
      });


    }else if (this.type === 'iq') {
      this.createInterview();
      let emailAdd = this.data.candidate.email;
    let subject = 'IQ Test for '+this.data.candidate.jobName;
    let body = 'Hi '+this.data.candidate.name+',%0D%0A'+
                'Kindly find the link to access the IQ Test below %0D%0A'+
                this.link;
    window.location.href='mailto:'+emailAdd+'?subject='+subject+'&body='+body;
    this.dialogRef.close();
    }
  }
  //'mailto:'+${userCompletedPrerequisite.user.emailAdd}+'?subject=Registration for certification '+ ${userCompletedPrerequisite.certificationName}+' approved&body=Hi '+${userCompletedPrerequisite.user.firstname}+',%0D%0AYour proofs of completion of prerequisites have been approved. %0D%0AVoucher code: '"
  sendMailTech(){
    if(this.interviewer != null){
      this.createInterview();
    let emailAdd = this.interviewer + '@accenture.com';
    let subject = 'Technical Interview for '+this.data.candidate.name;
    let body = 'Hi '+this.interviewer.replace('.',' ')+',%0D%0A'+
                'Kindly find the link to access the interview Feedback form below %0D%0A'+
                this.link;
    window.location.href='mailto:'+emailAdd+'?subject='+subject+'&body='+body;
  }else{
    this.message = 'Please enter an Interviewer';
  }
  }

 private createInterview() {
  this.interview = {
    candidateId: parseInt(this.sharedPreferences.getCandidateId()),
    jobId: +this.sharedPreferences.getJobId(),
    interviewer: this.interviewer,
    type: this.type,
    link: this.linkCode
  }
  this.interviewService.createInterview(this.interview);
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
