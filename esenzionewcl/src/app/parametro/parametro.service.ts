import { Injectable } from '@angular/core';
import { Http, RequestOptions,Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { parametro } from './parametro';
import { Messaggio } from './messaggio';
import { Notifica } from './notifica';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
import { ConfigService } from "../services/config.service";

import * as $ from 'jquery';

@Injectable()
export class parametroStoreService {

    baseUrl = this.config.getBEServerContext();


  constructor(private http: Http,private config: ConfigService
         ) {}


  getParametro(id: string): Observable<parametro[]> {

       const url = `${this.baseUrl}/getParametro/`+id;

      return this.http.get(url)
                .map(response => response.json().data as parametro[]);


  }
  getListaParametri(): Observable<parametro[]> {
      const url = `${this.baseUrl}/getParametri/`;
     return this.http.get(url)
     .map(response => response.json().data as parametro[]);
  }


  public getMessaggio(id: string): Observable<Messaggio[]> {

      const url = `${this.baseUrl}/getMessaggio/`+id;
     return this.http.get(url)
               .map(response => response.json().data as Messaggio[]);
 }

 getNotificaFiltro(id: string): Observable<Notifica[]> {

      const url = `${this.baseUrl}/getNotificheCittadino/`+id;
     return this.http.get(url)
               .map(response => response.json().data as Notifica[]);
 }

 UpdNotifica(id: number): Observable<Response | any> {

     const url = `${this.baseUrl}/aggiornaNotifica/`+id;
    return this.http.put(url,id)
    .map(success => success)
    .catch(this.handleError);
}

 getNumeroNuoveNotificheCittadino(id: string): Observable<any> {

     const url = `${this.baseUrl}/getNumeroNuoveNotificheCittadino/`+id;
    return this.http.get(url)

             .map(this.extractNotificanum)
}

 private extractNotificanum(res: any ) {

     let body = res.json();
     if (body.data[0]==0 && body.data[1]==0) {
         document.getElementById("nascico").style.visibility="hidden"
              document.getElementById( "divnotifica" ).style.visibility = "hidden"
             document.getElementById( "mesnotifica" ).innerHTML="Nessuna notifica ricevuta";
             document.getElementById( "mesnotifica" ).style.visibility = "visible"

     }
     else if (body.data[0]==0 && body.data[1]!=0){
         document.getElementById("nascico").style.visibility="hidden"
             document.getElementById( "divnotifica" ).style.visibility = "visible"
             document.getElementById( "mesnotifica" ).style.visibility = "hidden"
             document.getElementById( "precnot" ).style.display = "inherit"
             document.getElementById( "nuovanot" ).style.display = "none"

     }
     else if (body.data[0]==body.data[1]){
         document.getElementById("nascico").style.visibility="visible";
         (document.getElementById("conta").innerHTML) =body.data[0];
             document.getElementById( "divnotifica" ).style.visibility = "visible"
             document.getElementById( "mesnotifica" ).style.visibility = "hidden"
             document.getElementById( "precnot" ).style.display = "none"
             document.getElementById( "nuovanot" ).style.display = "inherit"

     }
     else {
         document.getElementById("nascico").style.visibility="visible";
        (document.getElementById("conta").innerHTML) =body.data[0];
        document.getElementById( "precnot" ).style.display = "inherit"
        document.getElementById( "nuovanot" ).style.display = "inherit"


     }
             return body.data;

     }


 getNotificaOperatore(id: string): Observable<Notifica[]> {

     const url = `${this.baseUrl}/getNotificheOperatore/`+id;
    return this.http.get(url)
              .map(response => response.json().data as Notifica[]);
}

 getNumeroNuoveNotificheOperatore(id: string): Observable<any> {

     const url = `${this.baseUrl}/getNumeroNuoveNotificheOperatore/`+id;
    return this.http.get(url)

        .map(this.extractNotificanum);
}

 EliminaNotifica(id: number): Observable<Response | any> {

     let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
     let options = new RequestOptions({ headers: cpHeaders });
     let CancellaNotificheUrl = `${this.baseUrl}/cancellaNotifica/`;
     return this.http.delete(CancellaNotificheUrl+id, options)
            .map(success => success)
            .catch(this.handleError);
 }

  private handleError(error: Response | any) {

      console.error(error.message || error);
      return Observable.throw(error);
  }
}


