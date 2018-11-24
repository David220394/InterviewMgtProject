import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, finalize } from 'rxjs/operators';
import { MatDialog } from '@angular/material';
import { LoginDialogComponent } from '../components/login-dialog/login-dialog.component';
import { LoginService } from '../components/login-dialog/providers/login.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { environment } from '../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { TrackingService } from '../components/candidate-profile/providers/tracking/tracking.service';
import { SharePreferencesService } from '../components/providers/share-preferences.service';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent {

  isAdmin : boolean;
  selected: boolean=false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private loginService : LoginService, private http : HttpClient, public dialog: MatDialog, private breakpointObserver: BreakpointObserver, private authenticationService: LoginService,private activeRoute: ActivatedRoute, private test : TrackingService, private sharePref : SharePreferencesService) {
      if (!authenticationService.isLoggedIn()) {
      const dialogRef = this.dialog.open
        (LoginDialogComponent, {
          width: '50%',backdropClass : 'login-backdrop'
        });

      dialogRef.afterClosed().subscribe(result => {
        console.log("Closed")
      })
    }

    this.isAdmin = loginService.isAdmin();

    let code;
    this.activeRoute.queryParamMap
    .subscribe((next : ParamMap) => {
       code =  next.get("code")
       console.log(code)
       if(!code){
        if(this.authenticationService.isLoginToOutlook()){
          if(this.sharePref.isCalender()){
            this.test.getFreeTimeSlot(1,2,3).subscribe();
          }
        }
      }else{
        console.log(code)
        this.authenticationService.setOutlookToken(code).subscribe();
      }
    })
  }


  onSelect(){
    this.selected=true;
  }

  logout(){
    this.authenticationService.logout();
  }


}
