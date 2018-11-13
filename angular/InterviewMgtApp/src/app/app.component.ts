import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { SharePreferencesService } from './components/providers/share-preferences.service';
import { LoginService } from './components/login-dialog/providers/login.service';
import { TrackingService } from './components/candidate-profile/providers/tracking/tracking.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {


  constructor(private test : TrackingService,private activeRoute: ActivatedRoute, private authenticationService : LoginService,private sharePref : SharePreferencesService) {

  }

  ngOnInit() {

  }

}
