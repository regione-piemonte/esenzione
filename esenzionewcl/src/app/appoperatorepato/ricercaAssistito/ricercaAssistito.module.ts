
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { parametroStoreService } from "app/parametro/parametro.service";
import { ricercaAssistito } from "./ricercaAssistito.component";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@NgModule({
    declarations: [

    ],
    imports: [
      BrowserModule,
      FormsModule,
      HttpModule,
      ReactiveFormsModule
    ],
    providers: [],
    bootstrap: [ricercaAssistito]
  })
  export class RicercaAssistitoModule {

  }
