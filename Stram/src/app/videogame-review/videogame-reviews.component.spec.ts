import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogameReviewsComponent } from './videogame-review.component';

describe('VideogameReviewsComponent', () => {
  let component: VideogameReviewsComponent;
  let fixture: ComponentFixture<VideogameReviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VideogameReviewsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VideogameReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
