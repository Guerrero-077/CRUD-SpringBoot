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
  private URLBase = environment.apiUrl + '/api/v1/tasks/with-details';
  private URLCrear = environment.apiUrl + '/api/v1/tasks';

  public filter(id: number): Observable<task[]>{
    return this.http.get<task[]>(`${this.URLCrear}/filter/${id}`)
  }

  public getTasks(): Observable<task[]> {
    return this.http.get<task[]>(this.URLBase);
  }

  public getAllById(id: number): Observable<task> {
    return this.http.get<task>(`${this.URLCrear}/${id}`);
  }
  
  public created(task: taskCreacion) {
    return this.http.post(this.URLCrear, task);
  }

  public update(id: number, task: taskCreacion) {
    return this.http.put(`${this.URLCrear}/${id}`, task);
  }

  updateActive(id: number, active: boolean) {
    return this.http.put(`${this.URLCrear}/toggle-active/${id}?active=${active}`, null);
  }

  public delete(id: number) {
    return this.http.delete(this.URLCrear + '/' + id);
  }
}
