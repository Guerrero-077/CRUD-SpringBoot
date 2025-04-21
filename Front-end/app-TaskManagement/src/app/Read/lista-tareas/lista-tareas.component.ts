import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink, Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';

import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { Tags } from '../../models/Tags.models';
import { task } from '../../models/task.models';
import { TagsService } from '../../Service/Tags/tags.service';
import { TaskService } from '../../Service/Tasks/task.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { Priorities } from '../../models/priorities.models';
import { PrioritiesService } from '../../Service/Priorities/priorities.service';

@Component({
  selector: 'app-lista-tareas',
  imports: [
    CommonModule,
    MatButtonModule,
    RouterLink,
    MatTableModule,
    MatCheckboxModule,
    MatIconModule,
    MatTooltipModule,
    MatFormFieldModule,
    MatSelectModule

  ],
  templateUrl: './lista-tareas.component.html',
  styleUrl: './lista-tareas.component.css'
})
export class ListaTareasComponent {
  TaskService = inject(TaskService);
  tagsServices = inject(TagsService);

  pritiesService = inject(PrioritiesService);
  priorities?: Priorities[];
  task?: task[];



  Router = inject(Router);

  tareas?: task[];
  tags?: Tags[];


  columnasAmostras = ['name', 'subtask', 'description', 'reminders', 'categories', 'tags', 'creation_date', 'expiration_date', 'active', 'priorities', 'TaskStatus', 'accciones'];
  constructor() {
    this.ListTasks()
    this.tagsServices.getTags().subscribe((data) => {
      this.tags = data;
    });

    this.pritiesService.getPriorities().subscribe((data) => {
      this.priorities = data;
    })
  }

  ListTasks() {

    this.TaskService.getTasks().subscribe((data) => {
      this.tareas = data;
      console.log(data)
    });
  }

  delete(id: number) {
    this.TaskService.delete(id).subscribe(() => {
      this.ListTasks();
    })
  }
  toggleActive(tarea: any): void {
    const newActive = !tarea.active;
    this.TaskService.updateActive(tarea.id, newActive).subscribe({
      next: () => {
        tarea.active = newActive;
        console.log('Estado actualizado correctamente');
        this.ListTasks()
      },
      error: (err) => {
        console.error('Error al actualizar el estado:', err);
      }
    });
  }

  getDataByPriority(priorityId: number) {
    if (priorityId == 0) {
      this.ListTasks()
    } else {
      this.TaskService.filter(priorityId).subscribe((data) => {
        this.tareas = data;
      });
    }
  }


}
