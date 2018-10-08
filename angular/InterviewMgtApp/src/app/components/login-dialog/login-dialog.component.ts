import { Component, OnInit } from '@angular/core';
import { MainNavComponent } from '../../main-nav/main-nav.component';
import { MatDialogRef } from '@angular/material';
import { LoginService } from './providers/login.service';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MainNavComponent>,private loginService : LoginService) {
    dialogRef.disableClose = true;
   }

  ngOnInit() {}

  Login(user){
    console.log(user.value);
    this.loginService.Login(user.value).subscribe ((data:any) => {
      if (data) {
        // What to do if  login successfull
        this.dialogRef.close();
        console.log('Logged in');
      } else {
        console.log('Wrong username or password');
      }
    })
  }

close(){
  this.dialogRef.close();
}

}
