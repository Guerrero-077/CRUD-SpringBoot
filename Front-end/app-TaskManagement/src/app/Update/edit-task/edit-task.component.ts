import { Component, inject, Input, numberAttribute, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormTaskComponent } from '../../form-task/form-task.component';
import { task, taskCreacion } from '../../models/task.models';
import { TaskService } from '../../Service/Tasks/task.service';

@Component({
  selector: 'app-edit-task',
  imports: [FormTaskComponent],
  templateUrl: './edit-task.component.html',
  styleUrl: './edit-task.component.css'
})
export class EditTaskComponent implements OnInit {
  @Input({ transform: numberAttribute })
  id!: number

  taskService = inject(TaskService);
  router = inject(Router);
  model?: task;

  ngOnInit(): void {
    this.taskService.getAllById(this.id).subscribe(task => {

      this.model = task;
    })
  }

  guadar(task: taskCreacion){
    this.taskService.update(this.id, task).subscribe(() => {
      this.router.navigate(['/'])
    })
  }
}
