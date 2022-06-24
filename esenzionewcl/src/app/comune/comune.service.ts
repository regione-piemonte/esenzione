import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { comune } from './comune';
import {isNull} from "util";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
import { ConfigService } from "../services/config.service";

@Injectable()
export class comuneStoreService {


    baseUrl = this.config.getBEServerContext();

  constructor(private http: Http,private config: ConfigService
          ) { }


  getComuni(filter: string): Observable<comune[]> {

      let url = `${this.baseUrl}/getComuni/`;
      if (!isNull(filter) && filter !== "") {
          url = url + filter;
        }

      return this.http.get(url)
                .map(response => response.json().data as comune[]);

  }

  search(text: string): Observable<any> {

      let query = {
        where: {
          or: [{descComune: {like: text, options: "i"}}],
        }
      };

      let filter = encodeURI(JSON.stringify(query));

      let url = `${this.baseUrl}/getComuni/` + text;

      return this.http.get(url).map(res => res.json().data as comune[]).catch(err => {

        return Observable.throw(err);
      });


    }

  getComune(id: string): Observable<comune> {

      let url = `${this.baseUrl}/getComuni/` + id;
      return this.http.get(url).map(res => res.json() as comune).catch(err => {

        return Observable.throw(err);
      });
    }

}
