import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { HistoryService } from '../../Service/history/history.service';
import { history } from '../../models/history.model';

@Component({
  selector: 'app-list-history',
  imports: [
    CommonModule,
    MatButtonModule,
    MatTableModule,
    MatCheckboxModule,
    MatIconModule,
    MatTooltipModule
  ],
  templateUrl: './list-history.component.html',
  styleUrl: './list-history.component.css'
})
export class ListHistoryComponent {

  private historyService = inject(HistoryService);
  history?: history[];
  
  columnasAMostrar = ['name', 'subtask','description', 'reminders', 'categories', 'tags','creation_date', 'expiration_date', 'active', 'priorities', 'TaskStatus'];


  router = inject(Router);
  
  constructor() {
    this.ListHistoy();
  }

  ListHistoy(){
    this.historyService.getHistory().subscribe((data)=> {
      this.history = data;
    });
  }
}
