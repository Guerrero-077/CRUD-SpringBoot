import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePrioritiesComponent } from './create-priorities.component';

describe('CreatePrioritiesComponent', () => {
  let component: CreatePrioritiesComponent;
  let fixture: ComponentFixture<CreatePrioritiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePrioritiesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePrioritiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
