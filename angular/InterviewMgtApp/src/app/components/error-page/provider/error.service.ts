import { Injectable, ErrorHandler } from '@angular/core';
import {UNAUTHORIZED, BAD_REQUEST, FORBIDDEN} from "http-status-codes";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ErrorService implements ErrorHandler {

  public readonly REFRESH_PAGE_ON_TOAST_CLICK_MESSAGE: string = "An error occurred: Please click this message to refresh";
  public readonly DEFAULT_ERROR_TITLE: string = "Something went wrong";

  constructor(private router: Router) { }
  handleError(error: any): void {
    console.error(error);
    let httpErrorCode = error.httpErrorCode;
    switch (httpErrorCode) {

      default:
         this.showError(this.REFRESH_PAGE_ON_TOAST_CLICK_MESSAGE);
    }
  }

  private showError(message:string){
  }
}
