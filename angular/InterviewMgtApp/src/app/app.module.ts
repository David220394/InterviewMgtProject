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
import { HttpClientModule } from '@angular/common/http';
import { SkillComponent } from './components/candidate-profile/skill/skill.component';
import { ExperienceComponent } from './components/candidate-profile/experience/experience.component';
import { TrackDialogComponent } from './components/candidate-profile/track-dialog/track-dialog.component';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { ContactDialogComponent } from './components/candidate-profile/contact-dialog/contact-dialog.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { PipelineCandidateComponent } from './components/pipeline/pipeline-candidate/pipeline-candidate.component';
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
    SkillComponent,
    ExperienceComponent,
    TrackDialogComponent,
    ContactDialogComponent,
    PipelineCandidateComponent
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
    HttpClientModule,
    FormsModule

  ],
  entryComponents : [TrackDialogComponent, ContactDialogComponent],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: true}}],
  bootstrap: [AppComponent]
})
export class AppModule { }
