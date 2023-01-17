import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverworldPageComponent } from './overworld-page.component';

describe('OverworldPageComponent', () => {
  let component: OverworldPageComponent;
  let fixture: ComponentFixture<OverworldPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverworldPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OverworldPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
