import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { Router, RouterLink } from '@angular/router';
import { TagsService } from '../../Service/Tags/tags.service';
import { task } from '../../models/task.models';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RequestRegisterSubTags } from '../../models/Tags.models';

@Component({
  selector: 'app-create-tags',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, RouterLink],
  templateUrl: './create-tags.component.html',
  styleUrl: './create-tags.component.css'
})
export class CreateTagsComponent {

  private tagsServices = inject(TagsService);
  private readonly formBuildr = inject(FormBuilder);
  router = inject(Router)


  tags?: task[];
  columnasAMostar = ['name', 'acciones'];

  form = this.formBuildr.group({
    name: ['']
  })

    guardarCambios(){
      let tags = this.form.value as RequestRegisterSubTags;
      this.tagsServices.created(tags).subscribe(()=>{
        this.router.navigate(["tag"])
      })
    }

}
