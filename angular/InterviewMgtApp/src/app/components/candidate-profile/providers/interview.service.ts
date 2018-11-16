import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Interview } from '../dtos/interview';
import { environment } from '../../../../environments/environment';
import { Observable, observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { Candidate } from '../dtos/candidate';
import { Education } from '../dtos/education';
import { Experience } from '../dtos/experience';
import { Question } from '../dtos/question';
import { AfterInterview } from '../dtos/afterInterview';
import { SharePreferencesService } from '../../providers/share-preferences.service';
import { Score } from '../dtos/score';

@Injectable({
  providedIn: 'root'
})
export class InterviewService {

  constructor(private http : HttpClient) { }

  /**
   * name
   */
  public createInterview(interview : Interview) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    this.http.post<Interview>(environment.url + '/interview/',interview, httpOptions).subscribe((data : any)=>{
        console.log(data);
    }
    );
  }

  public updateInterview(afterInterview : AfterInterview) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    console.log(afterInterview);
    this.http.post<AfterInterview>(environment.url + '/interview/afterinterview/',afterInterview, httpOptions).subscribe((data : any)=>{
        console.log(data);
    }
    );
  }

  public getScore(jobId : string,cid : string):Observable<Score>{
    return new Observable(observable =>{
      this.http.get(environment.url + '/interview/score/'+jobId+'/'+cid)
      .pipe( finalize(() => { observable.complete(); }))
      .subscribe( (data: any) => {
        let intScore : Score ={
          hrScore : data.hrScore,
          techScore : data.techScore,
          iqScore : data.iqScore,
          aveScore : data.aveScore
        }
        observable.next(intScore);
      },
      error =>{
        observable.error(error);
      });
    });
  }

  public quizQuestions(jobId : number):Observable<any>{
    return new Observable(observable =>{
      this.http.get(environment.url + '/api/jobs/quiz/'+jobId)
      .pipe( finalize(() => { observable.complete(); }))
      .subscribe( (data: any) => {
        console.log(data)
          let questions : Question[]=[];

          data.questions.forEach(element => {
            let possibleAns : string[]=[];
            element.answers.forEach(ans => {
              possibleAns.push(ans)
            });
            questions.push( {
              question : element.question,
              answer : element.correctAnswer,
              possibleAnswer : possibleAns,
              mark : element.mark
            })
          });
          let quiz ={
            quizName : data.quizName,
            questions : questions
          }
          observable.next(quiz);
        },
        error =>{
          observable.error(error);
        });
    });
  }

  public accessInterviewPage(link : string): Observable<Interview>{
    return new Observable(observable =>{
      this.http.get(environment.url + '/interview/'+link)
      .pipe( finalize(() => { observable.complete(); }))
      .subscribe( (data: any) => {
        let candidate : Candidate;
        const experience: Experience  = {
          name : data.candidate.candidateExperience.experienceName,
          specialty : data.candidate.candidateExperience.specialty,
          location : data.candidate.candidateExperience.location
        };
        const education : Education = {
          institutionName : data.candidate.education.institutionName,
          grade : data.candidate.education.grade,
          programStudy : data.candidate.education.programStudy
        };

        candidate ={
          name : data.candidate.candidateName,
          address : data.candidate.candidateAddress,
          gender : data.candidate.gender,
          dob : data.candidate.dob,
          applicationDate : data.candidate.applicationDate,
          education : education,
          email : data.candidate.email,
          completeApplication : data.candidate.completeApplication,
          internalApplication : data.candidate.internalApplication,
          rehire : data.candidate.rehire,
          availability : data.candidate.availability,
          score : data.candidate.score,
          cover : data.candidate.coverLetter,
          phone : data.candidate.candidatePhone,
          status : data.candidate.status,
          experience : experience,
          skills : null
        }
        let interview : Interview={
          candidateId : data.candidate.candidateId,
          candidate : candidate,
          interviewer : data.interviewer,
          jobId : data.job.jobId,
          jobName : data.job.jobName,
          link : data.link,
          type : data.type
        }
        observable.next(interview);
      },
      error =>{
        observable.error(error);
        });
    });
  }

  public getCompletedInterview(jobId : string,cid : string):Observable<AfterInterview[]>{
    return new Observable(observable =>{
      this.http.get(environment.url + '/interview/'+jobId+'/'+cid)
      .pipe( finalize(() => { observable.complete(); }))
      .subscribe( (data: any) => {
        let afterInterviews : AfterInterview[] = [];
        data.forEach(element => {
          afterInterviews.push({
            score: element.score,
            feedback: element.feedback,
            link: element.link,
            type: element.type,
            maxScore : element.maxScore,
            interviewer: element.interviewer,
            endDate: element.endDateTime
          })
        });
        observable.next(afterInterviews);
      },
      error =>{
        observable.error(error);
      });
    })

  }

  public updateAssessmentInterview(quizName, quizForm,link)  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    let maxScore : number = 0;
    quizForm.questions.forEach(element => {
      maxScore += element.mark;
    });
    let afterInterview = {
        quizName : quizName,
        maxScore : maxScore,
        afterAssessmentQuestionDtos : quizForm.questions,
        interviewLink : link

    }
    console.log(afterInterview);
    this.http.post<AfterInterview>(environment.url + '/interview/assessmentInterview/',afterInterview, httpOptions).subscribe((data : any)=>{
        console.log(data);
    }
    );
  }

  public createQuiz(quiz){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        // 'Authorization': 'my-auth-token'
      })
    };
    this.http.post(environment.url + '/interview/createQuiz/',quiz, httpOptions).subscribe((data : any)=>{
      console.log(data);
  });
  }

}
