import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyClubsComponent } from './modify-clubs.component';

describe('ModifyClubsComponent', () => {
  let component: ModifyClubsComponent;
  let fixture: ComponentFixture<ModifyClubsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyClubsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyClubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
