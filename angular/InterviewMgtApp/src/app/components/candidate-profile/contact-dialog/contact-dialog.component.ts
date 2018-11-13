import { Component, OnInit, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CandidateProfileComponent } from '../candidate-profile.component';
import { MatOptionSelectionChange, MatSelectionListChange } from '@angular/material';
import { TrackingService } from '../providers/tracking/tracking.service';

export interface DialogData{
  user : string;
  email : string;
  phone : string;
}

export interface Mail {
  value: string;
  viewValue: string;
  body : string;
}

export interface suggestedDateTime {
  start: Date;
  end: Date;
}

@Component({
  selector: 'app-contact-dialog',
  templateUrl: './contact-dialog.component.html',
  styleUrls: ['./contact-dialog.component.scss']
})
export class ContactDialogComponent implements OnInit {

  phone : string;
  user : string;
  comment : string;
  email : string;
  selectedValue: any;
  type : string;
  start : number;
  duration : number;
  suggestedDateTimes: suggestedDateTime[];
  selectedDate : suggestedDateTime;
  is_meeting : boolean;




  mails: Mail[] = [
    {value: 'interview-0', viewValue: 'Interview',body : 'Book interview'},
    {value: 'signContract-1', viewValue: 'Sign Contract',body : 'Sign Contract'},
    {value: 'rejected-2', viewValue: 'Rejected',body : 'Rejected'}
  ];

  constructor(private trackingService : TrackingService,public dialogRef : MatDialogRef<CandidateProfileComponent>,
    @Inject(MAT_DIALOG_DATA)public data : DialogData) { }

  ngOnInit() {
    this.phone = this.data.phone;
    this.email = this.data.email;
    this.user = this.data.user;
    this.is_meeting = false;
  }

  onContactSubmit(){
    if(this.comment != null){
      this.dialogRef.close({comment : this.comment,type : 'CALL',});
    }
  }

  onMailSubmit(){
    if(this.comment != null && this.type != null){
      if(this.type =='REJECTED' || this.selectedDate != null){
      this.trackingService.sentMail(this.comment,this.type,this.email,this.selectedDate).subscribe((data:any)=>{
        console.log(data)
      });
      this.dialogRef.close({comment : this.comment,type : this.type,});
    }
  }
  }

  updateSelected(event: MatSelectionListChange,mailId : any, comment : any){
    if(mailId === 'interview-0'){
        this.type = 'INTERVIEW';
        this.is_meeting = true;
    }else if(mailId === 'signContract-1'){
        this.type = 'SIGN_CONTRACT';
        this.is_meeting = true;
    }else{
        this.type = 'REJECTED';
        this.is_meeting = false;
    }
    this.selectedValue= comment;
    this.comment = this.selectedValue;
  }

  check(){
    if(this.duration && this.start){
    this.trackingService.getFreeTimeSlot(this.duration,this.start,(this.start+1)).subscribe((data:any)=>{
      data.meetingTimeSuggestions[0].meetingTimeSlot.start.dateTime
      this.suggestedDateTimes = [];
      data.meetingTimeSuggestions.forEach(element => {
        this.suggestedDateTimes.push({
          start : new Date(element.meetingTimeSlot.start.dateTime),
          end : new Date(element.meetingTimeSlot.end.dateTime)
        }
        )
      });
    })
    }
  }

}
