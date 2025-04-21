import { Component, inject } from '@angular/core';
import { TagsService } from '../../Service/Tags/tags.service';
import { MatButtonModule } from '@angular/material/button';
import { Tags } from '../../models/Tags.models';
import { CommonModule } from '@angular/common';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-list-tags',
  imports: [MatTableModule, CommonModule, RouterLink, MatButtonModule, MatDividerModule, MatIconModule, RouterLink],
  templateUrl: './list-tags.component.html',
  styleUrl: './list-tags.component.css'
})
export class ListTagsComponent {

  private tagsServices = inject(TagsService);

  tag?: Tags[];
  columnasAMostrar = ['name', 'acciones'];

  constructor() {
    this.listarCategories();
  }

  listarCategories() {
    this.tagsServices.getTags().subscribe(data => this.tag = data)

  }
  delete(id: number) {
    this.tagsServices.delete(id).subscribe(() => {
      this.listarCategories();
    })
  }

}
