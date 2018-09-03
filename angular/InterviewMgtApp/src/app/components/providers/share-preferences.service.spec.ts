import { TestBed, inject } from '@angular/core/testing';

import { SharePreferencesService } from './share-preferences.service';

describe('SharePreferencesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SharePreferencesService]
    });
  });

  it('should be created', inject([SharePreferencesService], (service: SharePreferencesService) => {
    expect(service).toBeTruthy();
  }));
});
