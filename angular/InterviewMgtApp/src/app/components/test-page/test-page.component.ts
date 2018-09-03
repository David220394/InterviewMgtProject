import { Component, OnInit } from '@angular/core';
import { Candidate } from '../candidate-profile/dtos/candidate';

@Component({
  selector: 'app-test-page',
  templateUrl: './test-page.component.html',
  styleUrls: ['./test-page.component.scss']
})
export class TestPageComponent implements OnInit {

  candidate : Candidate = {
    name : 'Brandon David',
    address : 'Mont Roche, Beau Bassin',
    availability : true,
    cover : 'Dear .. .',
    dob : null,
    email : 'brandondavid220394@gmail.com',
    score : 0,
    phone : 57565279,
    status : 'Applied',
    experience : null,
    skills : null,
    gender : 'Male',
    education : null,
    applicationDate : null,
    completeApplication : true,
    internalApplication : true,
    rehire : false
  }
  availability : string = "Free 24/7";
  rate : number = 3.0;
  regDate : Date = new Date();

  constructor() { }

  ngOnInit() {
  }

}
