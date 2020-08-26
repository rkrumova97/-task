import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListOfAccountsComponent} from "./components/list-of-accounts/list-of-accounts.component";
import {AccountCreateUpdateComponent} from "./components/account-create-update/account-create-update.component";


export const routes: Routes = [
  {
    path: '',
    component: ListOfAccountsComponent,
  },{
    path: 'create-update/:id',
    component: AccountCreateUpdateComponent,
  },
  {
    path: 'create-update',
    component: AccountCreateUpdateComponent,
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
