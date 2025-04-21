import { Component, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { categories } from '../../models/categories.models';
import { CategoriesService } from '../../Service/Categories/categories.service';

@Component({
  selector: 'app-list-categoires',
  imports: [MatTableModule, CommonModule, RouterLink, MatButtonModule, MatDividerModule, MatIconModule, RouterLink],
  templateUrl: './list-categoires.component.html',
  styleUrl: './list-categoires.component.css'
})
export class ListCategoiresComponent {


  private categoriesServices = inject(CategoriesService);
  categories?: categories[];
  columnasAMostrar = ['name', 'acciones'];


  constructor() {
    this.listarCategories();
  }

  listarCategories() {
    this.categoriesServices.getCategories().subscribe(data => this.categories = data)

  }
  delete(id: number) {
    this.categoriesServices.delete(id).subscribe(() => {
      this.listarCategories();
    })
  }

}
