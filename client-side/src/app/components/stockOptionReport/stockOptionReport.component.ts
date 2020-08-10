import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockOptionReportService } from '../../services/stockOptionReport.service';
import { FileInfo } from '../../models/fileInfo';

@Component({
  selector: 'app-stock-option-report',
  templateUrl: './stockOptionReport.component.html',
  styleUrls: ['./stockOptionReport.component.css']
})
export class StockOptionReportComponent implements OnInit {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(private stockOptionReportService: StockOptionReportService) { }

  ngOnInit(): void {
    
  }

}
