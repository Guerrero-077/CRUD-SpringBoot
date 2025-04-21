import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, retry } from 'rxjs';
import { environment } from '../../../environments/environment';
import { categories, RequestRegisterCategories } from '../../models/categories.models';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/categories';

  public getCategories(): Observable<categories[]> {
    return this.http.get<categories[]>(this.URLBase);
  }

  public getAllById(id: number): Observable<categories> {
    return this.http.get<categories>(`${this.URLBase}/${id}`);
  }
  public created(category: RequestRegisterCategories) {
    return this.http.post(this.URLBase, category);
  }

  public update(id: number, task: RequestRegisterCategories) {
    return this.http.put(`${this.URLBase}/${id}`, task);
  }

  public delete(id: number) {
    return this.http.delete(this.URLBase + '/' + id);
  }


}
