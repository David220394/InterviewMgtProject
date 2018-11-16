import { Component, OnInit } from '@angular/core';
import { RegisterHrService } from './providers/register-hr.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-hr',
  templateUrl: './register-hr.component.html',
  styleUrls: ['./register-hr.component.scss']
})
export class RegisterHrComponent implements OnInit {

  constructor(private _fb: FormBuilder,private registerHRService: RegisterHrService) { }

  ngOnInit() {
  }

  registerHR(register){
    this.registerHRService.registerHR(register.value).subscribe ((data:any) => {
      console.log(data);
    })
  }
}
