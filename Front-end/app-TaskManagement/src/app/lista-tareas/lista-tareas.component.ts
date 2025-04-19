import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { task } from '../models/task.models';
import { MatTableModule } from '@angular/material/table';
import { TaskService } from '../Service/TasksCategory/task.service';
import { Tags } from '../models/Tags.models';
import { TagsService } from '../Service/Tags/tags.service';

@Component({
  selector: 'app-lista-tareas',
  imports: [CommonModule, MatButtonModule, RouterLink, MatTableModule],
  templateUrl: './lista-tareas.component.html',
  styleUrl: './lista-tareas.component.css'
})
export class ListaTareasComponent {
  TaskService = inject(TaskService);
  tagsServices = inject(TagsService);


  tareas?: task[];
  tags?: Tags[];


  columnasAmostras = ['Title', 'description', 'creation_date', 'expiration_date', 'estado', 'tasksTags', 'accciones'];
  constructor() {
    this.TaskService.getTasks().subscribe((data) => {
      this.tareas = data;
    });

    this.tagsServices.getTags().subscribe((data) => {
      this.tags = data;
    });
  }


}
