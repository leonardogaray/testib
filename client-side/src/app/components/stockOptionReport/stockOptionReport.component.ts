import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockOptionReportService } from '../../services/stockOptionReport.service';
import { ActivatedRoute } from '@angular/router';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-stock-option-report',
  templateUrl: './stockOptionReport.component.html',
  styleUrls: ['./stockOptionReport.component.css']
})
export class StockOptionReportComponent implements OnInit {
  //[{ data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' }]
  public lineChartData: ChartDataSets[] = [];
  //['January', 'February', 'March', 'April', 'May', 'June', 'July']
  public lineChartLabels: Label[] = [];
  public lineChartOptions: ChartOptions = {
    responsive: true,
  };
  public lineChartColors: Color[] = [];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  constructor(private stockOptionReportService: StockOptionReportService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.has('filename')){
        this.stockOptionReportService.getCSV(params.get('filename')).subscribe((data: any) => {
          this.transformData(data);
        });
      }
    });
    
  }

  transformData(data: any): void {
    let temp: any[] = [];
    let final: any[] = [];

    //Group elements by brand
    data.forEach(element => {
      if(!temp[element.brand]){
        temp[element.brand] = [];
      }
      temp[element.brand].push(element);
    });
    
    //Generate ng2-charts structure
    let index = 0;
    for(var key in temp){
      let serie: any = {};
      serie["label"] = key;
      serie["data"] = [];
      temp[key].forEach(obj =>{
        serie["data"].push(obj.price);
        if(index === 0)
          this.lineChartLabels.push(obj.date);
      });
      final.push(serie);
      index++;
    };

    this.lineChartData = final;
  }

}
