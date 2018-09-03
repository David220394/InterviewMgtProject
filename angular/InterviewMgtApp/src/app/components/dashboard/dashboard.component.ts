import { Component, OnInit } from '@angular/core';
import { DashboardService } from './providers/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  file : File;

  constructor(private dashboardService : DashboardService) { }

  ngOnInit() {
  }

  onFileChanged(event){
    this.file = event.target.files[0];
  }

  uploadCandidate(){
      this.dashboardService.uploadCandidate(this.file);
  }

}
