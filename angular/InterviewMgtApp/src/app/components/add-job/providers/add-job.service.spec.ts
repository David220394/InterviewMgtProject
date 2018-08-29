import { TestBed, inject } from '@angular/core/testing';

import { AddJobService } from './add-job.service';

describe('AddJobService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddJobService]
    });
  });

  it('should be created', inject([AddJobService], (service: AddJobService) => {
    expect(service).toBeTruthy();
  }));
});
