import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PremiereLeagueTableComponent } from './premiere-league-table.component';

describe('PremiereLeagueTableComponent', () => {
  let component: PremiereLeagueTableComponent;
  let fixture: ComponentFixture<PremiereLeagueTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PremiereLeagueTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PremiereLeagueTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
