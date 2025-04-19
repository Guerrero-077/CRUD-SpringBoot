import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { SubTasks } from '../../models/subTasks.models';

@Injectable({
  providedIn: 'root'
})
export class SubTasksService {

  constructor() { }
  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/subtasks/';

  public getSubTasks(): Observable<SubTasks[]> {
    return this.http.get<SubTasks[]>(this.URLBase);
  }
}
