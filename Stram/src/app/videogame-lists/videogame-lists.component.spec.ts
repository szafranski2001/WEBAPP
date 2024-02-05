import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogameListsComponent } from './videogame-lists.component';

describe('VideogameListsComponent', () => {
  let component: VideogameListsComponent;
  let fixture: ComponentFixture<VideogameListsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VideogameListsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VideogameListsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
