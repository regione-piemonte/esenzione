import { Component, Input, OnInit, Output, Directive, ElementRef, ViewChild } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { EsenzioneStoreService } from '../esenzione/esenzione.service';
import { Esenzione } from '../esenzione/esenzione';
import { EsenzioneCittadino } from '../esenzione/esenzionecittadino';
import { RicercaEsenzioneCittadino } from '../esenzione/ricercaesenzionecittadino';
import { RicercaEsenzioneOperatore } from '../esenzione/ricercaesenzioneoperatore';
import { AssistitoStoreService } from '../assistito/assistito.service';
import { Assistito } from '../assistito/assistito';
import { parametroStoreService } from '../parametro/parametro.service';
import { parametro } from '../parametro/parametro';
import { TitoloDichStoreService } from '../titolodich/titolodich.service';
import { TitoloDich } from '../titolodich/titolodich';
import { datarichStoreService } from '../datarich/datarich.service';
import { datarich } from '../datarich/datarich';
import { FormControl, FormGroup, Validators, AbstractControl, FormBuilder } from '@angular/forms';
import { AutocertificazioneStoreService } from '../autocertificazione/autocertificazione.service';
import { Autocertificazione } from '../autocertificazione/autocertificazione';
import { RevocaEsenzione } from '../autocertificazione/revocaesenzione';
import { Messaggio } from '../parametro/messaggio';
import { Notifica } from '../parametro/notifica';
import { AutocertificazioneFam } from '../autocertificazione/autocertificazionefam';
import { AutocertificazioneASL } from '../autocertificazione/autocertificazioneasl';
import { Router, Params, RouterModule, ActivatedRoute } from '@angular/router';
import { NgIf, NgSwitch } from '@angular/common';

import { CookieService } from 'ngx-cookie-service';//questo
import { EventEmitter } from "events";
import { UserInfo } from "app/assistito/userinfo";
import * as $ from 'jquery';
import { EsenzioneOperatore } from "app/esenzione/esenzioneoperatore";
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import { EsenzioneAsl } from "app/esenzione/esenzioneAsl";
import { ConfigService } from "../services/config.service";
import { RevocaEsenzioneCertificata } from "app/autocertificazione/revocaesenzionecertificata";
declare var $;

@Component( {
    selector: 'appoperatore',
    templateUrl: './appoperatore.component.html',


} )
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

export class AppOperatoreComponent implements OnInit {
    idSblocco: string;
    messaggioTitolo: string;
    EsenzioneCodice: string;
    EsenzioneName: string;
    EsenzioneCodiceFam: string;
    EsenzioneNameFam: string;
    EsenzioneDescFam: string;
    listaEsenzioni: Esenzione[] = [];
    esenzione: Esenzione[] = [];
    esenzioneasl: EsenzioneAsl[] = [];
    listaTitoloDich: TitoloDich[] = [];
    datarich: datarich[];
    listaParametri: parametro[] = [];
    parametro: parametro[] = [];
    parametroTmp: parametro[] = [];
    listaAssistiti: Assistito[] = [];
      assistito: Assistito[] = [];
    listaEsenzioneCittadino: EsenzioneCittadino[] = [];
    listaEsenzioneOperatore: EsenzioneOperatore[] = [];
    listaEsenzioneOperatoreRevoca: EsenzioneOperatore[] = [];
    esenzionecittadino: EsenzioneCittadino[] = [];
    messaggio: string;
    messaggioRevoca: string;
    notifica: Notifica[] = [];
    numnot: number;
    statusCode: number;
    requestProcessing = false;
    processValidation = false;
    userinfo: UserInfo;
    codFisc: string;
    public datiben: string;
      contanotifica: any[];
    public loading = false;
    public dichiaranteFR = false
    public titolareFR = false
    public confermarev = false;
    public pagina: number = 1;
    private numerototpagine: Array<number>;
    public orderBy: string = "beneficiario";
    public asc: boolean = true;
    private lunghezzaarray: number;
    private isDisable : boolean = false;

    registraEsenzioneFormFam = new FormGroup( {

    } );





    constructor( http: Http,
        private autocertificazioneStoreService: AutocertificazioneStoreService,
        private esenzioneStoreService: EsenzioneStoreService,
        private assistitoStoreService: AssistitoStoreService,
        private parametroStoreService: parametroStoreService,
        private titolodichStoreService: TitoloDichStoreService,
        private datarichStoreService: datarichStoreService,
        private router: Router,
        private route: ActivatedRoute,
        private config: ConfigService,
        private cookieService: CookieService
    ) {
    }


    key: string = 'name';
    reverse: boolean = true;
    sort1( key ) {

        this.key = key;
        this.reverse = !this.reverse;
    }

    sort( key ) {
        this.pagina = 1;
        this.key = key;
        this.asc = this.reverse;
        this.reverse = !this.reverse;
        this.orderBy = key;
        this.ApplicaFiltroOperatore();
    }
    p: number = 1;


