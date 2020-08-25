import { Component, OnInit } from '@angular/core';
import {Account} from "../../model/account.model";
import {AccountService} from "../../service/account.service";

@Component({
  selector: 'app-list-of-accounts',
  templateUrl: './list-of-accounts.component.html',
  styleUrls: ['./list-of-accounts.component.css']
})
export class ListOfAccountsComponent implements OnInit {
  accounts?: Account[];

  constructor(public accountService: AccountService) { }

  ngOnInit(): void {
    this.accountService.find().subscribe(res => {
      this.accounts = res.body;
    });
  }

  delete(account: Account) {

  }
}
