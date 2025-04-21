import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCategoiresComponent } from './list-categoires.component';

describe('ListCategoiresComponent', () => {
  let component: ListCategoiresComponent;
  let fixture: ComponentFixture<ListCategoiresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListCategoiresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCategoiresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
