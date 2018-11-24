import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { UserList } from './dto/user-list';
import { UserListService } from './provider/user-list.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  userList : UserList[];
  dataSource: MatTableDataSource<UserList>;
  displayedColumns: string[] = ['employeeId', 'employeeName', 'role', 'edit'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService : UserListService) { }

  ngOnInit() {
    this.userService.getAllJobs().subscribe(data =>{
      console.log(data);
      this.userList= data;
      this.dataSource = new MatTableDataSource(this.userList);

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

  changeRole(user){
    if(user.role =='HR'){
      this.userService.updateRole(user).subscribe();
    }
  }
}
