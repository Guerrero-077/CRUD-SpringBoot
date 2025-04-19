import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { categories } from '../../models/categories.models';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor() { }

  private http = inject(HttpClient);
  private URLBase = environment.apiUrl + '/api/v1/categories/';

  public getCategories(): Observable<categories[]> {
    return this.http.get<categories[]>(this.URLBase);
  }

}
