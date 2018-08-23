import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PipelineCandidateComponent } from './pipeline-candidate.component';

describe('PipelineCandidateComponent', () => {
  let component: PipelineCandidateComponent;
  let fixture: ComponentFixture<PipelineCandidateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PipelineCandidateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
