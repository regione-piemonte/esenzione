
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpModule } from "@angular/http";
import { DettaglioEsenzione } from "./DettaglioEsenzione.component";
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
    bootstrap: [DettaglioEsenzione]
  })
  export class DettaglioEsenzioneModule {

  }
