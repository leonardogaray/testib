import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FileInfo } from '../models/fileInfo';

//let API_URL = 'http://localhost:8765/api/course/service/';
let API_URL = 'http://localhost:8001/service/';

@Injectable({
  providedIn: 'root'
})
export class StockOptionLoadService {

  constructor(private http: HttpClient) { 

  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', API_URL + 'upload/', formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(API_URL + 'files/');
  }

  process(fileInfo: FileInfo): Observable<any> {
    return this.http.get(API_URL + 'process/' + fileInfo.name);
  }
}
