import { TestBed } from '@angular/core/testing';

import { SpettacoloDataService } from './spettacolo-data.service';

describe('SpettacoloDataService', () => {
  let service: SpettacoloDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpettacoloDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
