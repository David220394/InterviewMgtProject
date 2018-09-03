import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterHrComponent } from './register-hr.component';

describe('RegisterHrComponent', () => {
  let component: RegisterHrComponent;
  let fixture: ComponentFixture<RegisterHrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterHrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterHrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
