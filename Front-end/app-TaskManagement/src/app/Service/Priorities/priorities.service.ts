import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Priorities, RequestRegisterPriorities } from '../../models/priorities.models';

@Injectable({
  providedIn: 'root'
})
export class PrioritiesService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase =  environment.apiUrl + '/api/v1/priorities';

  public getPriorities():Observable<Priorities[]> {
    return this.http.get<Priorities[]>(this.URLBase);
  }
  public getAllById(id: number): Observable<Priorities> {
    return this.http.get<Priorities>(`${this.URLBase}/${id}`);
  }
  public created(category: RequestRegisterPriorities) {
    return this.http.post(this.URLBase, category);
  }

  public update(id: number, task: RequestRegisterPriorities) {
    return this.http.put(`${this.URLBase}/${id}`, task);
  }

  public delete(id: number) {
    return this.http.delete(this.URLBase + '/' + id);
  }
}
