import { TestBed } from '@angular/core/testing';

import { StockOptionReportService } from './stockOptionReport.service';

describe('StockOptionLoadService', () => {
  let service: StockOptionReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockOptionReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
