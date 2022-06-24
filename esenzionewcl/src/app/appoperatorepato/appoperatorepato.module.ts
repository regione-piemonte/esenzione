import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http} from '@angular/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AppOperatorepatoComponent } from './appoperatorepato.component';
import { parametroStoreService } from '../parametro/parametro.service';
import { ricercaAssistito } from './ricercaAssistito/ricercaAssistito.component';
import { DettaglioCertificato } from './dettaglioCertificato/DettaglioCertificato.component';
import { DettaglioEsenzione } from './dettaglioEsenzione/DettaglioEsenzione.component';
import { CookieService } from 'ngx-cookie-service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@NgModule({
  declarations: [
      ricercaAssistito,
      DettaglioCertificato,
      DettaglioEsenzione
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CookieService,
    ReactiveFormsModule
  ],
  providers: [parametroStoreService,
              ],
  bootstrap: [AppOperatorepatoComponent]
})
export class AppOperatorepatoModule {

}
