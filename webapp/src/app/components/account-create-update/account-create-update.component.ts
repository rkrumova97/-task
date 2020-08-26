import {Component, OnInit} from '@angular/core';
import {Account, IAccount} from "../../model/account.model";
import {AccountService} from "../../service/account.service";
import {ActivatedRoute, Route} from "@angular/router";


@Component({
  selector: 'app-account-delete-dialog',
  templateUrl: './account-create-update.component.html',
  styleUrls: ['./account-create-update.component.css']
})
export class AccountCreateUpdateComponent implements OnInit {

  account?: IAccount;

  submitted = false;

  id?: string;

  constructor(protected accountService: AccountService, public route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.account = new Account();

    this.account.id = this.route.snapshot.paramMap.get('id');
  }


  process(): void {
    if(this.account.id !== null){
      this.accountService.update(this.account).subscribe();
    } else {
      this.account.id = null;
      this.accountService.create(this.account).subscribe();
    }
  }

}
