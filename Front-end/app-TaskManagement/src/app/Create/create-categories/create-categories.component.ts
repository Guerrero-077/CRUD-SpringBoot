import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { Router, RouterLink } from '@angular/router';
import { FormulariosComponent } from '../../formularios/formularios.component';
import { RequestRegisterCategories } from '../../models/categories.models';
import { CategoriesService } from '../../Service/Categories/categories.service';

@Component({
  selector: 'app-create-categories',
  imports: [FormulariosComponent],
  templateUrl: './create-categories.component.html',
  styleUrl: './create-categories.component.css'
})
export class CreateCategoriesComponent {

  categoryService = inject(CategoriesService);
  router = inject(Router);

  public readonly formBuilder = inject(FormBuilder);
  form = this.formBuilder.group({
    name: ['']
  })

  guardarCategories(categories: RequestRegisterCategories) {
    this.categoryService.created(categories).subscribe(() => {
      this.router.navigate(['categories'])
    })
  }
}
