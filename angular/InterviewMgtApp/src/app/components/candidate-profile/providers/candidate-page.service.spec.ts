import { TestBed, inject } from '@angular/core/testing';

import { CandidatePageService } from './candidate-page.service';

describe('CandidatePageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CandidatePageService]
    });
  });

  it('should be created', inject([CandidatePageService], (service: CandidatePageService) => {
    expect(service).toBeTruthy();
  }));
});
