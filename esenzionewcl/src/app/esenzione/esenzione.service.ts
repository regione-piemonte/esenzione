import { Injectable } from '@angular/core';
import {  Http, Response, Headers, URLSearchParams, RequestOptions, ResponseContentType  } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { EsenzioneCittadino } from './esenzionecittadino';
import { RicercaEsenzioneCittadino } from './ricercaesenzionecittadino';
import { Esenzione } from './esenzione';
import { RicercaEsenzioneOperatore } from "app/esenzione/ricercaesenzioneoperatore";
import { EsenzioneOperatore } from "app/esenzione/esenzioneoperatore";
//import { environment } from '../../environments/environment';
import { EsenzioneAsl } from './esenzioneAsl';
import {isNull} from "util";
import * as $ from 'jquery';
import { CodiceEsenzionePatologia } from "app/esenzione/codiceesenzionepatologia";
import { ConfigService } from "../services/config.service";
import { FiltroPaginazione } from "app/esenzione/filtropaginazione";
import { RicercaCertificatoPatologia } from "app/esenzione/ricercacertificatopatologia";
import { CertificatoPatologia } from './CertificatoPatologia';
import { errorHandler } from '@angular/platform-browser/src/browser';
import { FiltroValidaEsenzionePatologia } from "app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia";
import { FiltriDettaglioEsenzione } from './FiltriDettaglioEsenzione';
import { PraticaEsenzione } from './PraticaEsenzione';
import { FiltriRicercaPratiche } from './FiltriRicercaPratiche';
import { MotivazioneAnnRevRes } from './MotivazioneAnnRevRes';
import { StoricoEsenzione } from "app/esenzione/storicoEsenzione";
import { RichiestaRettificaDati } from "app/esenzione/richiestaRettificaDati";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class EsenzioneStoreService {


  baseUrl = this.config.getBEServerContext();
  public errcittadino : number;
  public dettaglioCertificato: CertificatoPatologia;
 public dettaglioEsenzione: PraticaEsenzione;
  listacodiceesenzionepatologia: CodiceEsenzionePatologia[] = [];
  loading = false;

  constructor(private http: Http,private config: ConfigService

          ) { }

  getListaEsenzioni(): Observable<Esenzione[]> {
    const url = `${this.baseUrl}/getEsenzioni/`;

    return this.http.get(url)
              .map(response => response.json().data as Esenzione[]);


  }

  getCodiceEsenzionePatologia(id: string): Observable<CodiceEsenzionePatologia[]> {
      //id=`${id}`;
      const url = `${this.baseUrl}/getCodiceEsenzionePatologia/`+id;
      return this.http.get(url)
                .map(response => response.json().data as CodiceEsenzionePatologia[]);
  }

  getListaCodiciEsenzioniPatologia(): Observable<CodiceEsenzionePatologia[]> {
      const url = `${this.baseUrl}/getCodiciEsenzionePatologia/`;

      return this.http.get(url)
                .map(response => response.json().data as CodiceEsenzionePatologia[]);


    }

  creaEsenzione(postData): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/inviaRichiestaEsenzione`;
      this.loading = true;
      return this.http.post(url,postData, options);
  }

  getListaCodiciEsenzioniPatologiaFiltro(): Observable<CodiceEsenzionePatologia[]> {
      const url = `${this.baseUrl}/getCodiceEsenzionePatologiaFiltro/`;

      return this.http.get(url)
                .map(response => response.json().data as CodiceEsenzionePatologia[]);


    }

  getCodiciEsenzioniPatologiaFiltro() {
      this.getListaCodiciEsenzioniPatologiaFiltro()
          .subscribe(CodiceEsenzionePatologia => {
              this.listacodiceesenzionepatologia = CodiceEsenzionePatologia
              this.listacodiceesenzionepatologia.forEach(elem => {
                elem['label'] = `${elem.esenzionetipologia.codEsenzione}: ${elem.esenzionetipologia.descEsenzione}`;
              });
//              this.loading = false;
          });
  }

    getEsenzioni(id: string): Observable<Esenzione[]> {
        //id=`${id}`;
        const url = `${this.baseUrl}/getEsenzione/`+id;
        return this.http.get(url)
                  .map(response => response.json().data as Esenzione[]);
    }

  getEsenzioneCittadinoCombo(ricercaesenzionecittadino: RicercaEsenzioneCittadino): Observable<EsenzioneCittadino[]> {
      const url = `${this.baseUrl}/getEsenzioniCittadino/`;
       return this.http.post(url,ricercaesenzionecittadino)
      .map(this.extractDataCittadino)
       .catch(this.handleError);

  }

  getEsenzioneCittadino(ricercaesenzionecittadino: RicercaEsenzioneCittadino): Observable<Response | any> {
      const url = `${this.baseUrl}/getEsenzioniCittadino/`;
       return this.http.post(url,ricercaesenzionecittadino)
       .map(success => success)
       .catch(this.handleError);

  }

  sbloccaEsenzione(filter: string): Observable<boolean> {

      let url = `${this.baseUrl}/sbloccaEsenzione/`;
      if (!isNull(filter) && filter !== "") {
          url = url + filter;
        }

      return this.http.get(url)
      .map(success => success)
      .catch(this.handleError);
  }


  getEsenzioneOperatore(ricercaesenzioneoperatore: RicercaEsenzioneOperatore): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getEsenzioniOperatore/`;
       return this.http.post(url,ricercaesenzioneoperatore, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getEsenzioniAsl(): Observable<EsenzioneAsl[]> {
      const url = `${this.baseUrl}/getEsenzioniAsl/`;
      return this.http.get(url)
      .map(response => response.json().data as EsenzioneAsl[]);
    }

  getEsenzioniTitolareRevoca(idTitolare: String): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

      const url = `${this.baseUrl}/getEsenzioniTitolareRevoca/`;
      return this.http.post(url,idTitolare, options)
      .map(success => success)
      .catch(this.handleError);
    }

  getEsenzioneOperatorePaginato(ricercaesenzioneoperatore: RicercaEsenzioneOperatore): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getEsenzioniOperatorePaginato/`;
       return this.http.post(url,ricercaesenzioneoperatore, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getCertificatoPatologiaPaginato(ricercacertificatopatologia: RicercaCertificatoPatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getCertificatoPatologiaPaginato/`;
       return this.http.post(url,ricercacertificatopatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getEsenzionePatologiaPaginato(ricercacertificatopatologia: RicercaCertificatoPatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getEsenzionePatologiaPaginato/`;
       return this.http.post(url,ricercacertificatopatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getRicercaCertificatoPaginato(ricercacertificatopatologia: RicercaCertificatoPatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getRicercaCertificatoPaginato/`;
       return this.http.post(url,ricercacertificatopatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getValidaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/validaesenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }


  getValidaSelezionate(filter: Array<string>) : Observable <Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

       const url = `${this.baseUrl}/valida-esenzioni-selezionate/`;
       return this.http.post(url, filter, options )
       .map(success => success)
       .catch(this.handleError);
    }

     getProcediValidaSelezionate(filter: Array<string>): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/procedi-valida-esenzioni-selezionate/`;
       return this.http.post(url,filter, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getProcediValidaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/procedivalidaesenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getApprovaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/approva-esenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getRinnovaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/rinnova-esenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getProcediApprovaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/procedi-approva-esenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }

  getProcediRinnovaEsenzione(filtrovalidaesenzionepatologia: FiltroValidaEsenzionePatologia): Observable<Response | any> {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/procedi-rinnova-esenzione/`;
       return this.http.post(url,filtrovalidaesenzionepatologia, options )
       .map(success => success)
       .catch(this.handleError);
  }
  private handleError (error: Response | any) {
      let body = error.json();
      console.log('sever error:', error);
      return Observable.throw(error);
  }

  private extractData(res: any ) {

      let body = res.json();
      if (res.status = 200) {
          if (body.data == "" || body.data == []) {
              (document.getElementById("messaggioricerca").innerHTML) =body.message;
              $(".mesric").show();
              $(".eleeseris").hide();

          } else {
              $(".eleeseris").show();
              $(".mesric").hide();
              (document.getElementById("numesentionitrovate").innerHTML)=body.data.length;

              return body.data;
              }
      }
  }
  private extractDataCittadino(res: any ) {

      let body = res.json();
      if (res.status = 200) {
          if (body.data == "" || body.data == []) {
              (document.getElementById("nontrovoesenzione").innerHTML) =body.message;
             $(".mesric").show();
              $(".eleeseris").hide();
          } else {
              $(".eleeseris").show();
              $(".mesric").hide();

              return body.data;
              }
      }
  }

  getDettaglioCertificato(cit_id: string, certificato_id: string): Observable<Response | any> {

    const url = `${this.baseUrl}/cittadini/`+cit_id+`/certificati/`+certificato_id;
    return this.http.get(url)
              .map(success => {
                this.dettaglioCertificato = success.json() as CertificatoPatologia
                return success; })
              .catch(this.handleError);
  }

  scaricaCertificato(cit_id: string, certificato_id: string) : any{
    const url = `${this.baseUrl}/cittadini/`+cit_id+`/certificati/`+certificato_id+`/pdf`;
    return this.http.get(url, { responseType: ResponseContentType.Blob | ResponseContentType.Json }).map(
          (res) => {
                  let m = new Map<string,any>();
                  let name = res.headers.get("Content-Disposition");
                  name = name.substring(name.lastIndexOf('=') + 1, name.length);
                  m.set("name", name);
                  m.set("file", new Blob([res.blob()], { type: 'application/pdf' }));
                  return m;
                  //return new Blob([res.blob()], { type: 'application/pdf' })
               }).catch(this.handleError);
  }

  createZipAllegati(cit_id: string, esenzione_id: string) : any{
      const url = `${this.baseUrl}/esenzioni/`+cit_id+`/allegati/`+esenzione_id+`/zip`;
      return this.http.get(url, { responseType: ResponseContentType.Blob}).map(
            (res) => {
                    let m = new Map<string,any>();
                    let name = res.headers.get("Content-Disposition");
                    name = name.substring(name.lastIndexOf('=') + 1, name.length);
                    m.set("name", name);
                    m.set("file", new Blob([res.blob()], { type: 'application/zip' }));
                    return m;
                    //return new Blob([res.blob()], { type: 'application/pdf' })
                 })
    }

  getDettaglioEsenzione(filter: FiltriDettaglioEsenzione) : Observable<Response | any> {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    const url = `${this.baseUrl}/cittadini/dettaglio-esenzione`;
    return this.http.post(url, filter, options)
      .map(success => {
        this.dettaglioEsenzione = success.json() as PraticaEsenzione
        return success; })
      .catch(this.handleError);
}


  getCronologia(cit_id: string, esenzione_id: string) : any{
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/esenzioni/`+cit_id+`/`+esenzione_id+`/storico`;

      return this.http.get(url)
      .map(response => response.json() as StoricoEsenzione[]);
    }

  getPratichePaginato(filter: FiltriRicercaPratiche) : Observable <Response | any> {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/getPratichePaginato/`;
     return this.http.post(url, filter, options )
     .map(success => success)
     .catch(this.handleError);
  }


  scaricaAttestatoLegale(cit_id: string) : any{
    const url = `${this.baseUrl}/esenzioni/`+cit_id+`/pdf`;
    return this.http.get(url, { responseType: ResponseContentType.Blob}).map(
      (res) => {
        let m = new Map<string,any>();
        let name = res.headers.get("Content-Disposition");
        name = name.substring(name.lastIndexOf('=') + 1, name.length);
        m.set("name", name);
        m.set("file", new Blob([res.blob()], { type: 'application/pdf' }));
        return m;
        //return new Blob([res.blob()], { type: 'application/pdf' })
      })
  }

  getMotivazioni(stato: string) : Observable<Response | any> {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });

     const url = `${this.baseUrl}/motivazioni/` + stato;
     return this.http.get(url, options)
     .map(success => success)
     .catch(this.handleError);
  }

  setAnnullamentoEsenzione(filtri: MotivazioneAnnRevRes, esenzione_id: string, cit_id: string) {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    const url = `${this.baseUrl}/cittadini/`+cit_id+`/esenzioni/`+esenzione_id+`/annullamento`;
    return this.http.put(url, filtri, options)
      .map(success => success)
      .catch(this.handleError);
  }

  setRespingiEsenzione(filtri: MotivazioneAnnRevRes, esenzione_id: string, cit_id: string) {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/`+cit_id+`/esenzione/`+esenzione_id+`/respingi-esenzione`;
      return this.http.post(url, filtri, options)
        .map(success => success)
        .catch(this.handleError);
    }

  setProcediRichiestaRettifica(filtri: RichiestaRettificaDati) {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/procedi-richiesta-rettifica-dati`;
      return this.http.post(url, filtri, options)
        .map(success => success)
        .catch(this.handleError);
    }

  setRichiestaRettifica(filtri: RichiestaRettificaDati) {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/richiesta-rettifica-dati`;
      return this.http.post(url, filtri, options)
        .map(success => success)
        .catch(this.handleError);
    }

  setRevocaEsenzione(filtri: MotivazioneAnnRevRes, esenzione_id: string, cit_id: string) {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    console.log(filtri);
    const url = `${this.baseUrl}/cittadini/`+cit_id+`/esenzioni/`+esenzione_id+`/revoca`;
    return this.http.put(url, filtri, options)
      .map(success => success)
      .catch(this.handleError);
  }

  scaricaAttestato(cit_id: string, esenzione_id: string, cod_esen: string, data_val: string) : any{
    const url = `${this.baseUrl}/esenzioni/`+cit_id+`/`+esenzione_id+`/pdf`;
    return this.http.get(url, { params: {
      document_type: '07',
      cod_esenzione: cod_esen,
      data_validita: data_val
    },responseType: ResponseContentType.Blob}).map(
      (res) => {
        let m = new Map<string,any>();
        let name = res.headers.get("Content-Disposition");
        name = name.substring(name.lastIndexOf('=') + 1, name.length);
        m.set("name", name);
        m.set("file", new Blob([res.blob()], { type: 'application/pdf' }));
        return m;
        //return new Blob([res.blob()], { type: 'application/pdf' })
      })
  }

  setRettificaEsenzione() {

  }


  salvaInBozza(filter: FiltroValidaEsenzionePatologia) {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    const url = `${this.baseUrl}/salva-in-bozza/`;
    return this.http.post(url, filter, options)
      .map(success => success)
      .catch(this.handleError);
  }

  procediSalvaInBozza(filter: FiltroValidaEsenzionePatologia) {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
    let options = new RequestOptions({ headers: cpHeaders });
    const url = `${this.baseUrl}/procedi-salva-in-bozza`;
    return this.http.post(url, filter, options)
      .map(success => success)
      .catch(this.handleError);
  }

  rettificaEsenzione(filtri: RichiestaRettificaDati) {
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
      let options = new RequestOptions({ headers: cpHeaders });
      const url = `${this.baseUrl}/rettifica-esenzione`;
      return this.http.post(url, filtri, options)
      .map(success => success)
      .catch(this.handleError);

  }

}
