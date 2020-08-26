import {HttpClient, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {IAccount} from "../model/account.model";
import {map} from "rxjs/operators";

type EntityResponseType = HttpResponse<IAccount[]>;


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  public resourceUrl = "http://localhost:8080";

  constructor(protected http: HttpClient) {
  }

  find(): Observable<EntityResponseType> {
    return this.http
      .get<IAccount[]>(`${this.resourceUrl}`, {observe: 'response'});
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  create(account: IAccount): Observable<HttpResponse<IAccount>> {
    return this.http
      .post<IAccount>(this.resourceUrl, account, { observe: 'response' });
  }

  update(account: IAccount): Observable<HttpResponse<IAccount>> {
    return this.http
      .put<IAccount>(this.resourceUrl, account, { observe: 'response' });  }

}
