import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
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
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { PipelineCandidateComponent } from './components/pipeline-candidate/pipeline-candidate.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    PipelineComponent,
    CandidateProfileComponent,
    JobListComponent,
    AddJobComponent,
    MainNavComponent,
    PipelineCandidateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    LayoutModule,
    MatTabsModule,
    MatSelectModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
