import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { task, taskCreacion } from '../../models/task.models';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor() { }
  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/tasks/';

  public getTasks(): Observable<task[]> {
    return this.http.get<task[]>(this.URLBase);
  }

  public created(task: taskCreacion) {
    return this.http.post(this.URLBase + 'crear', task);

  }
}
