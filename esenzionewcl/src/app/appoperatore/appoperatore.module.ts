import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http} from '@angular/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AppOperatoreComponent } from './appoperatore.component';
import { EsenzioneStoreService } from '../esenzione/esenzione.service';
import { AssistitoStoreService } from '../assistito/assistito.service';
import { parametroStoreService } from '../parametro/parametro.service';
import { TitoloDichStoreService } from '../titolodich/titolodich.service';
import { datarichStoreService } from '../datarich/datarich.service';
import { AutocertificazioneStoreService } from '../autocertificazione/autocertificazione.service';
import { Assistito } from "../assistito/assistito";

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@NgModule({
  declarations: [
  //  AppOperatoreComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: [EsenzioneStoreService, AssistitoStoreService , parametroStoreService,TitoloDichStoreService,datarichStoreService,AutocertificazioneStoreService
              ],
  bootstrap: [AppOperatoreComponent]
})
export class AppOperatoreModule {

}
