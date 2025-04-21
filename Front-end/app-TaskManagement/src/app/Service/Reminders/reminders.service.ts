import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Reminders } from '../../models/reminders.models';

@Injectable({
  providedIn: 'root'
})
export class RemindersService {

  constructor() { }
  private http = inject(HttpClient);
  private URLBase = environment.apiUrl = 'api/v1/reminders'
  // getAll Reminders
  getAll():Observable<Reminders[]>{
    return this.http.get<Reminders[]>(this.URLBase);
  }
}
