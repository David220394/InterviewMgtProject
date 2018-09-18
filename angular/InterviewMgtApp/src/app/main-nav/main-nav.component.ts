import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MatDialog } from '@angular/material';
import { LoginDialogComponent } from '../components/login-dialog/login-dialog.component';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor( public dialog: MatDialog,private breakpointObserver: BreakpointObserver) {
    const dialogRef = this.dialog.open
    (LoginDialogComponent, {
      width: '50%'
    });

  dialogRef.afterClosed().subscribe(result => {
    console.log("Closed")
  })
  }

  }
