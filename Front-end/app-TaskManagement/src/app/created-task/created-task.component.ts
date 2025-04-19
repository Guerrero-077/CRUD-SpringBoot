import { Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterLink } from '@angular/router';
import { taskCreacion } from '../models/task.models';
import { Priorities } from '../models/priorities.models';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { SubTasks } from '../models/subTasks.models';
import { SubTasksService } from '../Service/subTasks/sub-tasks.service';
import { categories } from '../models/categories.models';
import { TaskService } from '../Service/TasksCategory/task.service';
import { PrioritiesService } from '../Service/Priorities/priorities.service';
import { CategoriesService } from '../Service/Categories/categories.service';
import { TagsService } from '../Service/Tags/tags.service';
import { Tags } from '../models/Tags.models';

@Component({
  selector: 'app-created-task',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, RouterLink, MatSelectModule, FormsModule, MatCheckboxModule],
  templateUrl: './created-task.component.html',
  styleUrl: './created-task.component.css'
})
export class CreatedTaskComponent {

  private readonly formBuilder = inject(FormBuilder);
  taskService = inject(TaskService);
  prioritiesServices = inject(PrioritiesService);
  subTasksServices = inject(SubTasksService);
  categoriesServices = inject(CategoriesService);
  tagsServices = inject(TagsService);

  router = inject(Router);

  priorities?: Priorities[];
  subTasks?: SubTasks[];
  categories?: categories[];
  tags?: Tags[];

  form = this.formBuilder.group({
    title: [''],
    description: [''],
    expiration_date: [''],
    priority_id: [''],
  });

  constructor() {
    this.prioritiesServices.getPriorities().subscribe((data) => {
      this.priorities = data;
    });

    this.subTasksServices.getSubTasks().subscribe((data) => {
      this.subTasks = data;
    });

    this.categoriesServices.getCategories().subscribe((data) => {
      this.categories = data;
    });

    this.tagsServices.getTags().subscribe((data) => {
      this.tags = data;
    });
  }


  guardar() {

    let task = this.form.value as taskCreacion;
    this.taskService.created(task).subscribe(() => {
      this.router.navigate(['/']);
    })
  }

  // guardar(){
  //   console.log(this.form.value);
  // }
}
