import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { RevocaEsenzione } from './revocaesenzione';
import { RevocaEsenzioneCertificata } from './revocaesenzionecertificata';
import { Autocertificazione } from './autocertificazione';
import { AutocertificazioneFam } from './autocertificazionefam';
import { AutocertificazioneASL } from './autocertificazioneasl';

import { ConfigService } from "../services/config.service";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class AutocertificazioneStoreService {



    private headers: Headers;

baseUrl = this.config.getBEServerContext();


    constructor(private http:Http
            ,private config: ConfigService
           ) {
    }


    revocaAutocertificazione(revocaesenzione: RevocaEsenzione): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/revocaEsenzioneCittadino/`
        return this.http.put(url, revocaesenzione, options)
               .map(success => success)
               .catch(this.handleError);
    }

    revocaAutocertificazioneASL(revocaesenzione: RevocaEsenzione): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/revocaEsenzioneOperatore/`
        return this.http.put(url, revocaesenzione, options)
               .map(success => success)
               .catch(this.handleError);
    }

     revocaEsenzioneCertificata(revocaesenzione: RevocaEsenzioneCertificata): Observable<Response | any> {

            let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
            let options = new RequestOptions({ headers: cpHeaders });
            const url = `${this.baseUrl}/revocaEsenzioneCertificataOperatore/`
            return this.http.put(url, revocaesenzione, options)
                   .map(success => success)
                   .catch(this.handleError);
        }

    revocaEsenzioneTotaliTitolare(revocaesenzione: RevocaEsenzione,protocollo: string): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/revocaEsenzioneTotaliTitolare/`+ protocollo
        return this.http.put(url, revocaesenzione, options)
               .map(success => success)
               .catch(this.handleError);
    }


    createAutocertificazione(autocertificazione: Autocertificazione): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/insertEsenzioneRedditoAutocertificazione/`
        return this.http.post(url, autocertificazione, options)
               .map(success => success)
               .catch(this.handleError);
    }
    createAutocertificazioneASL(autocertificazioneASL: AutocertificazioneASL): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/insertEsenzioneOperatore/`
        return this.http.post(url, autocertificazioneASL, options)
               .map(success => success)
               .catch(this.handleError);
    }

    createAutocertificazioneFam(autocertificazione: AutocertificazioneFam): Observable<Response | any> {

        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/insertEsenzioneRedditoFamiliare/`
        return this.http.post(url, autocertificazione, options)
               .map(success => success)
               .catch(this.handleError);
    }



    createReport(idesenzione: number, path: string, cf_beneficiario: string) : any{

        const url = `${this.baseUrl}/getReportCertificatoEsenzione/`+ idesenzione + '/' + path+ '/' + cf_beneficiario


        return this.http.get(url, { responseType: ResponseContentType.Blob}).map(
                (res) => {
                        return new Blob([res.blob()], { type: 'application/pdf' })
                    })
    }
    createReportStorico(protocollo: string, codesenzione: string,datainival: string, path: string, tipoesenzione: string, cf_beneficiario: string) : any{
          const url = `${this.baseUrl}/getReportCertificatoEsenzioneStorico/`+ protocollo + '/' + codesenzione  + '/' + datainival  + '/' + path  + '/' + tipoesenzione+ '/' + cf_beneficiario
         return this.http.get(url, { responseType: ResponseContentType.Blob}).map(
                 (res) => {
                         return new Blob([res.blob()], { type: 'application/pdf' })
                     })
     }

    private setHeaders() {
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/pdf');

    }

    private extractData(res: Response) {
        let body = res.json();
        return body;
    }


    private handleError (error: Response | any) {
      console.error(error.message || error);
      return Observable.throw(error);
  }
}
