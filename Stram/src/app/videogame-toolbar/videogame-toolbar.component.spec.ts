import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogameToolbarComponent } from './videogame-toolbar.component';

describe('VideogameToolbarComponent', () => {
  let component: VideogameToolbarComponent;
  let fixture: ComponentFixture<VideogameToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VideogameToolbarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VideogameToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
