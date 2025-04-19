import { Routes } from '@angular/router';
import { ListaTareasComponent } from './lista-tareas/lista-tareas.component';
import { HistorialComponent } from './historial/historial.component';
import { TareasCompletadasComponent } from './tareas-completadas/tareas-completadas.component';
import { CreatedTaskComponent } from './created-task/created-task.component';

export const routes: Routes = [
{path: '', component: ListaTareasComponent}, // Ruta por defecto
{path: 'tareasCompletadas', component: TareasCompletadasComponent}, // Ruta para la lista de tareas
{path: 'historial', component: HistorialComponent}, // Ruta para el historial de tareas
{path: 'tasks/created', component: CreatedTaskComponent}
];
