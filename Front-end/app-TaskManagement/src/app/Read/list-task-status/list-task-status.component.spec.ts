import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTaskStatusComponent } from './list-task-status.component';

describe('ListTaskStatusComponent', () => {
  let component: ListTaskStatusComponent;
  let fixture: ComponentFixture<ListTaskStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListTaskStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListTaskStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
