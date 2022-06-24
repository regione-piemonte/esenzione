import { Component, ɵConsole, ViewChild, ElementRef, SystemJsNgModuleLoader, OnInit } from "@angular/core";
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import * as $ from 'jquery';
import { Http } from "@angular/http";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { Assistito } from "app/assistito/assistito";
import { UserInfo } from "app/assistito/userinfo";
import { FiltriRicercaAssistito } from "app/assistito/FiltriRicercaAssistito";
import { NgForm } from "@angular/forms";
import { esenzioneAssistito } from "app/esenzione/esenzioneAssistito";
import { ricercaAssistitoResponse } from "./ricercaAssistito.response";
import { certificato } from "app/esenzione/certificato";
import { ActivatedRoute, Router } from "@angular/router";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-ricerca-assistito',
    templateUrl: './ricercaAssistito.component.html',
    styleUrls: ['./ricercaAssistito.component.css']
})
export class ricercaAssistito implements OnInit  {

    public isOn : boolean = false;
    private scope : string = '';
    public loading = false;
    @ViewChild('codfiscben') codfiscben: ElementRef;
    @ViewChild('cognomeben') cognomeben: ElementRef;
    @ViewChild('nomeben') nomeben: ElementRef;
    statusCode: number;

    listaBeneficiari : Assistito[] = [];
    listaEsenzioni : esenzioneAssistito[] = [];

    ricerca : ricercaAssistitoResponse = new ricercaAssistitoResponse();
    assistitoBen : string;
    numBeneficiari : number;
    selectedBeneficiario: number;
    submitted = false;
    hideError = true;
    errorMessage: string;

    tipoRicerca: string;
    return: boolean;

    constructor( http: Http,

        private assistitoStoreService: AssistitoStoreService,

        private router: Router,
        private route: ActivatedRoute,

    ) {

    }

    ngOnInit() {
        this.route.params.subscribe( params => {
            this.tipoRicerca = params['tipoRicerca'];
        } );

        this.route.queryParams.subscribe( queryParam => {
            this.return = queryParam['back'];
        })

        if(this.return) {
            this.submitted = true;
            this.ricerca = new ricercaAssistitoResponse();
            this.ricerca.assistito = this.assistitoStoreService.getAssistito();
            this.ricerca.lista_certificati = this.assistitoStoreService.getListaCertificati();
            this.ricerca.lista_esenzioni = this.assistitoStoreService.getListaEsenzioni();
            this.listaEsenzioni = this.ricerca.lista_esenzioni.filter(esen => esen.visualizza);
            this.assistitoBen = this.ricerca.assistito.cognome + " " + this.ricerca.assistito.nome;
            this.numBeneficiari = 1;
        }

        if(this.tipoRicerca == 'cert')
            this.scope = "Nuovo Certificato: ";
        else if(this.tipoRicerca == 'esen')
            this.scope = "Nuova Esenzione: ";
    }

    onSubmit() {
        this.submitted = true;
    }


    checkDisabilitato() {
        return (this.cognomeben.nativeElement.value !== null ||
                this.codfiscben.nativeElement.value !== null);
    }


    cercaAssistito() {
        this.loading = true;
        this.ricerca = new ricercaAssistitoResponse();

        this.assistitoBen = "";
        this.hideError = true;
        this.errorMessage = "";
        this.numBeneficiari = 0;

        this.selectedBeneficiario = null;

        let assistito: UserInfo = new UserInfo();
        assistito.nome =this.nomeben.nativeElement.value.toUpperCase();
        assistito.cognome = this.cognomeben.nativeElement.value.toUpperCase();
        assistito.codFisc = this.codfiscben.nativeElement.value.toUpperCase();
        let filtri = new FiltriRicercaAssistito();
        filtri.assistito = assistito;

        this.assistitoStoreService.ricercaAssistito( filtri )
        .subscribe(

            data => {
                this.statusCode = data.status;
                let body = data.json();
                if ( this.statusCode == 200 ) {
                    this.ricerca = body as ricercaAssistitoResponse;

                    if ( this.ricerca.hasOwnProperty("elenco_assistiti") ) {
                        this.assistitoBen = assistito.cognome + " " + assistito.nome;
                        this.numBeneficiari = this.ricerca.elenco_assistiti.length;
                        this.loading = false;
                    } else {
                        this.assistitoBen = this.ricerca.assistito.cognome + " " + this.ricerca.assistito.nome;
                        this.numBeneficiari = 1;
                        this.listaEsenzioni = this.ricerca.lista_esenzioni.filter(esen => esen.visualizza);

                        this.loading = false;
                    }
                }

            },
            errorCode => {
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                this.statusCode = errorCode.status;
                this.hideError = false;
                this.errorMessage = objmsg.message as string;
                this.loading = false;
             }
        );
    }

    resetCercaBeneficiario() {
        this.ricerca = new ricercaAssistitoResponse();
        this.assistitoBen = "";
        this.hideError = true;
        this.errorMessage = "";
        this.numBeneficiari = 0;
        this.nomeben.nativeElement.value = "";
        this.cognomeben.nativeElement.value = "";
        this.codfiscben.nativeElement.value = "";
        this.submitted = false;
        this.selectedBeneficiario = null;
    }

    selectBeneficiario(i:number) {
        this.selectedBeneficiario = i;
    }


    getRicercaAssistitoIsOn() {
        return this.isOn;
    }

    setRicercaAssistitoIsOn() {
        this.isOn = !this.isOn;
    }

    confermaBeneficiarioSelezionato() {
        this.nomeben.nativeElement.value = this.ricerca.elenco_assistiti[this.selectedBeneficiario].nome;
        this.cognomeben.nativeElement.value = this.ricerca.elenco_assistiti[this.selectedBeneficiario].cognome;
        this.codfiscben.nativeElement.value = this.ricerca.elenco_assistiti[this.selectedBeneficiario].codFiscale;
        this.submitted = true;
        this.cercaAssistito();

    }

    confermaAssistito(){
        this.assistitoStoreService.setAssistito(this.ricerca.assistito);
        this.assistitoStoreService.setListaCertificati(this.ricerca.lista_certificati);
        this.assistitoStoreService.setListaEsenzioni(this.ricerca.lista_esenzioni);
        if(this.tipoRicerca == 'cert')
            this.router.navigate( ["nuovo-certificato"], {relativeTo: this.route.parent,skipLocationChange: true} );
        else if(this.tipoRicerca == 'esen')
            this.router.navigate( ["nuova-esenzione"], {relativeTo: this.route.parent,skipLocationChange: true} );
    }

    returnHome() {
        this.router.navigate( ["../../home"], {relativeTo: this.route, skipLocationChange: true} );
    }
}
