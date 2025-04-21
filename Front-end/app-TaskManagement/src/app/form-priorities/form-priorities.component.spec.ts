import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPrioritiesComponent } from './form-priorities.component';

describe('FormPrioritiesComponent', () => {
  let component: FormPrioritiesComponent;
  let fixture: ComponentFixture<FormPrioritiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormPrioritiesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPrioritiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
