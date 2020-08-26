import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountCreateUpdateComponent } from './account-create-update.component';

describe('AccountDeleteDialogComponent', () => {
  let component: AccountCreateUpdateComponent;
  let fixture: ComponentFixture<AccountCreateUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountCreateUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountCreateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
