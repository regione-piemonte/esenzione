import { Injectable } from '@angular/core';
import { Http, RequestOptions,Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Assistito } from './assistito';
import { UserInfo } from './userinfo';
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import * as $ from 'jquery';
import { Router } from '@angular/router';
import { ConfigService } from "../services/config.service";
import { FiltriRicercaAssistito } from './FiltriRicercaAssistito';
import { ricercaAssistitoResponse } from 'app/appoperatorepato/ricercaAssistito/ricercaAssistito.response';
import { certificato } from 'app/esenzione/certificato';
import { esenzioneAssistito } from 'app/esenzione/esenzioneAssistito';
import { Azione } from "app/assistito/azione";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Injectable()
export class AssistitoStoreService {
    userinfo: UserInfo;
    assistito: Assistito;
    lista_certificati: certificato[];
    lista_esenzioni: esenzioneAssistito[];

    baseUrl = this.config.getBEServerContext();


  constructor(private http: Http,
          private router: Router
          ,private config: ConfigService

          )
   {}


  getUser(): UserInfo{
      return this.userinfo;
  }

  setUser(utente: UserInfo){
      this.userinfo = utente;
  }

  getAssistito(): Assistito{
      return this.assistito;
  }

  setAssistito(assistito: Assistito){
      this.assistito = assistito;
  }

  abilitatoAdAzione(codAzione: string): boolean {
      let azione: Azione = this.userinfo.azioni.find(
              iter => iter.codAzione == codAzione);
      if(azione)
          return true;
      else
          return false;
  }


  getListaCertificati(): certificato[] {
      return this.lista_certificati;
  }

  setListaCertificati(certificati: certificato[]) {
      this.lista_certificati = certificati;
  }

  getListaEsenzioni(): esenzioneAssistito[] {
      return this.lista_esenzioni;
  }

  setListaEsenzioni(esenzioneAssistito: esenzioneAssistito[]){
      this.lista_esenzioni = esenzioneAssistito;
  }

  findCittadino(id: string): Observable<Assistito> {
      const url = `${this.baseUrl}/findCittadino/`+id;
      return this.http.get(url)
                .map(response => response.json().data as Assistito)
                .catch(this.handleError);
  }


  getLogout(): Observable<any> {
      const url = `${this.baseUrl}/localLogout/`;
      return this.http.get(url)
                .catch(this.handleError);


    }


 findCittadini(cittadinoricerca: CittadinoRicerca): Observable<Response | any> {

      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/findCittadini/`;
      return this.http.post(url,cittadinoricerca, options)
                 .map(success => success)
                .catch(this.handleError);
  }


 findCittadinoFuoriRegione(id: string): Observable<Response | any> {

     let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
     let options = new RequestOptions({ headers: cpHeaders });
     const url = `${this.baseUrl}/findCittadinoFuoriRegione/`+id;
     return this.http.get(url, options)
                .map(success => success)
               .catch(this.handleError);
 }



  getUserInfo(): Observable<UserInfo> {
      const url = `${this.baseUrl}/getUserInfo/`;
      return this.http.get(url)
                .map(response => response.json().data as UserInfo);
    }



  getLoginEsenpat(): Observable<Response | any> {
      const url = `${this.baseUrl}/loginEsenpat/`;
      return this.http.get(url)
      .map(success => success)
      .catch(this.handleError);
    }

  getLoginEsenred(): Observable<Response | any> {
      const url = `${this.baseUrl}/loginEsenred/`;
      return this.http.get(url)
      .map(success => success)
      .catch(this.handleError);
    }

  private handleError (error: Response | any) {
      console.error(error.message || error);
      return Observable.throw(error);
  }

  private extractDataLogin(res: any ) {

      let body = res.json();
      if (body.code != 200) {
          (document.getElementById("messaggiononaccesso").innerHTML) =body.data;
          document.getElementById("utnonaut").style.visibility='visible';

          } else {
              return body.data;


      }
  }
  private extractData(res: any ) {

      let body = res.json();
      if (res.status == 200) {
          if (body.data == "" || body.data == []) {
              (document.getElementById("messaggioricercatabella").innerHTML) =body.message;
              $(".tabellaricerca").hide();
              $(".tabellaricercames").show();
          } else {
              $(".tabellaricercames").hide();
              $(".tabellaricerca").show();
              (document.getElementById("numtrovati").innerHTML) =body.data.length
              return body.data;
        }
      }
  }

  ricercaAssistito(filtri: FiltriRicercaAssistito): Observable<Response | any> {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    const url = `${this.baseUrl}/ricercaAssistito/`;
    return this.http.post(url, filtri, options)
                    .map(success => success);
  }

  findCittadiniPato(cittadinoricerca: CittadinoRicerca): Observable<Response | any> {

      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/findCittadinipato/`;
      return this.http.post(url,cittadinoricerca, options)
                 .map(success => success)
                .catch(this.handleError);
  }
}
