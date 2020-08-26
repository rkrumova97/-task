import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {ListOfAccountsComponent} from "./components/list-of-accounts/list-of-accounts.component";
import {routes} from "./app-routing.module";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AccountCreateUpdateComponent} from "./components/account-create-update/account-create-update.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ListOfAccountsComponent,
    AccountCreateUpdateComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
