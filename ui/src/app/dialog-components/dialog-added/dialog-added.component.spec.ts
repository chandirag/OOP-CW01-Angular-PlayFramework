import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddedComponent } from './dialog-added.component';

describe('DialogAddedComponent', () => {
  let component: DialogAddedComponent;
  let fixture: ComponentFixture<DialogAddedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
