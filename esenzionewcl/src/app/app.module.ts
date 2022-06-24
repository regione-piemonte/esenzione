import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, HttpModule} from '@angular/http';
import { SelectDropDownModule } from 'assets/custom-modules/ngx-select-dropdown'
import {NgPipesModule} from 'ngx-pipes';
//import { AppCittadinoComponent }  from './appcittadino/appcittadino.component';
import { AppComponent }  from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AutocertificazioneStoreService } from './autocertificazione/autocertificazione.service';
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { parametroStoreService } from "app/parametro/parametro.service";
import { TitoloDichStoreService } from "app/titolodich/titolodich.service";
import { datarichStoreService } from "app/datarich/datarich.service";
import { AppOperatoreComponent } from "app/appoperatore/appoperatore.component";
import { AppOperatorepatoComponent } from "app/appoperatorepato/appoperatorepato.component";
//import { Ng2OrderModule } from 'ng2-order-pipe'; //importing the module
import { OrderModule } from 'ngx-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination'; // <-- import the module
import 'rxjs/operator/filter'
import { ConfigService } from "./services/config.service";
import { CookieService } from "ngx-cookie-service";
import { EsenzioneUniquePipe } from "app/esenzione/esenzioneunique.pipe";
import { PageNotFoundComponent } from "app/assistito/page-not-found.component";
import { LoadingModule } from 'ngx-loading';
import { CapitalizePipe } from "app/assistito/capitalize.pipe";
import { ricercaAssistito } from './appoperatorepato/ricercaAssistito/ricercaAssistito.component';
import { statodocumentoStoreService } from "app/statodocumento/statodocumento.service";
import { DettaglioCertificato } from './appoperatorepato/dettaglioCertificato/DettaglioCertificato.component';
import { CertificatoWizardComponent } from "app/certificato-wizard/certificato-wizard.component";
import { NavbarComponent } from "app/certificato-wizard/navbar/navbar.component";
import { GruppoEsenzioneComponent } from "app/certificato-wizard/gruppo-esenzione/gruppo-esenzione.component";
import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { TipologiaCertComponent } from './certificato-wizard/tipologia-cert/tipologia-cert.component';
import { RiepilogoCertComponent } from './certificato-wizard/riepilogo-cert/riepilogo-cert.component';
import { InitCertFields } from './certificato-wizard/initalizeCertFields.service';
import { HomeAppOperatorePatoCert } from "app/appoperatorepato/home-cert/home-cert.component";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { CertificatoCreatoComponent } from "app/certificato-wizard/certificato-creato/certificato-creato";
import { EsenzioneWizardComponent } from "app/esenzione-wizard/esenzione-wizard.component";
import { HomeAppOperatorePatoEsen } from "app/appoperatorepato/home-esen/home-esen.component";
import { EsenNavbarComponent } from "app/esenzione-wizard/navbar/navbar.component";
import { DocumentiEsenComponent } from "app/esenzione-wizard/documenti-esen/documenti-esen.component";
import { DocumentiRinnovaEsenComponent } from "app/esenzione-wizard/documenti-esen/documenti-rinnova-esen.component";
import { GruppoEsenComponent } from "app/esenzione-wizard/gruppo-esenzione/gruppo-esenzione.component";
import { RiepilogoEsenComponent } from "app/esenzione-wizard/riepilogo-esen/riepilogo-esen.component";
import { TipologiaEsenComponent } from "app/esenzione-wizard/tipologia-esen/tipologia-esen.component";
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { InitEsenFields } from "app/esenzione-wizard/initalizeEsenFields.service";
import { EsenErrorHandler } from "app/esenzione-wizard/esenErrorHandler";
import { DettaglioEsenzione } from './appoperatorepato/dettaglioEsenzione/DettaglioEsenzione.component';
import { EsenzioneCreataComponent } from "app/esenzione-wizard/esenzione-creata/esenzione-creata";
import { EsenzioneCreataValidataComponent } from "app/esenzione-wizard/esenzione-creata-validata/esenzione-creata-validata";
import { HomeAppOperatorePato } from "app/appoperatorepato/home/home.component";
import { TabService } from "app/appoperatorepato/home/tab.service";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
const routes: Routes =
    [
        {path: '',  component: PageNotFoundComponent},
        {path: 'appoperatore/:codFisc', component: AppOperatoreComponent},
        {path: 'appoperatore/', component: AppOperatoreComponent},
        {path: 'appoperatorepato/', component: AppOperatorepatoComponent},
        {path: 'appoperatorepato/:codFisc', component: AppOperatorepatoComponent, children:
            [
             {path: '',   redirectTo: 'home', pathMatch: 'full'},
             {path: 'home',   component: HomeAppOperatorePato},
             {path: 'home-cert', component: HomeAppOperatorePatoCert},
             {path: 'nuovo-certificato', component: CertificatoWizardComponent},
             {path: 'ricerca-assistito/:tipoRicerca', component: ricercaAssistito},
             {path: 'home-esen', component: HomeAppOperatorePatoEsen},
             {path: 'nuova-esenzione', component: EsenzioneWizardComponent},
             {path: 'dettaglio-certificato', component: DettaglioCertificato},
             {path: 'dettaglio-esenzione', component: DettaglioEsenzione}
             ]

        }
    ];

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpModule,

        OrderModule,
        NgxPaginationModule,
        LoadingModule,
        SelectDropDownModule,
        NgPipesModule,
        RouterModule.forRoot(routes, { enableTracing: false, useHash: false })
    ],
    declarations: [
        AppComponent,AppOperatoreComponent,AppOperatorepatoComponent,PageNotFoundComponent,
        EsenzioneUniquePipe,CapitalizePipe,
		ricercaAssistito, DettaglioCertificato, DettaglioEsenzione,
        CertificatoWizardComponent, NavbarComponent, GruppoEsenzioneComponent, TipologiaCertComponent,
        RiepilogoCertComponent, HomeAppOperatorePatoCert, CertificatoCreatoComponent,
        HomeAppOperatorePatoEsen, EsenNavbarComponent, EsenzioneWizardComponent,
        DocumentiEsenComponent,DocumentiRinnovaEsenComponent, GruppoEsenComponent, RiepilogoEsenComponent, TipologiaEsenComponent, EsenzioneCreataComponent,
        EsenzioneCreataValidataComponent,
        HomeAppOperatorePato
     ],
        providers: [EsenzioneStoreService, AssistitoStoreService , parametroStoreService,TitoloDichStoreService,datarichStoreService,
                    AutocertificazioneStoreService,CookieService,ConfigService,
                    FormDataService, WorkflowService, InitCertFields, statodocumentoStoreService, CertErrorHandler,
                    EsenFormDataService, EsenWorkflowService, InitEsenFields, EsenErrorHandler, TabService
                    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
