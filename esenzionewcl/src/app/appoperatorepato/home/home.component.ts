import { Component, OnInit, ViewChild } from '@angular/core';
import { HomeAppOperatorePatoEsen } from "app/appoperatorepato/home-esen/home-esen.component";
import { HomeAppOperatorePatoCert } from "app/appoperatorepato/home-cert/home-cert.component";
import {Subject} from 'rxjs/Subject';
import { TabService } from "app/appoperatorepato/home/tab.service";
import { UserInfo } from "app/assistito/userinfo";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { statodocumentoStoreService } from "app/statodocumento/statodocumento.service";
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { parametroStoreService } from "app/parametro/parametro.service";
import { statodocumento } from "app/statodocumento/statodocumento";
import { parametro } from "app/parametro/parametro";
import { CodiceEsenzionePatologia } from "app/esenzione/codiceesenzionepatologia";
import { Router, ActivatedRoute } from '@angular/router';
declare var $;
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-operatorepato-home',
    templateUrl: './home.component.html',
} )


export class HomeAppOperatorePato implements OnInit {


    tabActive: string = '';
    changingEsenTab: Subject<string> = new Subject();
    changingCertTab: Subject<string> = new Subject();
    loading = false;
    utenteMedicoCas: boolean = false;
    utenteOperatoreAslDistretto: boolean = false;
    utenteMedicoSpecialistaAslDistretto: boolean = false;
    utenteAmministratoreCsi: boolean = false;
    listaStatoDocumento: statodocumento[] = [];

    parametroTmp: parametro[] = [];


    listacodiceesenzionepatologia: CodiceEsenzionePatologia[] = [];
    return: boolean = false;


    constructor(private assistitoStoreService: AssistitoStoreService,
                private tabService: TabService,
                private statodocumentoStoreService: statodocumentoStoreService,
                private esenzioneStoreService: EsenzioneStoreService,
                private parametroStoreService: parametroStoreService,
                private router: Router,
                private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.queryParams.subscribe( queryParam => {
            this.return = queryParam['back'];
        })

        let utente: UserInfo = this.assistitoStoreService.getUser();

        utente.ruoli.forEach(ruolo => {
            if(ruolo.codiceRuolo === 'MEDICO_SPECIALISTA_CAS') {
                this.utenteMedicoCas = true;
            }
            if(ruolo.codiceRuolo === 'OPERATORE_ASL_DISTRETTO') {
                this.utenteOperatoreAslDistretto = true;
            }
            if(ruolo.codiceRuolo === 'MEDICO_SPECIALISTA_ASL_DISTRETTO') {
                this.utenteMedicoSpecialistaAslDistretto = true;
            }
            if(ruolo.codiceRuolo === 'AMMINISTRATORE_CSI') {
                this.utenteAmministratoreCsi = true;
            }
        });

        if(this.return) {
            this.tabService.setReturnFrom(true);
            this.tabActive = this.tabService.getCurrentTab();
            this.getParam( "ELEMENTI_RICERCA" );
        } else {

        if(this.utenteOperatoreAslDistretto) //OPERATORE_ASL_DISTRETTO
            this.tabActive = "daFare";
        else if(this.utenteMedicoCas) //MEDICO_SPECIALISTA_CAS
            this.tabActive="certificati";
        else //MEDICO_SPECIALISTA_ASL_DISTRETTO O AMMINISTRATORE_CSI
            this.tabActive="ricercarichiestacertificati";

        this.tabService.setCurrentTab(this.tabActive);
        this.loading = true;
        this.statodocumentoStoreService.getStatoDocumento1();
        this.statodocumentoStoreService.getStatoPratica1();
        this.esenzioneStoreService.getCodiciEsenzioniPatologiaFiltro();
        this.getParam( "ELEMENTI_RICERCA" );
        }
    }

    getParam( id: string ) {
        this.parametroStoreService.getParametro( id )
            .subscribe( parametro => {this.parametroTmp = parametro
                    this.loading = false;
            }
                    );

    }


    changeTab(form){
       if (form=="#ricercarichiestacertificati"){
           this.tabActive="ricercarichiestacertificati";
           this.tabService.setCurrentTab("ricercarichiestacertificati");
           this.changingCertTab.next(form);
       }else if (form=="#certificati"){
           this.tabActive="certificati";
           this.tabService.setCurrentTab("certificati");
           this.changingCertTab.next(form);
       }else if (form=="#esenzioni"){
           this.tabActive="esenzioni";
           this.tabService.setCurrentTab("esenzioni");
           this.changingCertTab.next(form);
       } else if (form == "#daFare"){
           this.tabActive="daFare";
           this.tabService.setCurrentTab("daFare");
           this.changingEsenTab.next(form);
       } else if (form == "#tutteLePratiche"){
           this.tabActive="tutteLePratiche";
           this.tabService.setCurrentTab("tutteLePratiche");
           this.changingEsenTab.next(form);
       }
    }

    ChiudiFiltroOp() {
        $( ".divFiltri" ).hide();
        $( ".aprifiltroOp" ).show();
        $( ".chiudifiltroOp" ).hide();

    }

    ApriFiltroOp() {
        $( ".divFiltri" ).show();
        $( ".aprifiltroOp" ).hide();
        $( ".chiudifiltroOp" ).show();

    }

    navigateToRicerca() {
        let tab:string = "";
        if(this.tabActive=="certificati")
            tab = "cert";
        else if(this.tabActive=="daFare")
            tab = "esen";
        if(tab != "")
            this.router.navigate( ["../ricerca-assistito", tab], {relativeTo: this.route,skipLocationChange: true} );
    }
}
