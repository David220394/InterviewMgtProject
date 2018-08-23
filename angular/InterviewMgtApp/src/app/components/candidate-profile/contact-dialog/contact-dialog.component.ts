import { Component, OnInit, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CandidateProfileComponent } from '../candidate-profile.component';

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
  selectedValue: Mail;

  mails: Mail[] = [
    {value: 'interview-0', viewValue: 'Interview',body : 'Book interview'},
    {value: 'signContract-1', viewValue: 'Sign Contract',body : 'Sign Contract'},
    {value: 'rejected-2', viewValue: 'Rejected',body : 'Rejected'}
  ];

  constructor(public dialogRef : MatDialogRef<CandidateProfileComponent>,
    @Inject(MAT_DIALOG_DATA)public data : DialogData) { }

  ngOnInit() {
    this.phone = this.data.phone;
    this.email = this.data.email;
    this.user = this.data.user;
  }



}
