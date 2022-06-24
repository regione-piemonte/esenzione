import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { datarich } from './datarich';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
import { ConfigService } from "../services/config.service";

@Injectable()
export class datarichStoreService {


    baseUrl = this.config.getBEServerContext();

  constructor(private http: Http,private config: ConfigService
          ) { }

  getDataRich(codEsenzione: string): Observable<datarich[]> {

      const url = `${this.baseUrl}/getDataInizioScadenza/` + codEsenzione;
      return this.http.get(url)
                .map(response => response.json().data as datarich[]);
  }

}
