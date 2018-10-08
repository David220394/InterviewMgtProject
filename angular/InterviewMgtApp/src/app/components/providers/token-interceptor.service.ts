import { Injectable } from '@angular/core';import { HttpInterceptor } from '@angular/common/http';
import { LoginService } from '../login-dialog/providers/login.service';
;

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authenticationService: LoginService) { }

  intercept(req, next) {


    const token = this.authenticationService.getTokenFromLocalStorage();

    if (token != null) {

      // Clone the request
      const reqClone = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
          //Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzeWx2aW8uYnJhbmRvbi5kYXZpZCIsInJvbGUiOiJST0xFX0FETUlOIiwiaWF0IjoxNTM4MDM4NTc1LCJleHAiOjE1MzgwNTY1NzV9.0jNp99WJ0vNaV2o3MY9mfsfUQfW6JLi2gAk58JT3FAA'
        }
      });

      console.log(reqClone);

      return next.handle(reqClone);
    } else {
      return next.handle(req);
    }
  }
}
