import { Component, inject } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { FormPrioritiesComponent } from "../../form-priorities/form-priorities.component";
import { FormulariosComponent } from "../../formularios/formularios.component";
import { RequestRegisterPriorities } from "../../models/priorities.models";
import { PrioritiesService } from "../../Service/Priorities/priorities.service";


@Component({
  selector: 'app-create-priorities',
  imports: [ FormPrioritiesComponent],
  templateUrl: './create-priorities.component.html',
  styleUrl: './create-priorities.component.css'
})
export class CreatePrioritiesComponent {

  prioritiesServices = inject(PrioritiesService);
  router = inject(Router);

  public readonly formBuilder = inject(FormBuilder);
  form = this.formBuilder.group({
    name: ['']
  })

  guardarPriorities(priorities: RequestRegisterPriorities) {
    this.prioritiesServices.created(priorities).subscribe(() => {
      this.router.navigate(['priority'])
    })
  }
}
