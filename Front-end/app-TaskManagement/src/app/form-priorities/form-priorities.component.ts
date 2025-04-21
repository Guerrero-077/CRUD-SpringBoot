import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Priorities, RequestRegisterPriorities } from '../models/priorities.models';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-form-priorities',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInput, MatButtonModule, RouterLink],
  templateUrl: './form-priorities.component.html',
  styleUrl: './form-priorities.component.css'
})
export class FormPrioritiesComponent implements OnInit {


  public readonly formBuilder = inject(FormBuilder);
  @Input({ required: true })
  name?: string;

  @Input()
  modelo?: Priorities;

  @Output()
  posteoFormulario = new EventEmitter<RequestRegisterPriorities>();
  form = this.formBuilder.group({
    name: ['']
  })

  ngOnInit(): void {
    if (this.modelo !== undefined) {
      this.form.patchValue(this.modelo)
    }
  }
  guardarPriorities() {
    let priority = this.form.value as RequestRegisterPriorities;
    this.posteoFormulario.emit(priority);
  }

}
