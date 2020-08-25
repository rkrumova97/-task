import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfAccountsComponent } from './list-of-accounts.component';

describe('ListOfAccountsComponent', () => {
  let component: ListOfAccountsComponent;
  let fixture: ComponentFixture<ListOfAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
