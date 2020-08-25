import {HttpClient, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {IAccount} from "../model/account.model";

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
      .get<IAccount[]>(`${this.resourceUrl}/get`, {observe: 'response'});
  }

}
