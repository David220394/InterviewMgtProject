import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from './providers/dashboard.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Interview } from './dto/interview';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

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

  dataSource: MatTableDataSource<Interview>;
  displayedColumns: string[] = ['jobName', 'candidateName','startDateTime','endDateTime','responseStatus'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

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
      this.dataSource = new MatTableDataSource(this.interviews);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(data)
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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

  OnClickGoToCandidate(): void{
    this.router.navigateByUrl('/candidateList');
  }
}
