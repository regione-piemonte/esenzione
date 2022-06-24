
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpModule } from "@angular/http";
import { DettaglioCertificato } from "./DettaglioCertificato.component";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@NgModule({
    declarations: [

    ],
    imports: [
      BrowserModule,
      HttpModule
    ],
    providers: [],
    bootstrap: [DettaglioCertificato]
  })
  export class DettaglioCertificatoModule {

  }
