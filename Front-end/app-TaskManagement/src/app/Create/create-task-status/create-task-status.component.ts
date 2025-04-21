import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterLink } from '@angular/router';
import { TaskStatusCreate } from '../../models/TaskStatus.model';
import { TaskStatusService } from '../../Service/TasksStatus/task-status.service';

@Component({
  selector: 'app-create-task-status',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, RouterLink],
  templateUrl: './create-task-status.component.html',
  styleUrl: './create-task-status.component.css'
})
export class CreateTaskStatusComponent {

  private readonly formBuildr = inject(FormBuilder);
  taskStatusServices = inject(TaskStatusService);
  router = inject(Router)
  
  form = this.formBuildr.group({
    name: ['']
  })

  guardarCambios(){
    let taskStatus = this.form.value as TaskStatusCreate;
    this.taskStatusServices.crear(taskStatus).subscribe(()=>{
      this.router.navigate(["taskStatus"])
    })
  }
}
