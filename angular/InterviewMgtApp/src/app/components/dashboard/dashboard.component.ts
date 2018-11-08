import { Component, OnInit } from '@angular/core';
import { DashboardService } from './providers/dashboard.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Interview } from './dto/interview';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  file : File;
  noOfCandidate : number;
  todaysInterview : number = 0;
  interviews : Interview[];
  constructor(private router : Router, private dashboardService : DashboardService) { }

  ngOnInit() {
    this.noOfCandidate = 10;
    this.dashboardService.getInterviewDate().subscribe((data : any)=>{
      this.interviews = data;
      var date : Date = new Date('2018-11-08')
      this.interviews.forEach(element=>{
        if(element.startDateTime.getDate() === date.getDate()){
          this.todaysInterview++;
        }
      })

      console.log(data)
    });
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
