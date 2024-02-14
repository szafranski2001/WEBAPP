import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewToolbarComponent } from './review-toolbar.component';

describe('ReviewToolbarComponent', () => {
  let component: ReviewToolbarComponent;
  let fixture: ComponentFixture<ReviewToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReviewToolbarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReviewToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
