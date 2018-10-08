import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // LocalStorage token key
  private LOCALSTORAGE_TOKEN_KEY = 'token';

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
    this.router.navigate(['/home']);
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
}
