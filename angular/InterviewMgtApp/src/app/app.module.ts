import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PipelineComponent } from './components/pipeline/pipeline.component';
import { CandidateProfileComponent } from './components/candidate-profile/candidate-profile.component';
import { JobListComponent } from './components/job-list/job-list.component';
import { AddJobComponent } from './components/add-job/add-job.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SkillComponent } from './components/candidate-profile/skill/skill.component';
import { ExperienceComponent } from './components/candidate-profile/experience/experience.component';
import { TrackDialogComponent } from './components/candidate-profile/track-dialog/track-dialog.component';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { ContactDialogComponent } from './components/candidate-profile/contact-dialog/contact-dialog.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { PipelineCandidateComponent } from './components/pipeline/pipeline-candidate/pipeline-candidate.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TestPageComponent } from './components/test-page/test-page.component';
import { BarRatingModule } from "ngx-bar-rating";
import { RegisterHrComponent } from './components/register-hr/register-hr.component';
import { InterviewDialogComponent } from './components/candidate-profile/interview-dialog/interview-dialog.component';
import { HrInterviewComponent } from './components/candidate-profile/interviews/hr-interview/hr-interview.component';
import { TechInterviewComponent } from './components/candidate-profile/interviews/tech-interview/tech-interview.component';
import { IqAssessmentComponent } from './components/candidate-profile/interviews/iq-assessment/iq-assessment.component';
import { InterviewsComponent } from './components/candidate-profile/interviews/interviews.component';
import { FeedbackComponent } from './components/candidate-profile/feedback/feedback.component';
import { LoginDialogComponent } from './components/login-dialog/login-dialog.component';
import { JwtModule } from '@auth0/angular-jwt';
import { TokenInterceptorService } from './components/providers/token-interceptor.service';
import { DatePipe } from '@angular/common';
import { CandidateListComponent } from './components/candidate-list/candidate-list.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { ChartsModule } from 'ng2-charts';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UpdateQuizComponent } from './components/update-quiz/update-quiz.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    PipelineComponent,
    CandidateProfileComponent,
    JobListComponent,
    AddJobComponent,
    MainNavComponent,
    SkillComponent,
    ExperienceComponent,
    TrackDialogComponent,
    ContactDialogComponent,
    PipelineCandidateComponent,
    TestPageComponent,
    RegisterHrComponent,
    InterviewDialogComponent,
    HrInterviewComponent,
    TechInterviewComponent,
    IqAssessmentComponent,
    InterviewsComponent,
    FeedbackComponent,
    LoginDialogComponent,
    CandidateListComponent,
    QuizComponent,
    ErrorPageComponent,
    UserProfileComponent,
    UserListComponent,
    UpdateQuizComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    LayoutModule,
    HttpClientModule,
    FormsModule,MatTabsModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    BarRatingModule,
    JwtModule,
    ChartsModule

  ],
  entryComponents : [TrackDialogComponent, ContactDialogComponent, InterviewDialogComponent, LoginDialogComponent],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: true}},  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  },
DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
