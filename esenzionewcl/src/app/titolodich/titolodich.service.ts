import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { ConfigService } from "../services/config.service";
import { TitoloDich } from './titolodich';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Injectable()
export class TitoloDichStoreService {


    baseUrl = this.config.getBEServerContext();



  constructor(private http: Http,private config: ConfigService
) { }

  getListaTitoloDich(): Observable<TitoloDich[]> {
    const url = `${this.baseUrl}/getTitoliDichiarante/`;

    return this.http.get(url)
              .map(response => response.json().data as TitoloDich[]);

  }
  getListaTitoloFam(): Observable<TitoloDich[]> {
      const url = `${this.baseUrl}/getTitoliFamiliare/`;

      return this.http.get(url)
                .map(response => response.json().data as TitoloDich[]);

    }

}
