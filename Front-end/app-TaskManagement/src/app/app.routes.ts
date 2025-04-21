import { Routes } from '@angular/router';
import { CreateCategoriesComponent } from './Create/create-categories/create-categories.component';
import { CreatePrioritiesComponent } from './Create/create-priorities/create-priorities.component';
import { CreateTaskStatusComponent } from './Create/create-task-status/create-task-status.component';
import { CreatedTaskComponent } from './Create/created-task/created-task.component';
import { ListCategoiresComponent } from './Read/list-categoires/list-categoires.component';
import { ListHistoryComponent } from './Read/list-history/list-history.component';
import { ListPrioritiesComponent } from './Read/list-priorities/list-priorities.component';
import { ListTaskStatusComponent } from './Read/list-task-status/list-task-status.component';
import { ListaTareasComponent } from './Read/lista-tareas/lista-tareas.component';
import { EditCategoriesComponent } from './Update/edit-categories/edit-categories.component';
import { EditTaskComponent } from './Update/edit-task/edit-task.component';
import { ListTagsComponent } from './Read/list-tags/list-tags.component';
import { CreateTagsComponent } from './Create/create-tags/create-tags.component';



export const routes: Routes = [
    { path: '', component: ListaTareasComponent },


    { path: 'history', component: ListHistoryComponent },

    { path: 'categories', component: ListCategoiresComponent },
    { path: 'categories/created', component: CreateCategoriesComponent },
    { path: 'categories/editar/:id', component: EditCategoriesComponent },

    { path: 'tasks/created', component: CreatedTaskComponent },
    { path: 'tasks/editar/:id', component: EditTaskComponent },

    { path: 'taskStatus', component: ListTaskStatusComponent },
    { path: 'taskStatus/created', component: CreateTaskStatusComponent },

    { path: 'priority', component: ListPrioritiesComponent },
    { path: 'priorities/created', component: CreatePrioritiesComponent },

    {path:'tag', component: ListTagsComponent},
    {path:'tags/created', component: CreateTagsComponent},
];
