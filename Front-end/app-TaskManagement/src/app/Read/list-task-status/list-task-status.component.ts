import { Component, inject } from '@angular/core';
import { TaskStatusService } from '../../Service/TasksStatus/task-status.service';
import { TaskStatus } from '../../models/TaskStatus.model';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-list-task-status',
  imports: [MatTableModule, MatButtonModule, RouterLink],
  templateUrl: './list-task-status.component.html',
  styleUrl: './list-task-status.component.css'
})
export class ListTaskStatusComponent {

  private taskStatusService = inject(TaskStatusService);
  taskStatus?: TaskStatus[];
  columnasAMostar = ['name', 'acciones'];

  constructor() {

    this.ListTaskStatus();
  }

  public ListTaskStatus() {
    this.taskStatusService.getTaskStatus().subscribe(data => {
      this.taskStatus = data;
    })
  }
}
