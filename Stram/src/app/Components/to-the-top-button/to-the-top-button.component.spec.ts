import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToTheTopButtonComponent } from './to-the-top-button.component';

describe('ToTheTopButtonComponent', () => {
  let component: ToTheTopButtonComponent;
  let fixture: ComponentFixture<ToTheTopButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ToTheTopButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ToTheTopButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
