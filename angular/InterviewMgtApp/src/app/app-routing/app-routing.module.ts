import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router'
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { PipelineComponent } from '../components/pipeline/pipeline.component';
import { CandidateProfileComponent } from '../components/candidate-profile/candidate-profile.component';
import { AddJobComponent } from '../components/add-job/add-job.component';
import { JobListComponent } from '../components/job-list/job-list.component';
import { TestPageComponent } from '../components/test-page/test-page.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'pipeline', component: PipelineComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'candidate/:id', component: CandidateProfileComponent },
  { path: 'addJob', component: AddJobComponent },
  { path: 'jobList', component: JobListComponent },
  { path: 'candidatePage', component : CandidateProfileComponent },
  { path: 'testPage', component: TestPageComponent }
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
