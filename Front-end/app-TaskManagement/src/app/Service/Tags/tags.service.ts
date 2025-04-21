import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Tags } from '../../models/Tags.models';

@Injectable({
  providedIn: 'root'
})
export class TagsService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/tags';

  getTags(): Observable<Tags[]> {
    return this.http.get<Tags[]>(this.URLBase);
  }

  created(tags: { name: string }): Observable<Tags> {
    return this.http.post<Tags>(this.URLBase, tags);
  }

  public delete(id: number) {
    return this.http.delete(this.URLBase + '/' + id);
  }

}
