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
export class InitEsenFields  {

    baseUrl = this.config.getBEServerContext();

    tipi_documento: any = [];

    constructor(private http: Http,private config: ConfigService) {}

    getTipiDocumentoFromServer(gruppo: string): Observable<Response | any>{
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });

       const url = `${this.baseUrl}/documentoTipo/`+gruppo;
       return this.http.get(url, options);
    }

    getPrestazioniEsenti(codiceDiagnosi: string): Observable<Response | any>{
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });

       const url = `${this.baseUrl}/getPrestazioniEsenti/`+codiceDiagnosi;
       return this.http.get(url, options);
    }
}

