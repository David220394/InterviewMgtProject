import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource, MatSort } from '@angular/material';
import { CandidateProfileComponent } from '../candidate-profile.component';
import { Tracking } from '../dtos/tracking';
import { TrackingService } from '../providers/tracking/tracking.service';
import { SharePreferencesService } from '../../providers/share-preferences.service';
import { TrackingDataSource } from './tracking-datasource';

export interface DialogData{
  user : string;
  name : string;
}

@Component({
  selector: 'app-track-dialog',
  templateUrl: './track-dialog.component.html',
  styleUrls: ['./track-dialog.component.scss']
})
export class TrackDialogComponent implements OnInit {

  trackings? : Tracking[];

  displayedColumns: string[] = ['candidate', 'employee', 'job','creationDate', 'comment'];
  dataSource : TrackingDataSource;

  @ViewChild(MatSort) sort: MatSort;


  constructor(private sharePreferences : SharePreferencesService,private trackingService : TrackingService,public dialogRef : MatDialogRef<CandidateProfileComponent>,
              @Inject(MAT_DIALOG_DATA)public data : DialogData) {
              }

  ngOnInit() {
    this.dataSource = new TrackingDataSource(this.trackingService);
    console.log(this.sharePreferences.getCandidateId())
    this.dataSource.loadTracks(this.sharePreferences.getJobId(),this.sharePreferences.getCandidateId());


  }



}