    escilogout() {
        this.assistitoStoreService.getLogout()
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                if ( this.statusCode == 204 ) {
                    localStorage.clear();
                    localStorage.removeItem( "cognome" );
                    localStorage.removeItem( "nome" );
                    localStorage.removeItem( "codFisc" );
                    localStorage.removeItem( "idAura" );
                    localStorage.removeItem( "codASL" );
                    sessionStorage.clear();
                    this.cookieService.deleteAll();
                        this.router.navigate(["/"]).then(result=>{window.location.replace(this.config.getOnAppExitURLOperatore())});

                }
            } );
    }




    selectchangefam( args ) {
        this.EsenzioneCodiceFam = args.target.value;
        this.getDataRich(this.EsenzioneCodiceFam);
        this.isDisable = this.listaEsenzioni[args.target.selectedIndex].dataModificabile;
        (document.getElementById( "flagconferma" ).innerHTML) = "1";
        this.EsenzioneNameFam = this.listaEsenzioni[args.target.selectedIndex].descMotivo;
        this.EsenzioneDescFam = this.listaEsenzioni[args.target.selectedIndex].descEsenzione;
        ( document.getElementById( "descesefam" ).innerHTML ) = this.listaEsenzioni[args.target.selectedIndex].descMotivo;
        $( "#iniVal" ).show();
        $( "#dataRic" ).show();
        $( "#fineVal" ).show();

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

    ngOnInit() {
        this.loading = true;
        this.route.params.subscribe( params => { this.codFisc = params['codFisc']; } )


        $( ".notifica" ).hide();
        $( ".insnuovaese" ).hide();
        $( ".eleeseris" ).hide();
        $( ".mesric" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".eleese" ).show();
        $( ".esito" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".revese" ).hide();
        $( ".visese" ).hide();
        $( ".viseserev" ).hide();
        $( "#dichPanelShow" ).hide();
        $( ".trova" ).hide();
        $( ".sitrova" ).hide();
        $( ".NoTitolare" ).hide();


        this.getInfoAssistito( this.codFisc );

        this.selezionastatofiltro( "STATO_ESENZIONE_" );
        this.getParam( "ELEMENTI_RICERCA" );

        this.getDatiEsenzioni();
        this.getTitoloDich();

        this.getEsenzioniAsl();
        this.getListaParametri();
        this.messaggioRevoca = " ";
        this.getMessaggioRevoca("MSG032");
        this.getMessaggioRevoca("MSG031");
        this.getNumeroNuoveNotificheOperatore( this.codFisc );
    }

    getNumeroNuoveNotificheOperatore( codFiscale: string ) {

        this.parametroStoreService.getNumeroNuoveNotificheOperatore( codFiscale )
            .subscribe( conta => {
                this.contanotifica = conta

            } );
    }


    ApriInfo() {
        $( ".eleese" ).hide();
        $( ".eleeseris" ).hide();
        $( ".noese" ).hide();
        $( ".siese" ).hide();
        $( ".sitrova" ).show();
        $( ".trova" ).show();
        $( ".esito" ).hide();
        $( ".esitoko" ).hide();
        $( ".sitrova" ).hide();
        $( ".esitook" ).hide();
        $( ".siese" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".insnuovaese" ).hide();
        $( ".revese" ).hide();
        $( ".visese" ).hide();
        $( ".viseserev" ).hide();
        $( ".mesric" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitogenerico" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".revese" ).hide();
        $( ".esito" ).hide();
        $( ".okstampa" ).hide();
        $( ".esitook" ).hide();
        $( ".esitoko" ).hide();
        $( ".esitover" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".esitorevocako" ).hide();
        $( ".esitorevocaver" ).hide();
        $( ".esitorevocaok" ).hide();
        $( ".besitook" ).hide();
        $( ".besitover" ).hide();
        $( ".NoTitolare" ).hide();


    }



    getInfoAssistito( codFiscale: string ) {
        this.assistitoStoreService.findCittadino( codFiscale )
            .subscribe( assistiti => this.listaAssistiti[0] = assistiti );
    }

    SvuotaFiltroRicerca() {
        ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "cognomedicfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "nomedicfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "codfiscdicfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "cognometitfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "nometitfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "codfisctitfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value = "";

        ( ( document.getElementById( "idassistitodicric" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "idassistitotitric" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "statofiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "codEsenzionefiltro" ) ) as HTMLInputElement ).value = "";
         ( document.getElementById( "storico" ) as HTMLInputElement ).checked = false;


        $( '#startdateda' ).datepicker( 'update', '' );
        $( '#startdatea' ).datepicker( 'update', '' );
        $( '#datanascbenfiltrod' ).datepicker( 'update', '' );
        $( '#datanascdicfiltrod' ).datepicker( 'update', '' );
        $( '#datanasctitfiltrod' ).datepicker( 'update', '' );

        ( document.getElementById( "operatorefiltro" ) as HTMLSelectElement ).value = this.listaAssistiti[0].idAura;
        if ( ( document.getElementById( "operatorefiltro" ) as HTMLSelectElem //ricalcola la combo per prevedere il nuovo operatore
            this.loading = true;
            this.getEsenzioniAsl();
        }

        ( document.getElementById( "operatorefiltro" ) as HTMLSelectElement ).selectedIndex = 0;


    }



    ApriElencoEse() {

        if ( this.confermarev == true ) {

            $( ".eleeseris" ).hide();
            $( ".trova" ).hide();
            $( ".sitrova" ).hide();
        }
        else {
            if ( document.getElementById( "numesentionitrovate" ).innerHTML== "") {
                    $( ".eleeseris" ).hide();
            }
            else
            {
                $( ".eleeseris" ).show();
            }
            $( ".trova" ).hide();
            $( ".sitrova" ).hide();
        }
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        this.RipristinaDati( 'Dichiarante' );
        this.RipristinaDati( 'Beneficiario' );
        this.RipristinaDati( 'Titolare' );
        $( ".mesric" ).hide();
        this.SvuotaFiltroRicerca();
        $( "#codEsenzione" ).removeClass( "inputError" );
        $( "#codFiscaleBen" ).removeClass( "inputError" );
        $( "#codtit" ).removeClass( "inputError" );
        $( "#cognomeben" ).removeClass( "inputError" );
        $( "#nomeben" ).removeClass( "inputError" );
        $( "#datanasBen" ).removeClass( "inputError" );
        $( "#checkNUOVO" ).css( "color", "" );
        $( ".esito" ).hide();
        $( ".noese" ).show();
        $( ".trova" ).hide();
        $( ".esitoko" ).hide();
        $( ".sitrova" ).hide();
        $( ".esitook" ).hide();
        $( ".siese" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".insnuovaese" ).hide();
        $( ".revese" ).hide();
        $( ".visese" ).hide();
        $( ".viseserev" ).hide();
        $( ".eleese" ).show();
        $( ".NoTitolare" ).hide();
        ( document.getElementById( "etitolareSif" ) as HTMLInputElement ).click();
        ( document.getElementById( "etitolareSif" ) as HTMLInputElement ).checked = false;
		( document.getElementById( "etitolareSib" ) as HTMLInputElement ).checked = false;
		( document.getElementById( "etitolareNof" ) as HTMLInputElement ).checked = false;
        $( window ).scrollTop( 0 );
    }

    SelezioneMessaggio( id: string ) {
        switch ( id ) {
            case "esitook":
                $( ".insnuovaese" ).hide();
                $( ".eleese" ).hide();
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).hide();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".esito" ).show();
                $( ".okstampa" ).show();
                $( ".esitook" ).show();
                $( ".esitoko" ).hide();
                $( ".esitover" ).hide();
                $( ".esitorevoca" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".besitook" ).show();
                $( ".besitover" ).hide();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();

                $( window ).scrollTop( 0 );
                break;
            case "esitoko":

                $( ".revese" ).hide();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();

                $( ".eleese" ).hide();
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".esitoricercagenerico" ).hide();

                $( ".esitoko" ).show();
                $( ".esitook" ).hide();
                $( ".esitover" ).hide();
                $( ".esitorevoca" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".besitook" ).hide();
                $( ".besitover" ).hide();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();

                $( window ).scrollTop( 0 );
                break;
            case "esitover":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).hide();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".eleese" ).hide();
                $( ".esito" ).show();
                $( ".esitoko" ).hide();
                $( ".esitook" ).hide();
                $( ".esitover" ).show();
                $( ".esitorevoca" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".besitook" ).hide();
                $( ".besitover" ).show();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();

                $( window ).scrollTop( 0 );
                break;
            case "esitogenerico":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).hide();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".esitogenerico" ).show();
                $( ".eleese" ).hide();
                $( ".esito" ).show();
                $( ".esitoko" ).hide();
                $( ".esitook" ).hide();
                $( ".esitover" ).hide();
                $( ".esitorevoca" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".besitook" ).hide();
                $( ".besitover" ).show();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();

                $( window ).scrollTop( 0 );
                break;
            case "esitorevocaok":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".eleese" ).hide();
                $( ".revese" ).show();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".esito" ).hide();
                $( ".esitook" ).hide();
                $( ".esitorevoca" ).show();
                $( ".esitorevocaok" ).show();
                $( ".esitoko" ).hide();
                $( ".esitover" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocagenerico" ).hide();
                $( ".besitook" ).hide();
                $( ".besitover" ).show();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();
                $( "#motrev" ).removeClass( "inputError" );

                $( window ).scrollTop( 0 );
                break;
            case "esitorevocako":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).show();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".eleese" ).hide();
                $( ".esito" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".esitoko" ).hide();
                $( ".esitorevoca" ).show();
                $( ".esitorevocako" ).show();
                $( ".esitook" ).hide();
                $( ".esitover" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocagenerico" ).hide();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();
                ( document.getElementById( "motrev" ) as HTMLInputElement ).readOnly = false;
                ( document.getElementById( "notaintrev" ) as HTMLInputElement ).readOnly = false;
                $( ".besitorev" ).show();
                $( ".besitorevfine" ).hide();
                this.FocusInput( "Motivo della revoca" );
                this.confermarev = false;

                $( window ).scrollTop( 0 );
                break;
            case "esitorevocaver":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).show();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".eleese" ).hide();
                $( ".esito" ).hide();
                $( ".esitorevoca" ).show();
                $( ".esitoko" ).hide();
                $( ".esitook" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".esitorevocaver" ).show();
                $( ".esitorevocagenerico" ).hide();
                $( ".esitover" ).hide();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();
                $( "#motrev" ).removeClass( "inputError" );

                $( window ).scrollTop( 0 );
                break;
            case "esitorevocagenerico":
                $( ".eleeseris" ).hide();
                $( ".mesric" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercako" ).hide();
                $( ".esitoricercagenerico" ).hide();
                $( ".revese" ).show();
                $( ".visese" ).hide();
                $( ".viseserev" ).hide();
                $( ".insnuovaese" ).hide();
                $( ".esitogenerico" ).hide();
                $( ".eleese" ).hide();
                $( ".esito" ).hide();
                $( ".esitorevoca" ).show();
                $( ".esitoko" ).hide();
                $( ".esitook" ).hide();
                $( ".esitorevocako" ).hide();
                $( ".esitorevocaok" ).hide();
                $( ".esitorevocaver" ).hide();
                $( ".esitorevocagenerico" ).show();
                $( ".esitover" ).hide();
                $( ".trova" ).hide();
                $( ".sitrova" ).hide();
                $( "#motrev" ).removeClass( "inputError" );

                $( window ).scrollTop( 0 );
                break;
            case "esitorevocatotgenerico":
                $( ".esitorevocatot" ).show();
                $( ".esitorevocatotgenerico" ).show();
                $( ".esitorevocatotko" ).hide();
                $( ".esitorevocatotok" ).hide();
                $( ".numerotrovatetot" ).show();
                $( ".tabellarictot" ).show();
                $( ".idmotrevtot" ).show();
                $( ".besitorevtot" ).hide();
                $( ".besitorevtotfine" ).show();
                break;
            case "esitorevocatotko":
                $( ".esitorevocatot" ).show();
                $( ".esitorevocatotgenerico" ).hide();
                $( ".esitorevocatotko" ).show();
                $( ".esitorevocatotok" ).hide();
                $( ".numerotrovatetot" ).show();
                $( ".tabellarictot" ).show();
                $( ".idmotrevtot" ).show();
                $( ".besitorevtot" ).show();
                $( ".besitorevtotfine" ).hide();
                break;
        }
    }


    paginaPersonale() {

        window.open(this.config.getProfilo(), "_blank");

    }

    PaginaRevocaEsenzione() {

        $( ".insnuovaese" ).hide();
        $( ".eleeseris" ).hide();
        $( ".mesric" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".esito" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".noese" ).hide();

        $( ".eleese" ).hide();
        $( ".revese" ).show();
        $( ".visese" ).hide();
        $( ".viseserev" ).hide();
        $( ".trova" ).hide();
        $( ".sitrova" ).hide();


        $( ".revese" ).show();
        $( ".eleese" ).hide();
        $( ".besitorev" ).show();
        $( ".notifica" ).hide();
        $( ".besitorevfine" ).hide();
        ( document.getElementById( "motrev" ) as HTMLInputElement ).readOnly = false;
        ( document.getElementById( "notaintrev" ) as HTMLInputElement ).readOnly = false;

        $( window ).scrollTop( 0 );

    }

    PaginaVisualizzaEsenzione() {

        $( ".insnuovaese" ).hide();
        $( ".eleeseris" ).hide();
        $( ".mesric" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".esito" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".noese" ).hide();

        $( ".eleese" ).hide();
        $( ".revese" ).hide();
        $( ".visese" ).show();
        $( ".trova" ).hide();
        $( ".sitrova" ).hide();
}

        $( ".eleese" ).hide();
        $( ".besitorev" ).hide();
        $( ".notifica" ).hide();
        $( ".besitorevfine" ).hide();

        $( window ).scrollTop( 0 );

    }
    selezionastatofiltro( id: string ) {
        this.parametroStoreService.getParametro( id )
            .subscribe( parametro => this.parametro = parametro );
    }

    getParam( id: string ) {
        this.parametroStoreService.getParametro( id )
            .subscribe( parametro => this.parametroTmp = parametro );
    }

    AnnullaRicerca() {
        this.SvuotaFiltroRicerca();
        this.listaEsenzioneOperatore = [];
        this.pagina = 1;
        $( ".eleeseris" ).hide();

    }

    ApplicaFiltroOperatoreFirst(){
        this.pagina = 1;
        $( ".esitoricercako" ).hide();
        $( ".esitoricercaok" ).hide();
        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = "";

        var cognomebeneficiario = ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value;
        var nomebeneficiario = ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value;
        var codfiscbenfiltro = ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value;
        var datanascbenfiltro = ( ( document.getElementById( "datanascbenfiltro" ) ) as HTMLInputElement ).value;
        var cognomedicfiltro = ( ( document.getElementById( "cognomedicfiltro" ) ) as HTMLInputElement ).value;
        var nomedicfiltro = ( ( document.getElementById( "nomedicfiltro" ) ) as HTMLInputElement ).value;
        var codfiscdicfiltro = ( ( document.getElementById( "codfiscdicfiltro" ) ) as HTMLInputElement ).value;
        var datanascdicfiltro = ( ( document.getElementById( "datanascdicfiltro" ) ) as HTMLInputElement ).value;
        var cognometitolare = ( ( document.getElementById( "cognometitfiltro" ) ) as HTMLInputElement ).value;
        var nometitolare = ( ( document.getElementById( "nometitfiltro" ) ) as HTMLInputElement ).value;
        var codfisctitolare = ( ( document.getElementById( "codfisctitfiltro" ) ) as HTMLInputElement ).value;
        var datanasctitolare = ( ( document.getElementById( "datanasctitfiltro" ) ) as HTMLInputElement ).value;
        if (cognomebeneficiario=="" || nomebeneficiario=="" ||codfiscbenfiltro=="" ||datanascbenfiltro==""){
        ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value="";
        }
        if (cognomedicfiltro=="" || nomedicfiltro=="" ||codfiscdicfiltro=="" ||datanascdicfiltro==""){
            ( ( document.getElementById( "idassistitodicric" ) ) as HTMLInputElement ).value="";
         }
        if (cognometitolare=="" || nometitolare=="" ||codfisctitolare=="" ||datanasctitolare==""){
            ( ( document.getElementById( "idassistitotitric" ) ) as HTMLInputElement ).value="";
         }


           if (( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value =="" && ( ( document.getElementById( "codfiscdicfiltro" ) ) as HTMLInputElement ).value ==""  && ( ( document.getElementById( "codfisctitfiltro" ) ) as HTMLInputElement ).value =="" ) {
               $( ".eleeseris" ).hide();
               $( ".esitoricercako" ).show();
               ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = "Attenzione!! Per effettuare la ricerca sulle Esenzioni, selezionare un Dichiarante o un Beneficiario o un Titolare";
           }
           else {
               this.ApplicaFiltroOperatore();
          }

    }

    ApplicaFiltroOperatore() {

        document.getElementById( "esitobloccoKo" ).style.display = "none";
        document.getElementById( "esitobloccoOk" ).style.display = "none";

        this.loading = true;
        let a: string;

        var e = ( document.getElementById( "codEsenzionefiltro" ) ) as HTMLSelectElement;
        var sel = e.selectedIndex;
        var selEsenzionefiltro = ""
        if ( sel !== -1 ) {
            var opt = e.options[sel];
            selEsenzionefiltro = ( <HTMLOptionElement>opt ).value;
        };

        var e = ( document.getElementById( "statofiltro" ) ) as HTMLSelectElement;
        var sel = e.selectedIndex;
        var statofiltro = "";
        if ( sel !== -1 ) {
            var opt = e.options[sel];
            statofiltro = ( <HTMLOptionElement>opt ).value;
            statofiltro = statofiltro.substr( -1, 1 );
        };

        var cognomebeneficiario = ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value;
        var nomebeneficiario = ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value;
        var codfiscbenfiltro = ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value;
        var datanascbenfiltro = ( ( document.getElementById( "datanascbenfiltro" ) ) as HTMLInputElement ).value;
        var cognomedicfiltro = ( ( document.getElementById( "cognomedicfiltro" ) ) as HTMLInputElement ).value;
        var nomedicfiltro = ( ( document.getElementById( "nomedicfiltro" ) ) as HTMLInputElement ).value;
        var codfiscdicfiltro = ( ( document.getElementById( "codfiscdicfiltro" ) ) as HTMLInputElement ).value;
        var datanascdicfiltro = ( ( document.getElementById( "datanascdicfiltro" ) ) as HTMLInputElement ).value;
        var cognometitolare = ( ( document.getElementById( "cognometitfiltro" ) ) as HTMLInputElement ).value;
        var nometitolare = ( ( document.getElementById( "nometitfiltro" ) ) as HTMLInputElement ).value;
        var codfisctitolare = ( ( document.getElementById( "codfisctitfiltro" ) ) as HTMLInputElement ).value;
        var datanasctitolare = ( ( document.getElementById( "datanasctitfiltro" ) ) as HTMLInputElement ).value;
        var idAuraBeneficiario = ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value;

        var idAuraDichiarante = ( ( document.getElementById( "idassistitodicric" ) ) as HTMLInputElement ).value;

        var idAuraTitolare = ( ( document.getElementById( "idassistitotitric" ) ) as HTMLInputElement ).value;

        var datainifiltro = ( document.getElementById( "validada" ) as HTMLInputElement ).value;
        a = datainifiltro;

        var datafinefiltro = ( document.getElementById( "validaa" ) as HTMLInputElement ).value;
        a = datafinefiltro;
        var storicofiltro = "No";

        if  ( ( document.getElementById( "storico" ) as HTMLInputElement ).checked ) {
            storicofiltro = "Si";
        }


        var inCaricoASL = false;
        var selOperatorefiltro = "";
        var selOperatorefiltroNome = "";

        if (( document.getElementById( "operatorefiltro" ) as HTMLSelectElement ).selectedIndex == 0 || ( document.getElementById( "operatorefiltro" ) as HTMLSelectElement ).selectedIndex == 1) {
            if ( ( document.getElementById( "operatorefiltro" ) as HTMLSelectElement ).selectedIndex == 0) {
            selOperatorefiltro="99999999";
            selOperatorefiltroNome="Tutta ASL";
            inCaricoASL = true;
            }
        }
        else {
            inCaricoASL = true;
            var e = ( document.getElementById( "operatorefiltro" ) ) as HTMLSelectElement;
            var sel = e.selectedIndex;
            if ( sel !== -1 ) {
                var opt = e.options[sel];
                selOperatorefiltro = ( <HTMLOptionElement>opt ).value;
                selOperatorefiltroNome = ( <HTMLOptionElement>opt ).text;
            };
        };

        this.getEsenzioneOperatorePaginato( selEsenzionefiltro, statofiltro, datainifiltro, datafinefiltro, inCaricoASL, idAuraBeneficiario, idAuraDichiarante,idAuraTitolare, selOperatorefiltro, selOperatorefiltroNome, this.pagina, this.orderBy, this.asc,storicofiltro, codfisctitolare, codfiscdicfiltro );

    }

    SetPage( i, event: any ) {
        if ( i != -2 ) {
            event.preventDefault();
            this.pagina = i;
            this.costruisciBarra( this.pagina, this.lunghezzaarray )
            this.ApplicaFiltroOperatore();
        }
    }

    getEsenzioneOperatorePaginato( selEsenzionefiltro: string, statofiltro: string, datainifiltro: string, datafinefiltro: string, inCaricoASL: boolean, idAuraBeneficiario: string, idAuraDichiarante: string, idAuraTitolare: string, selOperatorefiltro: string, selOperatorefiltroNome: string, pagina: number, orderBy: string, asc: boolean,storicofiltro:string, codFiscaleTitolare:string, codFiscaleDichiarante:string ) {

        this.loading = true;
        let ricesecit = new RicercaEsenzioneOperatore();
        var nota = "";
        var trovato = false;

        if ( selEsenzionefiltro != '' ) {
            ricesecit.codEsenzione = selEsenzionefiltro;
            nota = nota + " Codice Esenzione";
        }
        if ( statofiltro != '' ) {
            ricesecit.codStatoEsenzione = statofiltro;
            nota = nota + " Stato Esenzione";
        }
        if ( datainifiltro != '' ) {
            ricesecit.dataInizioValidita = datainifiltro;
            nota = nota + " Data Inizio";
        }
        if ( datafinefiltro != '' ) {
            ricesecit.dataFineValidita = datafinefiltro;
            nota = nota + " Data Fine";
        }
        if ( inCaricoASL != null ) {
            ricesecit.inCaricoASL = inCaricoASL;
            nota = nota + " In carico ASL";
        }
        if ( selOperatorefiltro != '' ) {
            ricesecit.idAuraOperatore = selOperatorefiltro;
            nota = nota + " In carico Operatore";
        }
        if ( idAuraBeneficiario != null ) {
            ricesecit.idAuraBeneficiario = idAuraBeneficiario;
        }
        if ( idAuraDichiarante != null ) {
            ricesecit.idAuraDichiarante = idAuraDichiarante;
        }
        if ( idAuraTitolare != null ) {
            ricesecit.idAuraTitolare = idAuraTitolare;
        }
        if  (storicofiltro != null) {
            ricesecit.storico = storicofiltro;
        }

        if(codFiscaleTitolare != null){
            ricesecit.codFiscaleTitolare = codFiscaleTitolare;
        }

        if(codFiscaleDichiarante != null){
            ricesecit.codFiscaleDichiarante = codFiscaleDichiarante;
        }

        if ( selOperatorefiltro == '99999999' ) {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni della ASL";
        }
        else if ( selOperatorefiltro != '' ) {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni inserite dall'operatore " + selOperatorefiltroNome;
        }
        else {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni compilate online";
        }

        if ( selOperatorefiltro != '' ) {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni inserite dall'operatore " + selOperatorefiltroNome;
        }
        else {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni compilate online";
        }

        ricesecit.asc = asc;
        ricesecit.orderBy = orderBy;
        ricesecit.pagina = pagina;

        this.esenzioneStoreService.getEsenzioneOperatorePaginato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] ) {

                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();

                        $( ".eleeseris" ).hide();
                        this.loading = false;

                    } else {
                        var numeroElementiPagina = +this.parametroTmp[0].valoreParametro;

                        this.lunghezzaarray = Math.ceil(( body.data[0].numeroTotaleElementi / numeroElementiPagina ) );
                        this.numerototpagine = new Array( this.lunghezzaarray );
                        this.costruisciBarra( this.pagina, this.lunghezzaarray );
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".eleeseris" ).show();
                        $( ".mesric" ).hide();
                        ( document.getElementById( "numesentionitrovate" ).innerHTML ) = body.data[0].numeroTotaleElementi;
                        this.loading = false;
                        this.listaEsenzioneOperatore = body.data;
                    }
                }

            },
            errorCode => {
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                this.statusCode = errorCode.status;
                switch ( this.statusCode ) {
                    case 400:
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).hide();

                        $( ".eleeseris" ).hide();

                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        $( ".esitoricercagenerico" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();

                        $( ".eleeseris" ).hide();

                        this.loading = false;
                        break;
                }
            } );


    }

    getEsenzioniTitolareRevoca(id: number) {

        this.loading = true;

        this.listaEsenzioneOperatoreRevoca = [];
        ( document.getElementById( "titolarevocate" ).innerHTML ) = "Elenco esenzione da revocare del titolare " + this.listaEsenzioneOperatore[id].titolare.nome + " " + this.listaEsenzioneOperatore[id].titolare.cognome;
        ( document.getElementById( "motrevtot" ) as HTMLInputElement ).value = "";
        var titolare = null;
        if (this.listaEsenzioneOperatore[id].titolare.idAura!=null)
        titolare =this.listaEsenzioneOperatore[id].titolare.idAura;
        else
        titolare = this.listaEsenzioneOperatore[id].titolare.codFiscale;
        this.esenzioneStoreService.getEsenzioniTitolareRevoca(titolare)
        .subscribe( successCode => {
        this.statusCode = successCode.status;
            let body = successCode.json();
            if ( this.statusCode == 200 ) {
            if ( body.data == "" || body.data == [] ) {
                this.loading = false;

                $( ".viseserev" ).show();
                $( ".esitorevocatot" ).show();
                $( ".esitorevocatotgenerico" ).hide();
                $( ".esitorevocatotko" ).hide();
                $( ".esitorevocatotok" ).show();
                $( ".numerotrovatetot" ).hide();
                $( ".tabellarictot" ).hide();
                $( ".idmotrevtot" ).hide();
                $( ".besitorevtot" ).hide();
                $( ".besitorevtotfine" ).show();
                ( document.getElementById( "messaggiorevocatotok" ).innerHTML ) = "Non ci sono esenzioni da revocare."
            }
            else
                {
                ( document.getElementById( "numesentionitrovaterev" ).innerHTML ) = body.data[0].numeroTotaleElementi;
                this.loading = false;
                this.listaEsenzioneOperatoreRevoca = body.data;

                $( ".viseserev" ).show();
                $( ".esitorevocatot" ).hide();
                $( ".esitorevocatotgenerico" ).hide();
                $( ".esitorevocatotko" ).hide();
                $( ".esitorevocatotok" ).hide();
                $( ".numerotrovatetot" ).show();
                $( ".tabellarictot" ).show();
                $( ".idmotrevtot" ).show();
                $( ".besitorevtot" ).show();
                $( ".besitorevtotfine" ).hide();
                }
            }
        },
        errorCode => {
            $( ".viseserev" ).show();
            $( ".esitorevocatot" ).show();
            $( ".esitorevocatotgenerico" ).hide();
            $( ".esitorevocatotko" ).hide();
            $( ".esitorevocatotok" ).show();
            $( ".numerotrovatetot" ).hide();
            $( ".tabellarictot" ).hide();
            $( ".idmotrevtot" ).hide();
            $( ".besitorevtot" ).hide();
            $( ".besitorevtotfine" ).show();
            this.loading = false;
        } );
        $( ".insnuovaese" ).hide();
        $( ".eleeseris" ).hide();
        $( ".mesric" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".esito" ).hide();
        $( ".esitorevoca" ).hide();
        $( ".noese" ).hide();
        $( ".eleese" ).hide();
        $( ".revese" ).hide();
        $( ".visese" ).hide();
        $( ".trova" ).hide();
        $( ".sitrova" ).hide();
        $( ".eleese" ).hide();
        $( ".besitorev" ).hide();
        $( ".notifica" ).hide();
        $( ".besitorevfine" ).hide();
        ( document.getElementById( "motrevtot" ) as HTMLInputElement ).readOnly = false;
        $( window ).scrollTop( 0 );

    }

    sbloccaEsenzioneModal( id: string){
        this.idSblocco = id;
        document.getElementById( "sbloccoModal" ).style.visibility = "visible";
    }

    sbloccaEsenzione() {
        document.getElementById( "sbloccoModal" ).style.visibility = "hidden";
        document.getElementById( "esitobloccoKo" ).style.display = "";
        this.esenzioneStoreService.sbloccaEsenzione( this.idSblocco )
        .subscribe( successCode => {
            document.getElementById( "esitobloccoKo" ).style.display = "none";
            document.getElementById( "esitobloccoOk" ).style.display = "";
            window.scrollTo(0, 0);
            this.AnnullaRicerca();
        })
    }

    costruisciBarra( currentPage: number, numTotPage: number ) {
        for ( var i = 0; i < this.lunghezzaarray; i++ ) {
            this.numerototpagine[i] = ( -1 );
        }
        this.numerototpagine[0] = -1;
        if ( numTotPage <= 9 ) {
            for ( var i = 0; i < this.lunghezzaarray; i++ ) {
                this.numerototpagine[i + 1] = i + 1;

            }
        } else {
            var diffine = numTotPage - currentPage;
            var difini = currentPage - 1;


            this.numerototpagine[1] = 1;
            if ( diffine < 3 ) {


                for ( var i = currentPage - 3 - ( 3 - diffine ); i < currentPage + diffine; i++ ) {
                    if ( i != 1 && i != numTotPage ) {
                        this.numerototpagine[i] = i;

                    }
                }
                this.numerototpagine[currentPage - 3 - ( 3 - diffine ) ] = -2;
            }
            else if ( difini < 3 ) {
                for ( var i = currentPage - difini; i < currentPage + 3 + ( 3 - difini ); i++ ) {
                    if ( i != 1 && i != numTotPage ) {
                        this.numerototpagine[i] = i;

                    }
                }
                this.numerototpagine[currentPage + 3 + ( 3 - difini )] = -2;
            }
            else {
                if(currentPage - 3 >1){
                    this.numerototpagine[currentPage - 3] = -2;
                }
                for ( var i = currentPage - 2; i <= currentPage + 3; i++ ) {
                    if ( i != 1 && i != numTotPage ) {
                        this.numerototpagine[i] = i;

                    }
                }
                if ( currentPage + 3 != numTotPage ) {
                    this.numerototpagine[currentPage + 3] = -2;
                }
            }


            this.numerototpagine[numTotPage] = numTotPage;
        }
    }


    getEsenzioneOperatore( selEsenzionefiltro: string, statofiltro: string, datainifiltro: string, datafinefiltro: string, inCaricoASL: boolean, idAuraBeneficiario: string, idAuraDichiarante: string, selOperatorefiltro: string, selOperatorefiltroNome: string,storicofiltro:string) {

        this.loading = true;
        let ricesecit = new RicercaEsenzioneOperatore();
        var nota = "";
        var trovato = false;

        if ( selEsenzionefiltro != '' ) {
            ricesecit.codEsenzione = selEsenzionefiltro;
            nota = nota + " Codice Esenzione";
        }
        if ( statofiltro != '' ) {
            ricesecit.codStatoEsenzione = statofiltro;
            nota = nota + " Stato Esenzione";
        }
        if ( datainifiltro != '' ) {
            ricesecit.dataInizioValidita = datainifiltro;
            nota = nota + " Data Inizio";
        }
        if ( datafinefiltro != '' ) {
            ricesecit.dataFineValidita = datafinefiltro;
            nota = nota + " Data Fine";
        }
        if ( inCaricoASL != null ) {
            ricesecit.inCaricoASL = inCaricoASL;
            nota = nota + " In carico ASL";
        }
        if ( selOperatorefiltro != '' ) {
            ricesecit.idAuraOperatore = selOperatorefiltro;
            nota = nota + " In carico Operatore";
        }
        if ( idAuraBeneficiario != null ) {
            ricesecit.idAuraBeneficiario = idAuraBeneficiario;
        }
        if ( idAuraDichiarante != null ) {
            ricesecit.idAuraDichiarante = idAuraDichiarante;
        }
        if  (storicofiltro != null) {
            ricesecit.storico = storicofiltro;
        }
        if ( selOperatorefiltro != '' ) {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni inserite dall'operatore " + selOperatorefiltroNome;
        }
        else {
            document.getElementById( "componitesto" ).innerHTML = "Hai Cercato: Tutte le esenzioni compilate online";
        }

        this.esenzioneStoreService.getEsenzioneOperatore( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] ) {

                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".eleeseris" ).hide();
                        this.loading = false;

                    } else {
                        $( ".eleeseris" ).show();
                        $( ".mesric" ).hide();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        ( document.getElementById( "numesentionitrovate" ).innerHTML ) = body.data.length;
                        this.loading = false;
                        this.listaEsenzioneOperatore = body.data;
                    }
                }

            },
            errorCode => {
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                this.statusCode = errorCode.status;
                switch ( this.statusCode ) {
                    case 400:
);
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).show();
                        $( ".eleeseris" ).hide();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        $( ".esitoricercagenerico" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercaok" ).hide();

                        $( ".eleeseris" ).hide();

                        this.loading = false;
                        break;
                }
            } );


    }

    stampa( id: number ) {
        this.loading = true;
        var cf_beneficiario = this.listaEsenzioneOperatore[id].codFiscaleBeneficiario;
        if ( id == -1 ) {
            var numese = parseInt( document.getElementById( "newEsenzione" ).innerHTML );
            var fisc = document.getElementById( "codicefiscbenmes" ).innerHTML;
        }
        else {
            var numese = this.listaEsenzioneOperatore[id].idEsenzione;
            var fisc = document.getElementById( "codfiscstampa" ).innerHTML;
        }
        if (numese==null)
            numese=0
        if ( numese == 0 && id != -1 ) {
            let slash = /\//gi;
            var datainival = this.listaEsenzioneOperatore[id].inizioValidita.replace( slash, '' );
            var tipoesenzione = "Autocertificata";
            var protocollo = this.listaEsenzioneOperatore[id].motivoRevoca;

            if ( this.listaEsenzioneOperatore[id].idUserInsert == "99999997" ) {
                tipoesenzione = "Certificata";

            }
            this.autocertificazioneStoreService.createReportStorico( protocollo, this.listaEsenzioneOperatore[id].codEsenzione, datainival, "Operatore", tipoesenzione, cf_beneficiario ).subscribe(
                ( res ) => {
                    var fileURL = URL.createObjectURL( res );
                    var link = document.createElement( 'a' );
                    link.href = window.URL.createObjectURL( res );
                    link.download = protocollo + "_" + fisc;
                    document.body.appendChild( link );
                    link.click();
                    document.body.removeChild( link );
                    this.loading = false;
                }
            );
        }
        else {
            this.autocertificazioneStoreService.createReport( numese, "Operatore",cf_beneficiario).subscribe(
                ( res ) => {
                    this.loading = false;
                    var fileURL = URL.createObjectURL( res );
                    var link = document.createElement( 'a' );
                    link.href = window.URL.createObjectURL( res );
                    link.download = numese + "_" + fisc;
                    document.body.appendChild( link );
                    link.click();
                    document.body.removeChild( link );

                }
            );
        }
    }

    ScegliCittadino( id: number ) {
        $( ".esitoricercako" ).hide();
        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = "";

        if ( ( document.getElementById( "provenienza" ).innerHTML ) == "Dichiarante" ) {
            ( document.getElementById( "codFiscalediclabel" ).innerHTML ) = this.assistito[id].codFiscale;
            ( document.getElementById( "cognomediclabel" ).innerHTML ) = this.assistito[id].cognome;
            ( document.getElementById( "nomediclabel" ).innerHTML ) = this.assistito[id].nome;
            ( document.getElementById( "sessoDiclabel" ).innerHTML ) = this.assistito[id].codSesso;
            ( document.getElementById( "datanasDiclabel" ).innerHTML ) = this.assistito[id].dataNascita;
            ( document.getElementById( "comuneDiclabel" ).innerHTML ) = this.assistito[id].comuneNascita;
            ( document.getElementById( "provinciaDiclabel" ).innerHTML ) = this.assistito[id].provinciaNascita;
            ( document.getElementById( "idassistitodic" ).innerHTML ) = id.toString();
            ( document.getElementById( "dataSSNdic" ).innerHTML ) = this.assistito[id].datascadenzaSSN;
  }
            $( ".sezionedicsceltalabel" ).show();
            $( ".sezionedicscelta" ).hide();
            $( "#dichPanel" ).hide();
            $( "#dichPanelShow" ).show();
            ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
            $( "#recuperadati" ).removeClass( "disabledbutton" );
        }
        else if ( ( document.getElementById( "provenienza" ).innerHTML ) == "Beneficiario" ) {
            ( document.getElementById( "codFiscalebenlabel" ).innerHTML ) = this.assistito[id].codFiscale;
            ( document.getElementById( "cognomebenlabel" ).innerHTML ) = this.assistito[id].cognome;
            ( document.getElementById( "nomebenlabel" ).innerHTML ) = this.assistito[id].nome;
            ( document.getElementById( "sessobenlabel" ).innerHTML ) = this.assistito[id].codSesso;
            ( document.getElementById( "datanasbenlabel" ).innerHTML ) = this.assistito[id].dataNascita;
            ( document.getElementById( "comunebenlabel" ).innerHTML ) = this.assistito[id].comuneNascita;
            ( document.getElementById( "provinciabenlabel" ).innerHTML ) = this.assistito[id].provinciaNascita;
            ( document.getElementById( "idassistitoben" ).innerHTML ) = id.toString();
            ( document.getElementById( "dataSSN" ).innerHTML ) = this.assistito[id].datascadenzaSSN;


            if (this.assistito[id].datascadenzaSSN.substr(6, 4)!="9999") {
                document.getElementById( "nascdataSSN" ).style.display = "";
            }
            else
                {
                document.getElementById( "nascdataSSN" ).style.display = "none";
                }
            $( ".sezionebensceltalabel" ).show();
            $( ".sezionebenscelta" ).hide();
            $( "#benPanel" ).hide();
            $( "#benPanelShow" ).show();
            $( ".beneficiario" ).hide();
            ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
        }
        else if ( ( document.getElementById( "provenienza" ).innerHTML ) == "Titolare" ) {
            ( document.getElementById( "codFiscaletitlabel" ).innerHTML ) = this.assistito[id].codFiscale;
            ( document.getElementById( "cognometitlabel" ).innerHTML ) = this.assistito[id].cognome;
            ( document.getElementById( "nometitlabel" ).innerHTML ) = this.assistito[id].nome;
            ( document.getElementById( "sessotitlabel" ).innerHTML ) = this.assistito[id].codSesso;
            ( document.getElementById( "datanastitlabel" ).innerHTML ) = this.assistito[id].dataNascita;
            ( document.getElementById( "comunetitlabel" ).innerHTML ) = this.assistito[id].comuneNascita;
            ( document.getElementById( "provinciatitlabel" ).innerHTML ) = this.assistito[id].provinciaNascita;
            ( document.getElementById( "idassistitotit" ).innerHTML ) = id.toString();

            $( ".sezionebensceltalabel" ).show();
            $( ".sezionebenscelta" ).hide();
            $( "#titPanel" ).hide();
            $( "#titPanelShow" ).show();
            $( ".titolare" ).hide();
            ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
        }
        else if ( ( document.getElementById( "provenienza" ).innerHTML ) == "BeneficiarioRicerca" ) {
            ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value = this.assistito[id].codFiscale;
            ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value = this.assistito[id].cognome;
            ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value = this.assistito[id].nome;

            ( document.getElementById( "idassistitobenric" ) as HTMLInputElement ).value = this.assistito[id].idAura;


            ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
            $( '#datanascbenfiltrod' ).datepicker( 'update', this.assistito[id].dataNascita );
        }
        else if ( ( document.getElementById( "provenienza" ).innerHTML ) == "DichiaranteRicerca" ) {

            ( document.getElementById( "codfiscdicfiltro" ) as HTMLInputElement ).value = this.assistito[id].codFiscale;
            ( document.getElementById( "cognomedicfiltro" ) as HTMLInputElement ).value = this.assistito[id].cognome;
            ( document.getElementById( "nomedicfiltro" ) as HTMLInputElement ).value = this.assistito[id].nome;

            ( document.getElementById( "idassistitodicric" ) as HTMLInputElement ).value = this.assistito[id].idAura;


            ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
            $( '#datanascdicfiltrod' ).datepicker( 'update', this.assistito[id].dataNascita );
        }
        else if ( ( document.getElementById( "provenienza" ).innerHTML ) == "TitolareRicerca" ) {
            ( document.getElementById( "codfisctitfiltro" ) as HTMLInputElement ).value = this.assistito[id].codFiscale;
            ( document.getElementById( "cognometitfiltro" ) as HTMLInputElement ).value = this.assistito[id].cognome;
            ( document.getElementById( "nometitfiltro" ) as HTMLInputElement ).value = this.assistito[id].nome;
          ( document.getElementById( "idassistitotitric" ) as HTMLInputElement ).value = this.assistito[id].idAura;
          ( document.getElementById( "chiudimodalricerca" ) as HTMLInputElement ).click();
            $( '#datanasctitfiltrod' ).datepicker( 'update', this.assistito[id].dataNascita );

        }
    }

    RecuperaDatiDic() {
        document.getElementById( "codFiscalebenlabel" ).innerHTML = document.getElementById( "codFiscalediclabel" ).innerHTML
        document.getElementById( "cognomebenlabel" ).innerHTML = document.getElementById( "cognomediclabel" ).innerHTML;
        document.getElementById( "nomebenlabel" ).innerHTML = document.getElementById( "nomediclabel" ).innerHTML;
        document.getElementById( "sessobenlabel" ).innerHTML = document.getElementById( "sessoDiclabel" ).innerHTML;
        document.getElementById( "datanasbenlabel" ).innerHTML = document.getElementById( "datanasDiclabel" ).innerHTML;
        document.getElementById( "comunebenlabel" ).innerHTML = document.getElementById( "comuneDiclabel" ).innerHTML;
        document.getElementById( "provinciabenlabel" ).innerHTML = document.getElementById( "provinciaDiclabel" ).innerHTML;
        document.getElementById( "dataSSN" ).innerHTML = document.getElementById( "dataSSNdic" ).innerHTML;
        $( ".sezionebensceltalabel" ).show();
        $( ".sezionebenscelta" ).hide();
        $( "#benPanel" ).hide();
        $( "#benPanelShow" ).show();

        document.getElementById( "copiadatidic" ).innerHTML = "dic";
        this.datiben = "dic";
        if ((document.getElementById( "dataSSN" ).innerHTML).substr(6, 4)!="9999") {
            document.getElementById( "nascdataSSN" ).style.display = "";
        }
        else
        {
        document.getElementById( "nascdataSSN" ).style.display = "none";
        }
    }

    RipristinaDati( id: string ) {

        this.datiben = ""
        document.getElementById( "idassistitodic" ).innerHTML = "";
        document.getElementById( "idassistitoben" ).innerHTML = "";
        document.getElementById( "idassistitotit" ).innerHTML = "";
        if ( id == "Dichiarante" ) {
            ( document.getElementById( "codFiscaledic" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "cognomedic" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "nomedic" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "datanasDic" ) as HTMLInputElement ).value = "";
            document.getElementById( "codFiscalediclabel" ).innerHTML = "";
            document.getElementById( "cognomediclabel" ).innerHTML = "";;
            document.getElementById( "nomediclabel" ).innerHTML = "";;
            document.getElementById( "sessoDiclabel" ).innerHTML = "";
            document.getElementById( "datanasDiclabel" ).innerHTML = "";
            document.getElementById( "comuneDiclabel" ).innerHTML = "";
            document.getElementById( "provinciaDiclabel" ).innerHTML = "";
            $( ".sezionedicsceltalabel" ).hide();
            $( ".sezionedicscelta" ).show();
            $( "#dichPanel" ).show();
            $( "#dichPanelShow" ).hide();
            $( "#recuperadati" ).addClass( "disabledbutton" );
            if ( document.getElementById( "copiadatidic" ).innerHTML == "dic" ) {
                document.getElementById( "copiadatidic" ).innerHTML = ""
                this.RipristinaDati( "Beneficiario" );
            }
        }
        if ( id == "Beneficiario" ) {
            (document.getElementById( "flagconferma" ).innerHTML) = "1";
            ( document.getElementById( "codFiscaleben" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "cognomeben" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "nomeben" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "datanasBen" ) as HTMLInputElement ).value = "";
            document.getElementById( "codFiscalebenlabel" ).innerHTML = "";
            document.getElementById( "cognomebenlabel" ).innerHTML = "";
            document.getElementById( "nomebenlabel" ).innerHTML = "";
            document.getElementById( "sessobenlabel" ).innerHTML = "";
            document.getElementById( "datanasbenlabel" ).innerHTML = "";
            document.getElementById( "comunebenlabel" ).innerHTML = "";
            document.getElementById( "provinciabenlabel" ).innerHTML = "";
            document.getElementById( "dataSSN" ).innerHTML="";
            document.getElementById( "nascdataSSN" ).style.display = "none";
            $( ".sezionebensceltalabel" ).hide();
            $( ".sezionebenscelta" ).show();
            $( "#benPanel" ).show();
            $( "#benPanelShow" ).hide();

            if ( document.getElementById( "codFiscalediclabel" ).innerHTML == "" ) {
                $( "#recuperadati" ).addClass( "disabledbutton" );
            }
            else {
                $( "#recuperadati" ).removeClass( "disabledbutton" );
            }
        }
        if ( id == "Titolare" ) {
            ( document.getElementById( "codFiscaletit" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "cognometit" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "nometit" ) as HTMLInputElement ).value = "";
            ( document.getElementById( "datanasTit" ) as HTMLInputElement ).value = "";
            document.getElementById( "codFiscaletitlabel" ).innerHTML = "";
            document.getElementById( "cognometitlabel" ).innerHTML = "";
            document.getElementById( "nometitlabel" ).innerHTML = "";
            document.getElementById( "sessotitlabel" ).innerHTML = "";
            document.getElementById( "datanastitlabel" ).innerHTML = "";
            document.getElementById( "comunetitlabel" ).innerHTML = "";
            document.getElementById( "provinciatitlabel" ).innerHTML = "";
            $( '#datanastit' ).datepicker( 'update', '' );
            $( ".sezionetitsceltalabel" ).hide();
            $( ".sezionetitscelta" ).show();
            $( "#titPanel" ).show();
            $( "#titPanelShow" ).hide();

        }
    }

    NascondiTit(statotit :string) {
        if (statotit == 'SI' || statotit == 'SIF' || statotit == 'SIB'){
            $( ".NoTitolare" ).hide();

            this.RipristinaDati( "Titolare" )
        }else {
            $( ".NoTitolare" ).show();
        }
    }

    ApriBeneficiario( chicerchi: string ) {
        let a: string;
        this.loading = true;

        ( document.getElementById( "provenienza" ).innerHTML ) = chicerchi;

        let ricesecit = new CittadinoRicerca();
        ricesecit.codFiscale = "";
        ricesecit.cognome = "";
        ricesecit.nome = "";
        ricesecit.dataDiNascita = "";
        ricesecit.ruolo ="";

        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = "";

        $( ".tabellaricerca" ).hide();

        if ( chicerchi == "Beneficiario" ) {

            if ( ( ( document.getElementById( "codFiscaleben" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codFiscaleben" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "cognomeben" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.cognome = ( document.getElementById( "cognomeben" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "nomeben" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.nome = ( document.getElementById( "nomeben" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "datanasBen" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.dataDiNascita = ( document.getElementById( "datanasBen" ) as HTMLInputElement ).value;
                a = ricesecit.dataDiNascita;

            }
            ricesecit.ruolo = "Beneficiario";
        }
        else if ( chicerchi == "Dichiarante" ) {


            if ( ( ( document.getElementById( "codFiscaledic" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codFiscaledic" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "cognomedic" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.cognome = ( document.getElementById( "cognomedic" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "nomedic" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.nome = ( document.getElementById( "nomedic" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "datanasDic" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.dataDiNascita = ( document.getElementById( "datanasDic" ) as HTMLInputElement ).value;

            }
            ricesecit.ruolo = "Dichiarante";
        }
        else if ( chicerchi == "Titolare" ) {


            if ( ( ( document.getElementById( "codFiscaletit" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codFiscaletit" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "cognometit" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.cognome = ( document.getElementById( "cognometit" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "nometit" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.nome = ( document.getElementById( "nometit" ) as HTMLInputElement ).value.toUpperCase();
            }
            if ( ( ( document.getElementById( "datanasTit" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.dataDiNascita = ( document.getElementById( "datanasTit" ) as HTMLInputElement ).value;

            }
            ricesecit.ruolo = "Titolare";
        }
        else if ( chicerchi == "DichiaranteRicerca" ) {
            ((document.getElementById( "idassistitodicric" ) ) as HTMLInputElement ).value="";

            if ( ( ( document.getElementById( "codfiscdicfiltro" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codfiscdicfiltro" ) as HTMLInputElement ).value.toUpperCase();
            } else {
                ricesecit.cognome = ( document.getElementById( "cognomedicfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.nome = ( document.getElementById( "nomedicfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.dataDiNascita = ( document.getElementById( "datanascdicfiltro" ) as HTMLInputElement ).value;
            }
            ricesecit.ruolo ="Ricerca";

        }
        else if ( chicerchi == "BeneficiarioRicerca" ) {

            ((document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value="";

            if ( ( ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value.toUpperCase();
            }
            else {
                ricesecit.cognome = ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.nome = ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.dataDiNascita = ( document.getElementById( "datanascbenfiltro" ) as HTMLInputElement ).value;
            }
            ricesecit.ruolo = "Ricerca";
        }
        else if ( chicerchi == "TitolareRicerca" ) {

            ((document.getElementById( "idassistitotitric" ) ) as HTMLInputElement ).value="";
            if ( ( ( document.getElementById( "codfisctitfiltro" ) as HTMLInputElement ).value ) != "" ) {
                ricesecit.codFiscale = ( document.getElementById( "codfisctitfiltro" ) as HTMLInputElement ).value.toUpperCase();
            }
            else {
                ricesecit.cognome = ( document.getElementById( "cognometitfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.nome = ( document.getElementById( "nometitfiltro" ) as HTMLInputElement ).value.toUpperCase();
                ricesecit.dataDiNascita = ( document.getElementById( "datanasctitfiltro" ) as HTMLInputElement ).value;

            }
            ricesecit.ruolo = "Ricerca";

        }

        this.assistito = [];


        this.assistitoStoreService.findCittadini( ricesecit )
        .subscribe( successCode => {
            this.statusCode = successCode.status;
            let body = successCode.json();
            if ( this.statusCode == 200 ) {
                if ( body.data == "" || body.data == [] ) {

                    $( ".tabellaricerca" ).hide();
                    $( ".tabellaricercames" ).show();

                    if(chicerchi != "BeneficiarioRicerca" && chicerchi != "Beneficiario" && ricesecit.codFiscale!=""){
                        this.assistitoStoreService.findCittadinoFuoriRegione( ricesecit.codFiscale )
                            .subscribe( successCodes => {
                                this.statusCode = successCodes.status;
                                let body = successCodes.json();
                                if ( this.statusCode == 200 ) {
                                    if ( body.data == "" || body.data == [] ) {
                                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = body.message;
                                        this.loading = false;
                                    } else {
                                        $( ".tabellaricercames" ).hide();
                                        $( ".tabellaricerca" ).show();
                                        ( document.getElementById( "numtrovati" ).innerHTML ) = "1";
                                        this.loading = false;
                                        this.assistito = body.data;
                                        if ( chicerchi == "Titolare" ) this.titolareFR = true;
                                        if ( chicerchi == "Dichiarante" ) this.dichiaranteFR = true;
                                    }
                                }
                            },
                            errorCode => {
                                this.statusCode = errorCode.status;
                                var str = errorCode._body;
                                var objmsg = JSON.parse( str );
                                switch ( this.statusCode ) {
                                    case 400:
                                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = objmsg.message;
                                        $( ".tabellaricerca" ).hide();
                                        $( ".tabellaricercames" ).show();
                                        this.loading = false;
                                        break;
                                    default:
                                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                                        $( ".tabellaricerca" ).hide();
                                        $( ".tabellaricercames" ).show();
                                        this.loading = false;

                                        break;
                                }
                            }

                            );
                    }else {

                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = body.message;
                        this.loading = false;
                             if ( chicerchi == "Titolare" ) this.titolareFR = false;
                             if ( chicerchi == "Dichiarante" ) this.dichiaranteFR = false;

                        }

                } else {
                        $( ".tabellaricercames" ).hide();
                        $( ".tabellaricerca" ).show();
                        ( document.getElementById( "numtrovati" ).innerHTML ) = body.data.length
                        if ( chicerchi == "Titolare" ) this.titolareFR = false;
                        if ( chicerchi == "Dichiarante" ) this.dichiaranteFR = false;
                        this.loading = false;
                        this.assistito = body.data;
                    }
                }
            },
            errorCode => {
                this.statusCode = errorCode.status;

                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                switch ( this.statusCode ) {
                    case 400:
                        $( ".tabellaricerca" ).hide();
                        $( ".tabellaricercames" ).show();
                        if(chicerchi != "BeneficiarioRicerca" && chicerchi != "Beneficiario"  && ricesecit.codFiscale!=""){
                            this.assistitoStoreService.findCittadinoFuoriRegione( ricesecit.codFiscale )
                            .subscribe( successCodes => {
                                this.statusCode = successCodes.status;
                                let body = successCodes.json();
                                if ( this.statusCode == 200 ) {
                                    if ( body.data == "" || body.data == [] ) {
                                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = body.message;
                                        this.loading = false;
                                    } else {
                                    $( ".tabellaricercames" ).hide();
                                    $( ".tabellaricerca" ).show();
                                    ( document.getElementById( "numtrovati" ).innerHTML ) = "1";
                                    this.loading = false;
                                    this.assistito =  body;
                                    if ( chicerchi == "Titolare" ) this.titolareFR = true;
                                    if ( chicerchi == "Dichiarante" ) this.dichiaranteFR = true;
                                }
                                }
                            });
                        }
                        this.loading = false;
                        break;
                default:
                    $( ".tabellaricerca" ).hide();
                    $( ".tabellaricercames" ).show();
                    if(chicerchi != "BeneficiarioRicerca" && chicerchi != "Beneficiario"  && ricesecit.codFiscale!=""){
                        this.assistitoStoreService.findCittadinoFuoriRegione( ricesecit.codFiscale )
                        .subscribe( successCodes => {
                            this.statusCode = successCodes.status;
                            let body = successCodes.json();
                                if ( this.statusCode == 200 ) {
                                    if ( body.data == "" || body.data == [] ) {
                                        ( document.getElementById( "messaggioricercatabella" ).innerHTML ) = body.message;
                                        this.loading = false;
                                    } else {

                                $( ".tabellaricercames" ).hide();
                                $( ".tabellaricerca" ).show();
                                ( document.getElementById( "numtrovati" ).innerHTML ) = "1";
                                this.loading = false;
                                this.assistito =  body;
                                if ( chicerchi == "Titolare" ) this.titolareFR = true;
                                if ( chicerchi == "Dichiarante" ) this.dichiaranteFR = true;
                            }
                                }
                        });
                    }
                    this.loading = false;

                    break;
            }
        } )
    }


    resetForm( form ) {
        $( ':input', form ).each( function() {
            var type = this.type;
            var tag = this.tagName.toLowerCase();
            if ( type == 'text' || type == 'password' || tag == 'textarea' || type == 'date' )
                this.value = "";
            else if ( type == 'checkbox' || type == 'radio' )
                this.checked = false;
            else if ( tag == 'select' )
                this.selectedIndex = -1;
            $( '#descesefam' )[0].innerHTML = "";
;
        } );
    };

    NuovaEsenzione() {
        (document.getElementById( "flagconferma" ).innerHTML) = "1";
        $( '#codtit' )[0].selectedIndex = -1;
        $( '#codEsenzioneFam' )[0].selectedIndex = -1;
        document.getElementById( "esitobloccoKo" ).style.display = "none";
        document.getElementById( "esitobloccoOk" ).style.display = "none";
        document.getElementById( "nascdataSSN" ).style.display = "none";
        $( "#iniVal" ).hide();
        $( "#dataRic" ).hide();
        $( "#fineVal" ).hide();
        $( "#dichPanelShow" ).hide();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        this.datiben = ""
        document.getElementById( "copiadatidic" ).innerHTML = ""
        document.getElementById( "idassistitodic" ).innerHTML = "";
        document.getElementById( "idassistitoben" ).innerHTML = "";
        $( ".eleese" ).hide();
        this.resetForm( "#formEsenzionefam" );
        document.getElementById( "codFiscalediclabel" ).innerHTML = "";
        document.getElementById( "cognomediclabel" ).innerHTML = "";;
        document.getElementById( "nomediclabel" ).innerHTML = "";;
        document.getElementById( "sessoDiclabel" ).innerHTML = "";
        document.getElementById( "datanasDiclabel" ).innerHTML = "";
        document.getElementById( "comuneDiclabel" ).innerHTML = "";
        document.getElementById( "provinciaDiclabel" ).innerHTML = "";
        ( document.getElementById( "notaint" ) as HTMLInputElement ).value = "";
        $( ".insnuovaese" ).show();
        $( ".noese" ).hide();
        $( ".siese" ).show();
        $( ".sezionedicscelta" ).show();
        $( ".sezionebenscelta" ).show();
        $( ".sezionebensceltalabel" ).hide();
        $( "#tuttoRicerca" ).hide();


        document.getElementById( "codFiscalebenlabel" ).innerHTML = "";
        document.getElementById( "cognomebenlabel" ).innerHTML = "";
        document.getElementById( "nomebenlabel" ).innerHTML = "";
        document.getElementById( "sessobenlabel" ).innerHTML = "";
        document.getElementById( "datanasbenlabel" ).innerHTML = "";
        document.getElementById( "comunebenlabel" ).innerHTML = "";
        document.getElementById( "provinciabenlabel" ).innerHTML = "";
        this.isDisable = false;
    }

    getNotificaFiltro( codFiscale: string ) {
        this.loading = true;
        this.parametroStoreService.getNotificaOperatore( codFiscale )
            .subscribe( notifica => {
                this.notifica = notifica
                this.loading = false;
            } );
    }

    LeggiNotifiche() {
        this.notifica = [];
        document.getElementById( "divnotifica" ).style.visibility = "visible"
        document.getElementById( "mesnotifica" ).style.visibility = "hidden"
        this.Aprinotifiche();
        this.getNotificaFiltro( this.listaAssistiti[0].codFiscale );
    }

    Aprinotifiche() {
        if ( document.getElementById( "aperto" ).innerHTML == "aperto" ) {
            this.contanotifica = [];
            this.getNumeroNuoveNotificheOperatore( this.listaAssistiti[0].codFiscale )
            $( ".notifica" ).hide();
            document.getElementById( "aperto" ).innerHTML = "chiuso"
        }
        else {
            this.loading = true;
            this.contanotifica = [];
            this.getNumeroNuoveNotificheOperatore( this.listaAssistiti[0].codFiscale )
            $( ".notifica" ).show();
            document.getElementById( "aperto" ).innerHTML = "aperto"
        }
    }

    ChiudiNotifiche() {
        $( ".notifica" ).hide();

    }

    EliminaNotifica( id: number ) {
        this.loading = true;
        var conta = parseInt( document.getElementById( "conta" ).innerHTML )
        this.parametroStoreService.EliminaNotifica( parseInt( this.notifica[id].idNotifica ) )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                this.contanotifica = [];
                this.getNumeroNuoveNotificheOperatore( this.listaAssistiti[0].codFiscale )
                document.getElementById( "aperto" ).innerHTML = "chiuso"
                this.LeggiNotifiche();
                this.loading = false;
            },
            errorCode => {

                this.statusCode = errorCode.status;
                this.getMessaggio( "MSG004" );
                document.getElementById( "divnotifica" ).style.visibility = "hidden"
                document.getElementById( "mesnotifica" ).style.visibility = "visible"
                this.loading = false;
            } );
    }


    UpdateNotifica( id: number ) {
        this.loading = true;
        this.parametroStoreService.UpdNotifica( parseInt( this.notifica[id].idNotifica ) )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                this.contanotifica = [];
                this.getNumeroNuoveNotificheOperatore( this.listaAssistiti[0].codFiscale )
                document.getElementById( "aperto" ).innerHTML = "chiuso"
                this.LeggiNotifiche();
                this.loading = false;
            },
            errorCode => {
                this.statusCode = errorCode.status;
                this.getMessaggio( "MSG004" );
                document.getElementById( "divnotifica" ).style.visibility = "hidden"
                document.getElementById( "mesnotifica" ).style.visibility = "visible"
                this.loading = false;
            } );

    }
    getDatiEsenzioni() {
        this.esenzioneStoreService.getListaEsenzioni()
            .subscribe( esenzioni => this.listaEsenzioni = esenzioni );
    }



    RevocaEsenzione( id: number ) {


        ( document.getElementById( "headrevoca" ).innerHTML ) = "Revoca Esenzione " + this.listaEsenzioneOperatore[id].codEsenzione + " - N. Protocollo " + this.listaEsenzioneOperatore[id].numProtocolloSogei + " - Id Esenzione " + this.listaEsenzioneOperatore[id].idEsenzione;
        ( document.getElementById( "codeserev" ).innerHTML ) = this.listaEsenzioneOperatore[id].codEsenzione;
        ( document.getElementById( "desceserev" ).innerHTML ) = this.listaEsenzioneOperatore[id].descEsenzione;
        ( document.getElementById( "datainirev" ).innerHTML ) = this.listaEsenzioneOperatore[id].inizioValidita;
        ( document.getElementById( "datascadrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].fineValidita;
        ( document.getElementById( "codfiscrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].codFiscaleBeneficiario;
        ( document.getElementById( "cognomerev" ).innerHTML ) = this.listaEsenzioneOperatore[id].cognomeBeneficiario;
        ( document.getElementById( "nomerev" ).innerHTML ) = this.listaEsenzioneOperatore[id].nomeBeneficiario;
        if ( this.listaEsenzioneOperatore[id].provinciaNascitaBeneficiario != null )
            ( document.getElementById( "comunenasrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaBeneficiario + '(' + this.listaEsenzioneOperatore[id].provinciaNascitaBeneficiario + ')';
        else
        ( document.getElementById( "comunenasrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaBeneficiario;
        ( document.getElementById( "datanasrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].dataNascitaBeneficiario;
        ( document.getElementById( "sessorev" ).innerHTML ) = this.listaEsenzioneOperatore[id].codSessoBeneficiario;

        ( document.getElementById( "notaintrev" ) as HTMLInputElement ).value = this.listaEsenzioneOperatore[id].nota;

        ( document.getElementById( "testoBlocco" ).innerHTML ) = this.messaggioRevoca;
        ( document.getElementById( "idrevoca" ).innerHTML ) = id.toString();


        if ( this.listaEsenzioneOperatore[id].codFiscaleBeneficiario != this.listaEsenzioneOperatore[id].titolare.codFiscale && this.listaEsenzioneOperatore[id].codFiscaleRichiedente != this.listaEsenzioneOperatore[id].titolare.codFiscale ) {
            $( ".sititolarerev" ).show();
            ( document.getElementById( "codfiscrevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.codFiscale;
            ( document.getElementById( "cognomerevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.cognome;
            ( document.getElementById( "nomerevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.nome;
            ( document.getElementById( "sessorevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.codSesso;
            ( document.getElementById( "datanasrevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.dataNascita;
            if ( this.listaEsenzioneOperatore[id].titolare.provinciaNascita != null )
                ( document.getElementById( "comunenasrevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.comuneNascita + '(' + this.listaEsenzioneOperatore[id].titolare.provinciaNascita + ')';
            else
        ( document.getElementById( "comunenasrevt" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.comuneNascita;
        } else {
            $( ".sititolarerev" ).hide();
            ( document.getElementById( "codfiscrevt" ).innerHTML ) = "";
            ( document.getElementById( "cognomerevt" ).innerHTML ) = "";
            ( document.getElementById( "nomerevt" ).innerHTML ) = "";
            ( document.getElementById( "sessorevt" ).innerHTML ) = "";
            ( document.getElementById( "datanasrevt" ).innerHTML ) = "";

            ( document.getElementById( "comunenasrevt" ).innerHTML ) = "";

        }
        this.getMessaggio( "MSG030" );
        $( "#motrev" ).removeClass( "inputError" );
        ( document.getElementById( "motrev" ) as HTMLInputElement ).value = "";


        ( document.getElementById( "id-name--1" ) as HTMLInputElement ).checked = false;
        this.PaginaRevocaEsenzione();

        if(this.listaEsenzioneOperatore[id].numProtocolloSogei==null){

            $(".revocaBlock")[2].style.display = "none";
        }else {
            $(".revocaBlock")[0].style.display = "";
            $(".revocaBlock")[1].style.display = "";
            $(".revocaBlock")[2].style.display = "";
        }

    }

    VisualizzaEsenzione( id: number ) {

        ( document.getElementById( "codesevis" ).innerHTML ) = this.listaEsenzioneOperatore[id].codEsenzione;
        ( document.getElementById( "descesevis" ).innerHTML ) = this.listaEsenzioneOperatore[id].descEsenzione;
        ( document.getElementById( "datainivis" ).innerHTML ) = this.listaEsenzioneOperatore[id].inizioValidita;
        ( document.getElementById( "datascadvis" ).innerHTML ) = this.listaEsenzioneOperatore[id].fineValidita;
        ( document.getElementById( "codfiscvis" ).innerHTML ) = this.listaEsenzioneOperatore[id].codFiscaleBeneficiario;
        ( document.getElementById( "cognomevis" ).innerHTML ) = this.listaEsenzioneOperatore[id].cognomeBeneficiario;
        ( document.getElementById( "nomevis" ).innerHTML ) = this.listaEsenzioneOperatore[id].nomeBeneficiario;
      if (this.listaEsenzioneOperatore[id].provinciaNascitaBeneficiario != null)
        ( document.getElementById( "comunenasvis" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaBeneficiario + '(' + this.listaEsenzioneOperatore[id].provinciaNascitaBeneficiario + ')';
      else
        ( document.getElementById( "comunenasvis" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaBeneficiario;
        ( document.getElementById( "datanasrev" ).innerHTML ) = this.listaEsenzioneOperatore[id].dataNascitaBeneficiario;
        ( document.getElementById( "sessorev" ).innerHTML ) = this.listaEsenzioneOperatore[id].codSessoBeneficiario;
        if (this.listaEsenzioneOperatore[id].codStato == 'R'){
            $(".motrevno").show();
            ( document.getElementById( "notaintvis" ) as HTMLInputElement ).value = this.listaEsenzioneOperatore[id].motivoRevoca;
        }
        else{
            $(".motrevno").hide();
        ( document.getElementById( "notaintvis" ) as HTMLInputElement ).value = "";
        }


        if (this.listaEsenzioneOperatore[id].codFiscaleBeneficiario != this.listaEsenzioneOperatore[id].titolare.codFiscale && this.listaEsenzioneOperatore[id].codFiscaleRichiedente != this.listaEsenzioneOperatore[id].titolare.codFiscale){
            $( ".sititolarevis" ).show();
        ( document.getElementById( "codfiscvist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.codFiscale;
        ( document.getElementById( "cognomevist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.cognome;
        ( document.getElementById( "nomevist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.nome;
        ( document.getElementById( "sessovist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.codSesso;
        ( document.getElementById( "datanasvist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.dataNascita;
        if (this.listaEsenzioneOperatore[id].titolare.provinciaNascita != null)
            ( document.getElementById( "comunenasvist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.comuneNascita + '(' + this.listaEsenzioneOperatore[id].titolare.provinciaNascita + ')';
          else
        ( document.getElementById( "comunenasvist" ).innerHTML ) = this.listaEsenzioneOperatore[id].titolare.comuneNascita;
        }else{
            $( ".sititolarevis" ).hide();
            ( document.getElementById( "codfiscvist" ).innerHTML ) = "";
        ( document.getElementById( "cognomevist" ).innerHTML ) = "";
        ( document.getElementById( "nomevist" ).innerHTML ) = "";
        ( document.getElementById( "sessovist" ).innerHTML ) = "";
        ( document.getElementById( "datanasvist" ).innerHTML ) = "";

        ( document.getElementById( "comunenasvist" ).innerHTML ) = "";

    }
        if (this.listaEsenzioneOperatore[id].codFiscaleBeneficiario != this.listaEsenzioneOperatore[id].codFiscaleRichiedente){
            $( ".sidichiarantevis" ).show();
        ( document.getElementById( "codfiscvisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].codFiscaleRichiedente;
        ( document.getElementById( "cognomevisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].cognomeRichiedente;
        ( document.getElementById( "nomevisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].nomeRichiedente;
        ( document.getElementById( "sessovisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].codSessoRichiedente;
        ( document.getElementById( "datanasvisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].dataNascitaRichiedente;
        if (this.listaEsenzioneOperatore[id].provinciaNascitaRichiedente != null)
            ( document.getElementById( "comunenasvisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaRichiedente + '(' + this.listaEsenzioneOperatore[id].provinciaNascitaRichiedente + ')';
          else
        ( document.getElementById( "comunenasvisd" ).innerHTML ) = this.listaEsenzioneOperatore[id].comuneNascitaRichiedente;
        }else{
            $( ".sidichiarantevis" ).hide();
            ( document.getElementById( "codfiscvisd" ).innerHTML ) = "";
        ( document.getElementById( "cognomevisd" ).innerHTML ) = "";
        ( document.getElementById( "nomevisd" ).innerHTML ) = "";
        ( document.getElementById( "sessovisd" ).innerHTML ) = "";
        ( document.getElementById( "datanasvisd" ).innerHTML ) = "";

        ( document.getElementById( "comunenasvisd" ).innerHTML ) = "";

    }
        this.PaginaVisualizzaEsenzione();
	}
    Aprinota( id: number ) {

        ( document.getElementById( "notaintmes" ).innerHTML ) = this.listaEsenzioneOperatore[id].nota;
    }

    getEsenzioniAsl() {
        this.esenzioneStoreService.getEsenzioniAsl()
            .subscribe( getoperatore => {
                this.esenzioneasl = getoperatore
                this.loading = false;
            },
            errorCode => {
                this.statusCode = errorCode.status;
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                this.SelezioneMessaggio( "esitoko" );
                $("#filtriPC").hide();
                $("#filtriSM").hide();
                this.loading = false;
            }
            );
    }





    EseguiRevoca() {
        if ( ( document.getElementById( "headrevoca" ).innerHTML ).includes( "null" ) ) {
            this.loading = true;
            $( "#motrev" ).removeClass( "inputError" );
            this.confermarev = true;
            let revocaesenzione = new RevocaEsenzioneCertificata();

            var idpass = ( document.getElementById( "idrevoca" ).innerHTML );
            var motivo = ( document.getElementById( "motrev" ) as HTMLInputElement ).value;
            ( document.getElementById( "motrev" ) as HTMLInputElement ).readOnly = true;
            $( ".besitorev" ).hide();
            $( ".besitorevfine" ).show();

            revocaesenzione.codiceEsenzione = this.listaEsenzioneOperatore[idpass].codEsenzione;
            revocaesenzione.codiceFiscaleAssistito = this.listaEsenzioneOperatore[idpass].codFiscaleBeneficiario;
            revocaesenzione.dataInizioValidita = this.listaEsenzioneOperatore[idpass].inizioValidita;


            revocaesenzione.motivoRevocaEsenzione = motivo;
            var notarev = ( document.getElementById( "notaintrev" ) as HTMLInputElement ).value;
            revocaesenzione.notaInternaRevoca = notarev;
            ( document.getElementById( "notaintrev" ) as HTMLInputElement ).readOnly = true;
            this.autocertificazioneStoreService.revocaEsenzioneCertificata( revocaesenzione )
                .subscribe( successCode => {
                    this.statusCode = successCode.status;
                    var str = successCode._body;
                    var objmsg = JSON.parse( str );
                    if ( this.statusCode == 200 ) {
                        switch ( objmsg.codeAura ) {
                            case "7499":
                                ( document.getElementById( "messaggiorevocaok" ).innerHTML ) = objmsg.message ;
                                this.SelezioneMessaggio( "esitorevocaok" );
                                this.loading = false;
                                break;
                            case "0003":
                                ( document.getElementById( "messaggiorevocaver" ).innerHTML ) = objmsg.message;
                                this.SelezioneMessaggio( "esitorevocaver" );
                                this.loading = false;
                                break;
                            default:
                                ( document.getElementById( "messaggiorevocagenerico" ).innerHTML ) = objmsg.message;
                                this.SelezioneMessaggio( "esitorevocagenerico" );
                                this.loading = false;
                                break;
                        }
                    }
                },
                errorCode => {
                    this.statusCode = errorCode.status;
                    if ( this.statusCode == 400 ) {
                        var str = errorCode._body;
                        var objmsg = JSON.parse( str );
                        ( document.getElementById( "messaggiorevocako" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitorevocako" );
                        this.loading = false;
                    }
                    else {
                        ( document.getElementById( "messaggiorevocagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        this.SelezioneMessaggio( "esitorevocagenerico" );
                        this.loading = false;
                    }
                }
                );
        } else {
            this.loading = true;
            $( "#motrev" ).removeClass( "inputError" );
            this.confermarev = true;
            let revocaesenzione = new RevocaEsenzione();
            var idpass = ( document.getElementById( "idrevoca" ).innerHTML );
            var motivo = ( document.getElementById( "motrev" ) as HTMLInputElement ).value;
            ( document.getElementById( "motrev" ) as HTMLInputElement ).readOnly = true;
            $( ".besitorev" ).hide();
            $( ".besitorevfine" ).show();
            revocaesenzione.idEsenzione = this.listaEsenzioneOperatore[idpass].idEsenzione;
            revocaesenzione.blocco = $( ".toggle--off" ).css( 'display' ) == "none";
            revocaesenzione.motivoRevocaEsenzione = motivo;
            var notarev = ( document.getElementById( "notaintrev" ) as HTMLInputElement ).value;
            revocaesenzione.notaInternaRevoca = notarev;
            ( document.getElementById( "notaintrev" ) as HTMLInputElement ).readOnly = true;
            this.autocertificazioneStoreService.revocaAutocertificazioneASL( revocaesenzione )
                .subscribe( successCode => {
                    this.statusCode = successCode.status;
                    var str = successCode._body;
                    var objmsg = JSON.parse( str );
                    if ( this.statusCode == 200 ) {
                        switch ( objmsg.codeAura ) {
                            case "7199":
                                ( document.getElementById( "messaggiorevocaok" ).innerHTML ) = objmsg.message;
                                this.SelezioneMessaggio( "esitorevocaok" );
                                this.loading = false;
                                break;
                            case "0003":
                                ( document.getElementById( "messaggiorevocaver" ).innerHTML ) = objmsg.message;
                                this.SelezioneMessaggio( "esitorevocaver" );
                                this.loading = false;
                                break;
                            default:
                                ( document.getElementById( "messaggiorevocagenerico" ).innerHTML ) = objmsg.message;

                                this.SelezioneMessaggio( "esitorevocagenerico" );
                                this.loading = false;
                                break;
                        }
                    }
                },
                errorCode => {
                    this.statusCode = errorCode.status;
                    if ( this.statusCode == 400 ) {
                        var str = errorCode._body;
                        var objmsg = JSON.parse( str );
                        ( document.getElementById( "messaggiorevocako" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitorevocako" );
                        this.loading = false;
                    }
                    else {
                        ( document.getElementById( "messaggiorevocagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        this.SelezioneMessaggio( "esitorevocagenerico" );
                        this.loading = false;
                    }
                }
                );
        }

    }

    EseguiRevocaTotale() {
        this.loading = true;
        this.confermarev = true;
        $( "#motrevtot" ).removeClass( "inputError" );
        let revocaesenzione = new RevocaEsenzione();

        var motivo = ( document.getElementById( "motrevtot" ) as HTMLInputElement ).value;
        revocaesenzione.motivoRevocaEsenzione = motivo;
        for ( var idpass = 0; idpass < this.listaEsenzioneOperatoreRevoca.length; idpass++ ) {

        revocaesenzione.idEsenzione = this.listaEsenzioneOperatoreRevoca[idpass].idEsenzione;


        ( document.getElementById( "messaggiorevocatotgenerico" ).innerHTML ) = "Elenco messaggi revoca esenzioni : " + "<br>"
        this.autocertificazioneStoreService.revocaEsenzioneTotaliTitolare( revocaesenzione, this.listaEsenzioneOperatoreRevoca[idpass].numProtocolloSogei)
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                if ( this.statusCode == 200 ) {
                            ( document.getElementById( "messaggiorevocatotgenerico" ).innerHTML ) = ( document.getElementById( "messaggiorevocatotgenerico" ).innerHTML ) +  objmsg.message + "<br>";
                            this.SelezioneMessaggio( "esitorevocatotgenerico" );
                            ( document.getElementById( "motrevtot" ) as HTMLInputElement ).readOnly = true;

                            this.loading = false;
                }
            },
            errorCode => {
                this.statusCode = errorCode.status;
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                if ( this.statusCode == 400 ) {
                    ( document.getElementById( "messaggiorevocatotko" ).innerHTML ) = objmsg.message;
                    this.SelezioneMessaggio( "esitorevocatotko" );
                    ( document.getElementById( "motrevtot" ) as HTMLInputElement ).readOnly = false;
                    this.loading = false;
                }
                else {
                    ( document.getElementById( "messaggiorevocatotgenerico" ).innerHTML ) = ( document.getElementById( "messaggiorevocatotgenerico" ).innerHTML ) +  objmsg.message + "<br>";
                    this.SelezioneMessaggio( "esitorevocatotgenerico" );
                    ( document.getElementById( "motrevtot" ) as HTMLInputElement ).readOnly = true;

                    this.loading = false;
                }
            }
            );
        }
    }

    getTitoloDich() {
        this.titolodichStoreService.getListaTitoloDich()
            .subscribe( titolodich => this.listaTitoloDich = titolodich );
    }

    getDataRich(codEsenzione: string) {
        this.datarichStoreService.getDataRich(codEsenzione)
            .subscribe( datarich => this.datarich = datarich );
    }

        getListaParametri() {
            this.parametroStoreService.getListaParametri()
                .subscribe(parametro => this.listaParametri=parametro);
        }



    getMessaggio( codice: string ) {
        this.parametroStoreService.getMessaggio( codice )
            .subscribe( messaggio => this.messaggio = messaggio[0].testo );
    }

    getMessaggioRevoca( codice: string ) {
        this.parametroStoreService.getMessaggio( codice )
            .subscribe( messaggio => this.messaggioRevoca = this.messaggioRevoca + " " + messaggio[0].testo );
    }



    onregistraEsenzioneFormSubmitFam() {
        this.loading = true;
        // this.preProcessConfigurations();
        let autocertificazionefam = new AutocertificazioneASL();
        autocertificazionefam.flagconferma = (document.getElementById( "flagconferma" ).innerHTML);
        var idpassdic = ( document.getElementById( "idassistitodic" ).innerHTML );
        var idpassben = ( document.getElementById( "idassistitoben" ).innerHTML );
        var idpasstit = ( document.getElementById( "idassistitotit" ).innerHTML );
        var e = ( document.getElementById( "codEsenzioneFam" ) ) as HTMLSelectElement;
        var sel = e.selectedIndex;
        var selEsenzione = ""
        var selEsenzionetext = ""
        if ( sel !== -1 ) {
            var opt = e.options[sel];
            selEsenzione = ( <HTMLOptionElement>opt ).value;
            selEsenzionetext = ( <HTMLOptionElement>opt ).text;
        };

        if ( selEsenzione != null ) {
            autocertificazionefam.codEsenzioneBeneficiario = selEsenzione;
        }
        else {
            autocertificazionefam.codEsenzioneBeneficiario = "";
        };

        var e = ( document.getElementById( "codtit" ) ) as HTMLSelectElement;
        var sel = e.selectedIndex;
        var selTitolo = ""
        var selTitolotext = ""
        if ( sel !== -1 ) {
            var opt = e.options[sel];
            selTitolo = ( <HTMLOptionElement>opt ).value;
            selTitolotext = ( <HTMLOptionElement>opt ).text;
        };

        if ( selTitolo != null ) {
            autocertificazionefam.codTitolo = selTitolo;
            this.messaggioTitolo = selTitolotext;
        }
        else {
            autocertificazionefam.codTitolo = "";
            this.messaggioTitolo = "";
        };
        autocertificazionefam.codFiscaleDichiarante = ( document.getElementById( "codFiscalediclabel" ).innerHTML );
        autocertificazionefam.cognomeDichiarante = ( document.getElementById( "cognomediclabel" ).innerHTML );
        autocertificazionefam.nomeDichiarante = ( document.getElementById( "nomediclabel" ).innerHTML );
        autocertificazionefam.sessoDichiarante = ( document.getElementById( "sessoDiclabel" ).innerHTML );
        autocertificazionefam.dataNascitaDichiarante = ( document.getElementById( "datanasDiclabel" ).innerHTML );
        autocertificazionefam.comuneNascitaDichiarante = ( document.getElementById( "comuneDiclabel" ).innerHTML );
        autocertificazionefam.provinciaNascitaDichiarante = ( document.getElementById( "provinciaDiclabel" ).innerHTML );
        autocertificazionefam.codFiscaleBeneficiario = ( document.getElementById( "codFiscalebenlabel" ).innerHTML );
        autocertificazionefam.cognomeBeneficiario = ( document.getElementById( "cognomebenlabel" ).innerHTML );
        autocertificazionefam.nomeBeneficiario = ( document.getElementById( "nomebenlabel" ).innerHTML );
        autocertificazionefam.sessoBeneficiario = ( document.getElementById( "sessobenlabel" ).innerHTML );
        autocertificazionefam.dataNascitaBeneficiario = ( document.getElementById( "datanasbenlabel" ).innerHTML );
        autocertificazionefam.comuneNascitaBeneficiario = ( document.getElementById( "comunebenlabel" ).innerHTML );
        autocertificazionefam.provinciaNascitaBeneficiario = ( document.getElementById( "provinciabenlabel" ).innerHTML );


        autocertificazionefam.dataInizioValidita = this.datarich[0].inizioValidita;

        autocertificazionefam.dataScadenzaValidita =  ( document.getElementById( "fineVal" ) as HTMLInputElement ).value;
        autocertificazionefam.datascadenzaSSN =  ( document.getElementById( "dataSSN" ).innerHTML );

        autocertificazionefam.sititolare="";
        if  ( ( document.getElementById( "etitolareSib" ) as HTMLInputElement ).checked ) {
            autocertificazionefam.sititolare="Beneficiario";
            autocertificazionefam.nomeTitolare=autocertificazionefam.nomeBeneficiario;
            autocertificazionefam.cognomeTitolare = autocertificazionefam.cognomeBeneficiario;
            autocertificazionefam.codFiscaleTitolare = autocertificazionefam.codFiscaleBeneficiario;
            autocertificazionefam.sessoTitolare = autocertificazionefam.sessoBeneficiario;
            autocertificazionefam.dataNascitaTitolare = autocertificazionefam.dataNascitaBeneficiario;
            autocertificazionefam.comuneNascitaTitolare = autocertificazionefam.comuneNascitaBeneficiario;
            autocertificazionefam.provinciaNascitaTitolare = autocertificazionefam.provinciaNascitaBeneficiario;
        }
        else if (( document.getElementById( "etitolareSif" ) as HTMLInputElement ).checked )
        {
        	if ( this.dichiaranteFR ) {
	          	autocertificazionefam.codFiscaleTitolareFuoriRegione = ( document.getElementById( "codFiscalediclabel" ).innerHTML );
	          	autocertificazionefam.codFiscaleTitolare = null;
          	}
	            autocertificazionefam.sititolare="Dichiarante";
	            autocertificazionefam.nomeTitolare=autocertificazionefam.nomeDichiarante;
	            autocertificazionefam.cognomeTitolare = autocertificazionefam.cognomeDichiarante;
	            autocertificazionefam.codFiscaleTitolare = autocertificazionefam.codFiscaleDichiarante;
	            autocertificazionefam.sessoTitolare = autocertificazionefam.sessoDichiarante;
	            autocertificazionefam.dataNascitaTitolare = autocertificazionefam.dataNascitaDichiarante;
	            autocertificazionefam.comuneNascitaTitolare = autocertificazionefam.comuneNascitaDichiarante;
	            autocertificazionefam.provinciaNascitaTitolare = autocertificazionefam.provinciaNascitaDichiarante;

        }
        else if  ( ( document.getElementById( "etitolareNof" ) as HTMLInputElement ).checked )
            {
            autocertificazionefam.sititolare="TITOLARE";
            autocertificazionefam.codFiscaleTitolare = ( document.getElementById( "codFiscaletitlabel" ).innerHTML );
            autocertificazionefam.cognomeTitolare = ( document.getElementById( "cognometitlabel" ).innerHTML );
            autocertificazionefam.nomeTitolare = ( document.getElementById( "nometitlabel" ).innerHTML );
            autocertificazionefam.sessoTitolare = ( document.getElementById( "sessotitlabel" ).innerHTML );
            autocertificazionefam.dataNascitaTitolare = ( document.getElementById( "datanastitlabel" ).innerHTML );
            autocertificazionefam.comuneNascitaTitolare = ( document.getElementById( "comunetitlabel" ).innerHTML );
            autocertificazionefam.provinciaNascitaTitolare = ( document.getElementById( "provinciatitlabel" ).innerHTML );
            }


        autocertificazionefam.dataRichiesta = this.datarich[0].dataRichiesta;
        autocertificazionefam.nota = ( document.getElementById( "notaint" ) as HTMLInputElement ).value;

        if ( autocertificazionefam.nota.length > 500 ) {
            autocertificazionefam.nota = autocertificazionefam.nota.substring( 0, 499 );
        }

       if ( this.titolareFR )  {
	           autocertificazionefam.codFiscaleTitolareFuoriRegione = ( document.getElementById( "codFiscaletitlabel" ).innerHTML );
	           autocertificazionefam.codFiscaleTitolare = null;
           }
          if ( this.dichiaranteFR ) {
	          	autocertificazionefam.codFiscaleDichiaranteFuoriRegione = ( document.getElementById( "codFiscalediclabel" ).innerHTML );
	          	autocertificazionefam.codFiscaleDichiarante = null;
          	}

        this.autocertificazioneStoreService.createAutocertificazioneASL( autocertificazionefam )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                this.loading = false;
                if ( this.statusCode == 200 ) {
                    switch ( objmsg.codeAura ) {
                    case "7099":
                        this.loading = false;
                        ( document.getElementById( "messaggiook" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitook" );
                        ( document.getElementById( "newEsenzione" ).innerHTML ) = objmsg.idEsenzione;
                        break;
                    case "0003":
                        this.loading = false;
                        ( document.getElementById( "messaggiover" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitover" );
                        break;
                    case "E1020":
                        this.loading = false;
                        ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitoko" );
                        this.FocusInput("codice esenzione");
                        break;
                    case "E1019":
                        this.loading = false;
                        ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitoko" );
                        this.FocusInput("codice esenzione");
                        break;
                    case "E1042":
                        this.loading = false;
                        ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitoko" );
                        this.FocusInput("codice esenzione");
                        break;
                    case "7030":
                        this.loading = false;
                        ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitoko" );
                        this.FocusInput("codice esenzione");
                        break;
                    case "7023":
                        this.loading = false;
                        ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                        this.SelezioneMessaggio( "esitoko" );
                        this.FocusInput("codice esenzione");
                        break;
                    default:
                        this.loading = false;
                        ( document.getElementById( "messaggiogenerico" ).innerHTML ) = objmsg.message;
                        //this.koAura="si"
                        this.SelezioneMessaggio( "esitogenerico" );
                        break;
                    }
                }

            },
            errorCode => {
                this.statusCode = errorCode.status;
                this.loading = false;
                if ( this.statusCode == 400 ) {
                    var str = errorCode._body;
                    var objmsg = JSON.parse( str );
                    ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                    this.SelezioneMessaggio( "esitoko" );
                    var stringaMessaggio = objmsg.message;
                    var nuovaStringa = stringaMessaggio.replace( "La ricerca del ", "" );
                    var nuovaStringa2 = nuovaStringa.replace( " non può procedere. E' necessario inserire, alternativamente, codice fiscale, oppure cognome, nome e data di nascita.", "" );
                    this.FocusInput( nuovaStringa2 );
                    var stringaErroreIncompatibilita = "ATTENZIONE! La richiesta di esenzione non può essere concessa:  Il Beneficiario possiede esenzioni incompatibili con quella richiesta!";
                    if ( objmsg.message == stringaErroreIncompatibilita ) {
                        this.FocusInput( "codice esenzione" );
                    }

                    this.loading = false;
                }
                else if (this.statusCode == 412)
                {
                    var str = errorCode._body;
                    var objmsg = JSON.parse( str );
                    ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                    this.SelezioneMessaggio( "esitoko" );
                    (document.getElementById( "flagconferma" ).innerHTML) = "0";
                    this.loading = false;
                }
                else {
                    this.loading = false;

                    ( document.getElementById( "messaggioko" ).innerHTML ) = objmsg.message;
                    this.SelezioneMessaggio( "esitoko" );



                    this.loading = false;
                }
            }
            );


        ( document.getElementById( "codesemes" ).innerHTML ) = selEsenzione;
;
        if ( selEsenzionetext != "" ) {
            ( document.getElementById( "descesemes" ).innerHTML ) = this.EsenzioneDescFam;
        }

        ( document.getElementById( "datainimes" ).innerHTML ) = autocertificazionefam.dataInizioValidita;
        ( document.getElementById( "datafinemes" ).innerHTML ) = autocertificazionefam.dataScadenzaValidita;


        ( document.getElementById( "nomebenmes" ).innerHTML ) = autocertificazionefam.nomeBeneficiario;
        ( document.getElementById( "cognomebenmes" ).innerHTML ) = autocertificazionefam.cognomeBeneficiario;
        ( document.getElementById( "codicefiscbenmes" ).innerHTML ) = autocertificazionefam.codFiscaleBeneficiario;
        ( document.getElementById( "sessobenmes" ).innerHTML ) = autocertificazionefam.sessoBeneficiario;

        ( document.getElementById( "datanasbenmes" ).innerHTML ) = autocertificazionefam.dataNascitaBeneficiario;

        if ( autocertificazionefam.comuneNascitaBeneficiario == "" ) {
            ( document.getElementById( "comunenasbenmes" ).innerHTML ) = "";
        }
        else {
            if (autocertificazionefam.provinciaNascitaBeneficiario != null)
                ( document.getElementById( "comunenasbenmes" ).innerHTML ) = autocertificazionefam.comuneNascitaBeneficiario
                    + "(" + autocertificazionefam.provinciaNascitaBeneficiario + ")";
                    else
                        ( document.getElementById( "comunenasbenmes" ).innerHTML ) = autocertificazionefam.comuneNascitaBeneficiario;
        }

    }

    FocusInput( qualeInput: string ) {
        $( "#codFiscaledic" ).removeClass( "inputError" );
        $( "#codFiscaleben" ).removeClass( "inputError" );
        $( "#cognomeben" ).removeClass( "inputError" );
        $( "#datanasBen" ).removeClass( "inputError" );
        $( "#nomeben" ).removeClass( "inputError" );
        $( "#nomedic" ).removeClass( "inputError" );
        $( "#datanasDic" ).removeClass( "inputError" );
        $( "#cognomedic" ).css( "color", "" );
        switch ( qualeInput ) {
            case "dichiarante":
                $( "#codFiscaledic" ).addClass( "inputError" );
                $( "#nomedic" ).addClass( "inputError" );
                $( "#datanasDic" ).addClass( "inputError" );
                $( "#cognomedic" ).addClass( "inputError" );
                break;
            case "beneficiario":
                $( "#codFiscaleben" ).addClass( "inputError" );
                $( "#nomeben" ).addClass( "inputError" );
                $( "#datanasBen" ).addClass( "inputError" );
                $( "#cognomeben" ).addClass( "inputError" );
                break;
            case "Motivo della revoca":
                $( "#motrev" ).addClass( "inputError" );
                document.getElementById( "motrev" ).focus();
                break;
        }
    }

}
