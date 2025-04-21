import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormArray, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RouterLink } from '@angular/router';
import { categories } from '../models/categories.models';
import { Priorities } from '../models/priorities.models';
import { Tags } from '../models/Tags.models';
import { TaskStatus } from '../models/TaskStatus.model';
import { CategoriesService } from '../Service/Categories/categories.service';
import { PrioritiesService } from '../Service/Priorities/priorities.service';
import { SubTasksService } from '../Service/subTasks/sub-tasks.service';
import { TagsService } from '../Service/Tags/tags.service';
import { TaskStatusService } from '../Service/TasksStatus/task-status.service';
import { task, taskCreacion } from '../models/task.models';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-form-task',
  providers: [provideNativeDateAdapter()],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
    MatSelectModule,
    FormsModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule],
  templateUrl: './form-task.component.html',
  styleUrl: './form-task.component.css'
})
export class FormTaskComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  prioritiesServices = inject(PrioritiesService);
  subTasksServices = inject(SubTasksService);
  categoriesServices = inject(CategoriesService);
  tagsServices = inject(TagsService);
  taskStatusServices = inject(TaskStatusService);

  tags?: Tags[];
  priorities?: Priorities[];
  categories?: categories[];
  taskStatus?: TaskStatus[];

  @Input({ required: true })
  name!: string;

  @Input()
  model?: task;

  @Output()
  posteoFormulario = new EventEmitter<taskCreacion>();

  ngOnInit(): void {
    if (this.model !== undefined) {
      this.form.patchValue(this.model);
    }
  }

  form = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    description: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(500)]],
    active: [true],
    creation_date: [new Date().toISOString()],
    expirationDate: ['', [Validators.required, this.futureDateValidator]],
    priorityId: ['', Validators.required],
    taskStatusId: ['', Validators.required],
    subTasks: this.fb.array([], [Validators.maxLength(10)]),
    reminders: this.fb.array([]),
    categoryIds: this.fb.array([], [Validators.required, Validators.minLength(1)]),
    tagIds: this.fb.array([]),
  });

  // Validador personalizado para fechas futuras
  private futureDateValidator(control: any) {
    const selectedDate = new Date(control.value);
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    if (selectedDate < today) {
      return { pastDate: true };
    }
    return null;
  }

  get subTasksFormArray(): FormArray {
    return this.form.get('subTasks') as FormArray;
  }

  get remindersFormArray(): FormArray {
    return this.form.get('reminders') as FormArray;
  }
  constructor() {
    this.prioritiesServices.getPriorities().subscribe((data) => this.priorities = data);
    this.categoriesServices.getCategories().subscribe((data) => this.categories = data);
    this.tagsServices.getTags().subscribe((data) => this.tags = data);
    this.taskStatusServices.getTaskStatus().subscribe((data) => this.taskStatus = data);
  }

  addSubTask(): void {
    if (this.subTasksFormArray.length < 10) {
      this.subTasksFormArray.push(this.fb.group({
        name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
      }));
    }
  }

  addReminder(): void {
    this.remindersFormArray.push(this.fb.group({
      name: ['']
    }));
  }

  removeSubTask(index: number): void {
    this.subTasksFormArray.removeAt(index);
  }

  removeReminders(index: number): void {
    this.remindersFormArray.removeAt(index);
  }

  onCategoryChange(event: any, categoryId: number) {
    const categoriesArray = this.form.get('categoryIds') as FormArray;

    if (event.checked) {
      categoriesArray.push(this.fb.control(categoryId));
    } else {
      const index = categoriesArray.controls.findIndex(x => x.value === categoryId);
      if (index !== -1) categoriesArray.removeAt(index);
    }
  }

  onTagChange(event: any, tagId: number) {
    const tagsArray = this.form.get('tagIds') as FormArray;

    if (event.checked) {
      tagsArray.push(this.fb.control(tagId));
    } else {
      const index = tagsArray.controls.findIndex(x => x.value === tagId);
      if (index !== -1) tagsArray.removeAt(index);
    }
  }

  guardar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const task = this.form.value as taskCreacion;
    this.posteoFormulario.emit(task);
  }

  // guardar(){
  //   console.log(this.form.value)
  // }

  getErrorMessage(controlName: string): string {
    const control = this.form.get(controlName);

    if (!control || !control.errors) return '';

    if (control.hasError('required')) {
      return 'Este campo es requerido';
    } else if (control.hasError('minlength')) {
      return `Mínimo ${control.getError('minlength').requiredLength} caracteres`;
    } else if (control.hasError('maxlength')) {
      return `Máximo ${control.getError('maxlength').requiredLength} caracteres`;
    } else if (control.hasError('pastDate')) {
      return 'La fecha debe ser futura';
    }

    return '';
  }
}