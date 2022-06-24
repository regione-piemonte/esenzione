import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from "@angular/router";
//import { environment } from "../../environments/environment"
import { ConfigService } from "../services/config.service";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
    template: `
    <div id="logError" hidden><div class="row"></div>
    <div class="row" id="utnonaut" style="visibility:hidden">
    <div class="col-md-2"></div>
     <div class="col-md-8" style="padding-top:30px;padding-bottom:30px;">
    <div class="row">
    <h2>Utente Non Autorizzato</h2>
    </div>
                <div class="row">
               <label id="messaggiononaccesso"></label>
               </div>
                <div class="row">
                  <button (click)="goBack()">Esci</button>
                   </div>
                   </div>
         <div class="col-md-2"></div>
           </div>
           <div class="row"></div>
            </div> `
  })
export class PageNotFoundComponent {
    constructor(private location: Location, private router: Router
            ,private config: ConfigService
            ) { }
    goBack(): void {
        this.router.navigate(["/"]).then(result=>{window.location.href = this.config.getOnAppExitURL();});
        //this.router.navigate(["/"]).then(result=>{window.location.href = environment.onAppExitURL});
        }
}
