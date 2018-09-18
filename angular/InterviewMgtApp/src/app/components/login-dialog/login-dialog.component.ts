import { Component, OnInit } from '@angular/core';
import { MainNavComponent } from '../../main-nav/main-nav.component';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MainNavComponent>) {
    dialogRef.disableClose = true;
   }

  ngOnInit() {
  }

close(){
  this.dialogRef.close();
}

}
