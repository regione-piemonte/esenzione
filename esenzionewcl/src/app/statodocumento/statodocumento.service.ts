import { Injectable } from '@angular/core';
import { Http, RequestOptions,Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { statodocumento } from './statodocumento';
import { ConfigService } from "../services/config.service";
import * as $ from 'jquery';
import { firmadocumento } from "app/statodocumento/firmadocumento";
import {datiFirmaDigitale} from './datiFirmaDigitale';
import { CaModel } from './caModel';
import {datiRichiestaOtp} from './datiRichiestaOtp';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class statodocumentoStoreService {

    baseUrl = this.config.getBEServerContext();
    listaStatoPraticaDaFare: statodocumento[] = [];
    listaStatoPraticaTutteLePratiche: statodocumento[] = [];
    listaStatoPratica: statodocumento[] = [];
    listaStatoDocumento: statodocumento[] = [];
    listaStatoPraticaRicerca: statodocumento[] = [];

  constructor(private http: Http,private config: ConfigService
         ) {}


  getStatoDocumento(): Observable<statodocumento[]> {

       const url = `${this.baseUrl}/getStatoDocumento/`;
      return this.http.get(url)
                .map(response => response.json().data as statodocumento[]);


  }

  getStatoPratica(skAzione: string): Observable<statodocumento[]> {

      const url = `${this.baseUrl}/getStatoPratica/`+skAzione;
     return this.http.get(url)
               .map(response => response.json().data as statodocumento[]);


 }


  FirmaDocumento(datifirma: firmadocumento): Observable<Response | any> {
      const url = `${this.baseUrl}/setFirmaDigitale/`;
       return this.http.post(url,datifirma)
       .map(success => success)
       .catch(this.handleError);

  }

  getStatoPratica1() {
      this.getStatoPratica("9")
          .subscribe(statoPratica => this.listaStatoPraticaDaFare = statoPratica);
      this.getStatoPratica("10")
          .subscribe(statoPratica => this.listaStatoPraticaTutteLePratiche = statoPratica);
      this.getStatoPratica("6")
      .subscribe( statodocumento => this.listaStatoPratica = statodocumento );
      this.getStatoPratica("26")
      .subscribe( statodocumento => this.listaStatoPraticaRicerca = statodocumento );
  }
  getStatoDocumento1() {
      this.getStatoDocumento()
          .subscribe( statodocumento => this.listaStatoDocumento = statodocumento );
  }

  private handleError (error: Response | any) {
      let body = error.json();
      console.log('sever error:', error);
      return Observable.throw(error);
  }

  firmaDocumentoDigitale(datifirma: datiFirmaDigitale): Observable<Response | any> {
    const url = `${this.baseUrl}/firmaDocumentoDigitale/`;
    return this.http.post(url, datifirma)
      .map(success => success)
      .catch(this.handleError);

  }

  richiestaOtpFirma(datiRichiesta: datiRichiestaOtp): Observable<Response | any> {
    const url = `${this.baseUrl}/richiestaOtpFirma/`;
    return this.http.post(url, datiRichiesta)
      .map(success => success)
      .catch(this.handleError);

  }


    getCaOtpTypes(): Observable<CaModel[]> {
        const url = `${this.baseUrl}/getOtpTypes/`;
        return this.http.get(url)
             .map(response => response.json().data as CaModel[]);
    }
}


