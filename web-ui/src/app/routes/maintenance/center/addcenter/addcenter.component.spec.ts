import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcenterComponent } from './addcenter.component';

describe('AddcenterComponent', () => {
  let component: AddcenterComponent;
  let fixture: ComponentFixture<AddcenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddcenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddcenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
