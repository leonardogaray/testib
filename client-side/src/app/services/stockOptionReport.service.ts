import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent  } from '@angular/common/http';
import { Observable } from 'rxjs';

//let API_URL = 'http://localhost:8765/api/course/service/';
let API_URL = 'http://localhost:8001/service/';

@Injectable({
  providedIn: 'root'
})
export class StockOptionReportService {

  constructor(private http: HttpClient) { 
    
  }

  getCSV(filename: string): Observable<any> {
    return this.http.get(API_URL + 'files/getCSV/' + filename);
  }

  getStrategies(filename: string): Observable<any> {
    return this.http.get(API_URL + 'simulate/' + filename);
  }
}
