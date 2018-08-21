import { TestBed, inject } from '@angular/core/testing';

import { PipelineCandidateService } from './pipeline-candidate.service';

describe('PipelineCandidateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PipelineCandidateService]
    });
  });

  it('should be created', inject([PipelineCandidateService], (service: PipelineCandidateService) => {
    expect(service).toBeTruthy();
  }));
});
