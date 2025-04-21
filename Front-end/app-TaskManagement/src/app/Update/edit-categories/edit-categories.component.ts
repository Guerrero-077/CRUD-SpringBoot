import { Component, inject, Input, numberAttribute, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormulariosComponent } from '../../formularios/formularios.component';
import { categories, RequestRegisterCategories } from '../../models/categories.models';
import { CategoriesService } from '../../Service/Categories/categories.service';

@Component({
  selector: 'app-edit-categories',
  imports: [FormulariosComponent],
  templateUrl: './edit-categories.component.html',
  styleUrl: './edit-categories.component.css'
})
export class EditCategoriesComponent implements OnInit {
  @Input({ transform: numberAttribute })
  id!: number;

  categoriesServices = inject(CategoriesService);
  router = inject(Router);
  modelo?: categories;

  ngOnInit(): void {
    this.categoriesServices.getAllById(this.id).subscribe(category => {
      this.modelo = category;
    })
  }

  guardarCambios(category: RequestRegisterCategories){
    this.categoriesServices.update(this.id, category).subscribe(() => {
      this.router.navigate(['/categories'])
    })
  }
}
