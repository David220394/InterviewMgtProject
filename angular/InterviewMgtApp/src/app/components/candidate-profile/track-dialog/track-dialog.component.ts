import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource, MatSort } from '@angular/material';
import { CandidateProfileComponent } from '../candidate-profile.component';

export interface DialogData{
  user : string;
  name : string;
}

export interface PeriodicElement {
  user: string;
  type: string;
  position: number;
  creationDate: number;
  comment: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, user: 'Hydrogen',type: 'Call', creationDate: Date.now(), comment: 'Good'},

];

@Component({
  selector: 'app-track-dialog',
  templateUrl: './track-dialog.component.html',
  styleUrls: ['./track-dialog.component.scss']
})
export class TrackDialogComponent implements OnInit {

  displayedColumns: string[] = ['position', 'user', 'type','creationDate', 'comment'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  @ViewChild(MatSort) sort: MatSort;


  constructor(public dialogRef : MatDialogRef<CandidateProfileComponent>,
              @Inject(MAT_DIALOG_DATA)public data : DialogData) {
              }

  ngOnInit() {
    this.dataSource.sort = this.sort;
    console.log(this.data.user)
  }



}
