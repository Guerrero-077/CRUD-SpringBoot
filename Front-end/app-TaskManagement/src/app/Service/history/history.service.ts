import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { history } from '../../models/history.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/history'
  private URLDetails = environment.apiUrl + '/api/v1/history/with-details'

  public getHistory(): Observable<history[]> {
    return this.http.get<history[]>(this.URLDetails);
  }
}
