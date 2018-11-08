import { Injectable } from '@angular/core';
import { Tracking } from '../../dtos/tracking';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { finalize } from 'rxjs/operators';
import { Contact } from '../../dtos/contact';
import { SharePreferencesService } from '../../../providers/share-preferences.service';
import { LoginService } from '../../../login-dialog/providers/login.service';
import { DatePipe } from '@angular/common';
import { suggestedDateTime } from '../../contact-dialog/contact-dialog.component';


@Injectable({
  providedIn: 'root'
})
export class TrackingService {

  constructor(private datePipe: DatePipe, private http: HttpClient, private shrePreferences: SharePreferencesService, private authenticationService: LoginService) { }

  public insertTracking(contact: Contact) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    this.http.post<Contact>(environment.url + '/tracking/', contact, httpOptions).subscribe((data: any) => {
      console.log(data);
    }
    );
  }

  public findTrackingByJobIdAndCandidateId(jobId: string, candidateId: string): Observable<Tracking[]> {
    return new Observable(observer => {
      this.http.get(environment.url + '/tracking/' + jobId + '/' + candidateId)
        .pipe(finalize(() => { observer.complete(); }))
        .subscribe((data: any) => {
          let trackings: Tracking[] = [];
          data.forEach((element: any) => {
            trackings.push({
              candidateName: element.candidate.candidateName,
              employeeName: element.employee.employeeName,
              jobName: element.job.jobName,
              creationDate: element.creationDate,
              comment: element.comment
            })
          });
          console.log(trackings);
          observer.next(trackings);
        },
          error => {
            observer.error(error);
          });
    });
  }

  public sentMail(comment: string, type: string, email: string, time : suggestedDateTime): Observable<any> {
    return new Observable(observer => {
      if(type == 'INTERVIEW'){

        let mail = {
          "subject": "Invitation for an Interview for "+this.shrePreferences.getJobName(),
          "body": {
            "contentType": "HTML",
            "content": "Dear "+this.shrePreferences.getCandidateName()+"<br/><br/> This is an invitation for an interview <br/><br/>"+comment
          },
          "start": {
            "dateTime":time.start,
            "timeZone": "UTC"
          },
          "end": {
            "dateTime":time.end,
            "timeZone": "UTC"
        },
          "attendees": [
            {
              "emailAddress": {
                "address":email,
                "name": this.shrePreferences.getJobId()+"/"+this.shrePreferences.getCandidateId()+"Job Interview"
              },
              "type": "required"
            }]
        }

          this.http.post('https://graph.microsoft.com/v1.0/me/events', mail)
            .subscribe((data: any) => {
              observer.next(data)
            })

      }else if(type == 'SIGN_CONTRACT'){
        this.getFreeTimeSlot(1, 2, 3).subscribe((data) => {
          let mail = {
            "subject": "Invitation to Sign Contract for "+this.shrePreferences.getJobName(),
            "body": {
              "contentType": "HTML",
              "content": "Dear "+this.shrePreferences.getCandidateName()+"<br/><br/> This is an invitation to sign contract <br/><br/>"+comment
            },
            "start": {
              "dateTime":data.meetingTimeSuggestions[0].meetingTimeSlot.start.dateTime,
              "timeZone": "UTC"
            },
            "end": {
              "dateTime": data.meetingTimeSuggestions[0].meetingTimeSlot.end.dateTime,
              "timeZone": "UTC"
          },
            "attendees": [
              {
                "emailAddress": {
                  "address":email,
                  "name": this.shrePreferences.getJobId()+"/"+this.shrePreferences.getCandidateId()+"Job Interview"
                },
                "type": "required"
              }]
          }

            this.http.post('https://graph.microsoft.com/v1.0/me/events', mail)
              .subscribe((data: any) => {
                observer.next(data)
              })
          })
      }else{
       let mail = {
        "message": {
          "subject": "Rejection of Job Application for "+this.shrePreferences.getJobName(),
          "body": {
            "contentType": "Text",
            "content": "Dear "+this.shrePreferences.getCandidateName()+"<br/><br/>Sorry, You have been rejected :) <br/><br/>"+comment
          },
          "toRecipients": [
            {
              "emailAddress": {
                "address": email
              }
            }
          ]
        }
        }
        this.http.post('https://graph.microsoft.com/v1.0/me/sendmail', mail)
        .subscribe((data: any) => {
          observer.next(data)
        })
      }
      })
  }


  public getFreeTimeSlot(duration: number, start: number, end: number): Observable<any> {
    let date: Date = new Date();
    let startDate: string = this.addDays(date, start * 7);
    let endDate: string = this.addDays(date, end * 7);

    console.log(startDate)
    let timeslot = {
      "timeConstraint": {
        "activityDomain": "work",
        "timeslots": [
          {
            "start": {
              "dateTime": startDate + "T08:00:00",
              "timeZone": "UTC"
            },
            "end": {
              "dateTime": endDate + "T20:00:00",
              "timeZone": "UTC"
            }
          }
        ]
      },
      "meetingDuration": "PT" + duration + "H"
    }
    return new Observable(observer => {
      this.http.post('https://graph.microsoft.com/beta/me/findMeetingTimes', timeslot)
        .pipe(finalize(() => { observer.complete(); }))
        .subscribe((data: any) => {
          observer.next(data)
        },
          (err: any) => {
            console.log(err)
            if (err.error.error.code === 'InvalidAuthenticationToken') {
              this.authenticationService.setOutlookNull();
              window.location.reload();
            }
          })
    })
  }


  private addDays(date, numberOfDays): string {
    var returnDate = new Date(
      date.getFullYear(),
      date.getMonth(),
      date.getDate() + numberOfDays,
      date.getHours(),
      date.getMinutes(),
      date.getSeconds());

    let returnDateString: string = this.datePipe.transform(returnDate, "yyyy-MM-dd")
    return returnDateString;
  }


}
