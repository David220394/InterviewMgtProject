import { Component, OnInit } from '@angular/core';
import { DashboardService } from './providers/dashboard.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  file : File;
  noOfCandidate : number;

  constructor(private router : Router, private dashboardService : DashboardService) { }

  ngOnInit() {
    this.noOfCandidate = 10;

  }


  onFileChanged(event){
    this.file = event.target.files[0];
  }

  uploadCandidate(){
      this.dashboardService.uploadCandidate(this.file);
  }

  onClickRedirect(): void{
    this.router.navigateByUrl('/jobList');
  }
}
