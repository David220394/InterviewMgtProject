import { TestBed, inject } from '@angular/core/testing';

import { UpdateQuizService } from './update-quiz.service';

describe('UpdateQuizService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UpdateQuizService]
    });
  });

  it('should be created', inject([UpdateQuizService], (service: UpdateQuizService) => {
    expect(service).toBeTruthy();
  }));
});
