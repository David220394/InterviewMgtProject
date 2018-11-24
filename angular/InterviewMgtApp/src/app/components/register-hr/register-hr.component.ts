import { Component, OnInit } from '@angular/core';
import { RegisterHrService } from './providers/register-hr.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login-dialog/providers/login.service';

@Component({
  selector: 'app-register-hr',
  templateUrl: './register-hr.component.html',
  styleUrls: ['./register-hr.component.scss']
})
export class RegisterHrComponent implements OnInit {

  constructor(private loginService: LoginService,private _fb: FormBuilder,private registerHRService: RegisterHrService) { }

  ngOnInit() {
    this.loginService.isValidUser();
  }

  registerHR(register){
    this.registerHRService.registerHR(register.value).subscribe ((data:any) => {
      console.log(data);
    })
  }
}
