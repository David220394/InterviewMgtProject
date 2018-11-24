import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.scss']
})
export class ErrorPageComponent implements OnInit {

  code : any;
  errMsg : string;
  constructor(private route : ActivatedRoute,private router : Router) { }

  ngOnInit() {
    this.code = this.route.snapshot.paramMap.get('code');
    if(this.code == 409){
      this.errMsg = "You don't have Admin Rights";
    }
  }

  onClickToDashboard(){
    this.router.navigate(['']);
  }

}
