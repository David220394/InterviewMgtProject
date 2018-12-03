import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // LocalStorage token key
  private LOCALSTORAGE_TOKEN_KEY = 'token';
  private LOCALSTORAGE_OUTLOOK_KEY = 'outlook';
  private LOCALSTORAGE_USER_ROLE = 'role';
  private LOCALSTORAGE_USER_NAME = 'username'

  // Header
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  private jwtHelper: JwtHelperService;

  constructor(
    private router: Router,
    private http: HttpClient) {
    this.jwtHelper = new JwtHelperService();
  }

  public Login(user):Observable<boolean>{
    return new Observable(observer => {
      this.http.post("http://localhost:8082/authentication/login", user, this.httpOptions)
      .pipe(finalize(() => { observer.complete(); } ))
      .subscribe((res: any) => {
        this.storeTokenToLocalStorage(res.token);
        this.http.post("http://localhost:8082/authentication/token",res.token, this.httpOptions)
        .pipe(finalize(() => { observer.complete(); } ))
      .subscribe((employee : any) =>{
        console.log(employee.role);
        this.storeRoleToLocalStorage(employee.role);
        this.storeUsernameToLocalStorage(employee.employeeId);
      });

        observer.next(true);
      }, (err: any) => {
        observer.next(false);
      });
    })
  }
  /**
   * Check if a user is logged in or not
   */
  public isLoggedIn(): boolean {
    const token = localStorage.getItem(this.LOCALSTORAGE_TOKEN_KEY);

    if (token != null) {
      return !this.jwtHelper.isTokenExpired(token);
    }

    return false;
  }


  /**
   * Logout
   */
  public logout(): void {
    localStorage.removeItem(this.LOCALSTORAGE_TOKEN_KEY);
    localStorage.removeItem(this.LOCALSTORAGE_USER_ROLE);
    this.router.navigate(['']);
  }


  /**
   * Get token from localstorage
   */
  public getTokenFromLocalStorage(): string {
    return localStorage.getItem(this.LOCALSTORAGE_TOKEN_KEY);
  }

  /**
   * Store token inside localstorage
   */
  private storeTokenToLocalStorage(token): void {
    localStorage.removeItem(this.LOCALSTORAGE_TOKEN_KEY);
    localStorage.setItem(this.LOCALSTORAGE_TOKEN_KEY, token);
  }

   /**
   * Get role from localstorage
   */
  public getRoleFromLocalStorage(): string {
    return localStorage.getItem(this.LOCALSTORAGE_USER_ROLE);
  }

  public isValidUser(){
    if(localStorage.getItem(this.LOCALSTORAGE_USER_ROLE) != 'ADMIN'){
      this.router.navigate(['/error/409']);
    }
  }

  public isAdmin(){
      return localStorage.getItem(this.LOCALSTORAGE_USER_ROLE) === 'ADMIN';
  }

   /**
   * Store role inside localstorage
   */
  private storeRoleToLocalStorage(role): void {
    localStorage.removeItem(this.LOCALSTORAGE_USER_ROLE);
    localStorage.setItem(this.LOCALSTORAGE_USER_ROLE, role);
  }

  /**
   * Get token from localstorage
   */
  public getUsernameFromLocalStorage(): string {
    return localStorage.getItem(this.LOCALSTORAGE_USER_NAME);
  }

  /**
   * Store token inside localstorage
   */
  private storeUsernameToLocalStorage(username): void {
    localStorage.removeItem(this.LOCALSTORAGE_USER_NAME);
    localStorage.setItem(this.LOCALSTORAGE_USER_NAME, username);
  }



/**
 * Check if outlook token is still available
 */
isLoginToOutlook(){
  if(localStorage.getItem(this.LOCALSTORAGE_OUTLOOK_KEY) !=null){
    return true
  }
  //Redirect to the authentication page of microsoft
  window.location.href='https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id='+
  environment.outlookId+
  '&redirect_uri=http://localhost:4200/dashboard&response_type=code&scope='+
  environment.scope;

}

/**
 * Obtain the oulook token
 * From the code received
 */
setOutlookToken(code : string):Observable<any>{
console.log("In method Set Outlook Token")
  const httpParams : HttpParams = new HttpParams() //Prepare request body
    .set('client_id',environment.outlookId)
    .set('client_secret',environment.outlookSecret)
    .set('code',code)
    .set('redirect_uri','http://localhost:4200/dashboard')
    .set('grant_type','authorization_code')
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        })
    };
  return new Observable(observer => {
    this.http.post("https://login.microsoftonline.com/common/oauth2/v2.0/token", httpParams, httpOptions)
    .pipe(finalize(() => { observer.complete(); } ))
    .subscribe((data: any) => {
      const outlook : string = data.access_token
      this.storeOutlookToLocalStorage(outlook); //Store token in local Storage
      observer.next(data);
    },(err : any) =>{
        observer.error(err)
    })
  });
}

  private storeOutlookToLocalStorage(outlook): void {
    if(localStorage.getItem(this.LOCALSTORAGE_OUTLOOK_KEY)){
    localStorage.removeItem(this.LOCALSTORAGE_OUTLOOK_KEY);
    }
    localStorage.setItem(this.LOCALSTORAGE_OUTLOOK_KEY, outlook);
  }

  public getOutlookFromLocalStorage(): string {
    return localStorage.getItem(this.LOCALSTORAGE_OUTLOOK_KEY);
  }

  public setOutlookNull(){
    localStorage.removeItem(this.LOCALSTORAGE_OUTLOOK_KEY);
  }
}
