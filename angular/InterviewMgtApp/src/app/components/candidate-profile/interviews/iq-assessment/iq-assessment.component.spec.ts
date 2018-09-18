import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IqAssessmentComponent } from './iq-assessment.component';

describe('IqAssessmentComponent', () => {
  let component: IqAssessmentComponent;
  let fixture: ComponentFixture<IqAssessmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IqAssessmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IqAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
