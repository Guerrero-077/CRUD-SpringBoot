import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { TaskStatus, TaskStatusCreate } from '../../models/TaskStatus.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskStatusService {

  constructor() { }
  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/tasks-status';

  public getTaskStatus(): Observable<TaskStatus[]> {
    return this.http.get<TaskStatus[]>(this.URLBase)
  }

  public crear(taskStatus: TaskStatusCreate){
    return this.http.post(this.URLBase, taskStatus)
  }
}
