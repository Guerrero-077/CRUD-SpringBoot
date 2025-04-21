import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { PrioritiesService } from '../../Service/Priorities/priorities.service';
import { Priorities } from '../../models/priorities.models';

@Component({
  selector: 'app-list-priorities',
  imports: [MatTableModule, CommonModule, RouterLink, MatButtonModule, MatDividerModule, MatIconModule, RouterLink],
  templateUrl: './list-priorities.component.html',
  styleUrl: './list-priorities.component.css'
})
export class ListPrioritiesComponent {

  private prioritiesServices = inject(PrioritiesService);
  priority?: Priorities[];
  columnasAMostrar = ['name', 'acciones'];


  constructor() {
    this.listarPriorities();
  }

  listarPriorities() {
    this.prioritiesServices.getPriorities().subscribe(data => this.priority = data)

  }

  delete(id: number) {
    this.prioritiesServices.delete(id).subscribe(() => {
      this.listarPriorities();
    })
  }
}
