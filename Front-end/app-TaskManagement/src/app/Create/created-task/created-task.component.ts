import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { provideNativeDateAdapter } from '@angular/material/core';
import { FormTaskComponent } from '../../form-task/form-task.component';
import { taskCreacion } from '../../models/task.models';
import { TaskService } from '../../Service/Tasks/task.service';



@Component({
  selector: 'app-created-task',
  providers: [provideNativeDateAdapter()],
  imports: [FormTaskComponent],
  templateUrl: './created-task.component.html',
  styleUrl: './created-task.component.css'
})
export class CreatedTaskComponent {

  taskService = inject(TaskService);
  router = inject(Router);

  guardar(task: taskCreacion) {
    this.taskService.created(task).subscribe(() => {
      this.router.navigate(['/']);
    });
  }


  //console log del formulari
  // guardar(){
  //   console.log(this.form.value);
  // }

  delete(id: number) {
    this.taskService.delete(id).subscribe(() => {
      this.router.navigate(['/']);
    })
  }
}

