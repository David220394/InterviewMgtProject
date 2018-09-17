import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { JobListService } from './providers/job-list.service';
import { JobList } from './dtos/job-list';


@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.scss']
})

export class JobListComponent implements OnInit {
  jobList: JobList[];

  dataSource: MatTableDataSource<JobList>;
  displayedColumns: string[] = ['id', 'jobName', 'position', 'location','closingDate','creationDate'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private jobListService : JobListService) {
    // Assign the data to the data source for the table to render

  }

  ngOnInit() {
    this.jobListService.getAllJobs().subscribe(data =>{
      console.log(data);
      this.jobList= data;
      this.dataSource = new MatTableDataSource(this.jobList);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

