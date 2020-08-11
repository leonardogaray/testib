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

  public lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
  ];
  public lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartOptions: (ChartOptions & { annotation: any }) = {
    responsive: true,
  };
  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  constructor(private stockOptionReportService: StockOptionReportService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.has('filename')){
        this.stockOptionReportService.getCSV(params.get('filename')).subscribe((data: any) => {
          let temp: any[] = [];
          let final: any[] = [];

          data.forEach(element => {
            if(!temp[element.brand]){
              temp[element.brand] = [];
            }
            temp[element.brand].push(element);
          });
          
          for(var key in temp){
            let serie: any = {};
            serie["name"] = key;
            serie["serie"] = [];
            temp[key].forEach(obj =>{
              serie["serie"].push({name: obj.date, value: obj.price});
            });
            final.push(serie);
          };

          this.csv = final;
        });
      }
    });
    
  }

  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

}
