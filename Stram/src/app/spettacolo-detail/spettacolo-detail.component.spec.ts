import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpettacoloDetailComponent } from './spettacolo-detail.component';

describe('SpettacoloDetailComponent', () => {
  let component: SpettacoloDetailComponent;
  let fixture: ComponentFixture<SpettacoloDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SpettacoloDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpettacoloDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
