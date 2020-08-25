import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListOfAccountsComponent} from "./components/list-of-accounts/list-of-accounts.component";


export const routes: Routes = [
  {
    path: '',
    component: ListOfAccountsComponent,
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
