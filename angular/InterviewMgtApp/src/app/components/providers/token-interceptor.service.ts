import { Injectable } from '@angular/core'; import { HttpInterceptor, HttpResponse, HttpEvent } from '@angular/common/http';
import { LoginService } from '../login-dialog/providers/login.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Route, Router } from '@angular/router';
;

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authenticationService: LoginService,private router : Router) { }

  intercept(req, next): Observable<HttpEvent<any>> {
    const token = this.authenticationService.getTokenFromLocalStorage();
    const outlook = this.authenticationService.getOutlookFromLocalStorage();
    if (req['url'].includes("http://localhost:8082")) {
      if (token != null) {

        // Clone the request
        let reqClone = req.clone({
          setHeaders: {
            Authorization: 'Bearer ' + token,
          }
        });
        if(req['url'].includes("createPdf/")){
          reqClone = req.clone({
            setHeaders: {
              Authorization: 'Bearer ' + token,
              Accept : 'application/pdf'
            }
          });
        }


        console.log(reqClone);

        return next.handle(reqClone).pipe(tap((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse && event['status'] == 401) {
            this.router.navigate(['']);
          }
          return event;
        }));
      } else {
        return next.handle(req).pipe(tap((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            console.log(event)
          }
          return event;
        }));
      }
    }
    if(req['url'].includes('https://graph.microsoft.com/v1.0') || req['url'].includes('https://graph.microsoft.com/')){
      const reqClone = req.clone({
        setHeaders: {
          'Authorization' : 'Bearer '+ outlook,
          'Accept' : 'application/json',
          'Content-Type' : 'application/json',
          'X-AnchorMailbox' : 'json@contoso.onmicrosoft.com',
          'Prefer': 'outlook.timezone= "Mauritius Standard Time"'
        }
      });

      console.log(reqClone);

      return next.handle(reqClone).pipe(tap((event: HttpEvent<any>) => {
        console.log(event)
        if (event['status'] == 401) {
          console.log(event)
        }
        return event;
      }));
    }

    return next.handle(req).pipe(tap((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        console.log(event)
      }
      return event;
    }));
  }

}
