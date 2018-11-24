import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router'
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { PipelineComponent } from '../components/pipeline/pipeline.component';
import { CandidateProfileComponent } from '../components/candidate-profile/candidate-profile.component';
import { AddJobComponent } from '../components/add-job/add-job.component';
import { JobListComponent } from '../components/job-list/job-list.component';
import { TestPageComponent } from '../components/test-page/test-page.component';
import { RegisterHrComponent } from '../components/register-hr/register-hr.component';
import { InterviewsComponent } from '../components/candidate-profile/interviews/interviews.component';
import { MainNavComponent } from '../main-nav/main-nav.component';
import { CandidateListComponent } from '../components/candidate-list/candidate-list.component';
import { QuizComponent } from '../components/quiz/quiz.component';
import { ErrorPageComponent } from '../components/error-page/error-page.component';
import { UserListComponent } from '../components/user-list/user-list.component';
import { UpdateQuizComponent } from '../components/update-quiz/update-quiz.component';

const routes: Routes = [
  {path: '', component: MainNavComponent,children:[
  { path: 'dashboard', component: DashboardComponent },
  { path: 'pipeline', component: PipelineComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'candidate/:id', component: CandidateProfileComponent },
  { path: 'addJob', component: AddJobComponent },
  { path: 'jobList', component: JobListComponent },
  { path: 'employeeList', component: UserListComponent },
  { path: 'candidateList', component: CandidateListComponent },
  { path: 'candidatePage', component : CandidateProfileComponent },
  { path: 'candidatePage/:jobId', component : CandidateProfileComponent },
  { path: 'createQuiz', component: QuizComponent },
  { path: 'updateQuiz', component: UpdateQuizComponent },
  { path: 'regPage', component: RegisterHrComponent },
  ]},
  { path: 'interview/:link', component: InterviewsComponent },
  { path: 'error/:code', component: ErrorPageComponent}
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
  ],
  exports : [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
