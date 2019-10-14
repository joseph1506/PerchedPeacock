import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcenterComponent } from './viewcenter.component';

describe('ViewcenterComponent', () => {
  let component: ViewcenterComponent;
  let fixture: ComponentFixture<ViewcenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewcenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
