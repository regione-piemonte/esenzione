import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from "@angular/http";
import { ConfigService } from "app/services/config.service";
import { Observable } from "rxjs";
import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import {  Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class InitCertFields  {

    baseUrl = this.config.getBEServerContext();

    lista_diagnosi: Diagnosi[] = [];
    lista_esen: Esenzione[] = [];

    constructor(private http: Http,private config: ConfigService) {}

    getDiagnosiFromServer(): Observable<Response | any>{
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });

       const url = `${this.baseUrl}/diagnosi`;
       return this.http.get(url, options);
    }

    getEsenzioniFromServer(): Observable<Response | any>{
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });

       const url = `${this.baseUrl}/esenzioni`;
       return this.http.get(url, options);
    }

}

