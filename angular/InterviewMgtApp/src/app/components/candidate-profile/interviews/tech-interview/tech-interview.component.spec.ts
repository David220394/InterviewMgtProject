import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechInterviewComponent } from './tech-interview.component';

describe('TechInterviewComponent', () => {
  let component: TechInterviewComponent;
  let fixture: ComponentFixture<TechInterviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechInterviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechInterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
