import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { MeterData } from '../modals/meterData';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private host = "http://localhost:5000"

  constructor(private _http: HttpClient) { }
  cards() {
    return [71, 78, 39, 66];
  }

  getAllMeterData(): Observable<any>{
    return this._http.get<any>(`${this.host}/getMeterData`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert("Please check your internet connection!.");
    return throwError(errorMessage);
 }

  
}
