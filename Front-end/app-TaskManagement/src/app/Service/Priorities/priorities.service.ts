import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Priorities } from '../../models/priorities.models';

@Injectable({
  providedIn: 'root'
})
export class PrioritiesService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase =  environment.apiUrl + '/api/v1/priorities/';

  public getPriorities():Observable<Priorities[]> {
    return this.http.get<Priorities[]>(this.URLBase);
  }
  public getPriority(id: number) {
    return this.http.get(this.URLBase + id);
  }
}
