import { TestBed, inject } from '@angular/core/testing';

import { RegisterHrService } from './register-hr.service';

describe('RegisterHrService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegisterHrService]
    });
  });

  it('should be created', inject([RegisterHrService], (service: RegisterHrService) => {
    expect(service).toBeTruthy();
  }));
});
