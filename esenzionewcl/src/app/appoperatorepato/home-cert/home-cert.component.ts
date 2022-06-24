import { Component, Input, OnInit, Output, Directive, ElementRef, ViewChild, OnDestroy } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { parametroStoreService } from 'app/parametro/parametro.service';
import { parametro } from 'app/parametro/parametro';
import { FormControl, FormGroup, Validators, AbstractControl, FormBuilder } from '@angular/forms';
import { Router, Params, RouterModule, ActivatedRoute } from '@angular/router';
import { NgIf, NgSwitch } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';//questo
import { EventEmitter } from "events";
import * as $ from 'jquery';
import { ConfigService } from "app/services/config.service";
import { ricercaAssistito } from 'app/appoperatorepato/ricercaAssistito/ricercaAssistito.component';
import { Assistito } from "app/assistito/assistito";
import { statodocumento } from "app/statodocumento/statodocumento";
import { CodiceEsenzionePatologia } from "app/esenzione/codiceesenzionepatologia";
import { statodocumentoStoreService } from "app/statodocumento/statodocumento.service";
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import { RicercaCertificatoPatologia } from "app/esenzione/ricercacertificatopatologia";
import { certificato } from "app/esenzione/certificato";
import { DettaglioCertificato } from "app/appoperatorepato/dettaglioCertificato/DettaglioCertificato.component";
import { CertificatoPatologia } from "app/esenzione/CertificatoPatologia";
import { esenzioneAssistito } from "app/esenzione/esenzioneAssistito";
import { FiltriDettaglioEsenzione } from "app/esenzione/FiltriDettaglioEsenzione";
import { StoricoEsenzione } from "app/esenzione/storicoEsenzione";
import { Stato } from "app/esenzione/Stato";
import {Subject} from 'rxjs/Subject';
import { TabService } from "app/appoperatorepato/home/tab.service";
import { Subscription } from "rxjs/Subscription";
import { UserInfo } from 'app/assistito/userinfo';
import { OrderPipe } from 'ngx-order-pipe';
declare var $;
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-operatorepato-home-cert',
    templateUrl: './home-cert.component.html',


} )


export class HomeAppOperatorePatoCert implements OnInit, OnDestroy{
    assistito: Assistito[] = [];
    on : boolean = false;
    listacertificati : certificato[] = [];
    listaesenzioni : esenzioneAssistito[] = [];
    loading = false;
    @ViewChild('dettaglioCertificato') dettCert : DettaglioCertificato;
    @ViewChild('ricercaAssistito') ricAss : ricercaAssistito;
    @ViewChild('codfiscben') codfiscben: ElementRef;
    @ViewChild('cognomeben') cognomeben: ElementRef;
    @ViewChild('nomeben') nomeben: ElementRef;
    assistitoBen : string;
    statusCode: number;
    storicoEsenzione: StoricoEsenzione[] = [];
    public pagina: number = 1;
    private numerototpagine: Array<number>;
    public orderBy: string = "beneficiarionumpratica";
    public asc: boolean = true;
    private lunghezzaarray: number;
    parametroTmp: parametro[] = [];
    selectedBeneficiario: number;
    @Input() notifier: Subject<string> = new Subject();
    subscription: Subscription;
    selectedPatologia: CodiceEsenzionePatologia;

    configPatologia = {
        displayKey: "label", //if objects array passed which key to be displayed defaults to description
        search:true, //true/false for the search functionlity defaults to false,
        height: '300px', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
        placeholder:'TUTTE', // text to be displayed when no item is selected defaults to Select,
        //customComparator: ()=>{} // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
        //limitTo: 0, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
        moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
        noResultsFound: 'Patologia non trovata', // text to be displayed when no items are found while searching
        searchPlaceholder:'cerca' // label thats displayed in search input,
        //searchOnKey: 'name' // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
    }

    constructor( private http: Http,
        private router: Router,
        private route: ActivatedRoute,
        private assistitoStoreService: AssistitoStoreService,
        private statodocumentoStoreService: statodocumentoStoreService,
        private esenzioneStoreService: EsenzioneStoreService,
        private parametroStoreService: parametroStoreService,
        private config: ConfigService,
        private orderPipe: OrderPipe,
        private tabService: TabService){
        }

    ngOnInit() {
        var currentTab = this.tabService.getCurrentTab();
        // this.patologiaSelected = "TUTTE";
        let utente: UserInfo = this.assistitoStoreService.getUser();

        utente.ruoli.forEach(ruolo => {
            if(ruolo.codiceRuolo === 'MEDICO_SPECIALISTA_CAS') {
                let tab: HTMLElement = document.getElementById("certTab") as HTMLElement;
                tab.click();
            }
        });

        this.ricAss = new ricercaAssistito(this.http,this.assistitoStoreService, this.router, this.route);
        $( ".eleese" ).show();
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".elecertris" ).hide();
        $( ".eleeseris" ).hide();
        $( ".elericcertris" ).hide();
        $( ".eleassistiti" ).hide();
        this.getParam( "ELEMENTI_RICERCA" );
        this.subscription = this.notifier.subscribe(
                data => this.resetForm(data));
    }

    ngOnDestroy(){
        this.subscription.unsubscribe();
    }

    resetForm( form ) {

        var e = ( document.getElementById( "codEsenzionePatologiafiltro" ) ) as HTMLSelectElement
        e.selectedIndex = 0;
        this.resetCercaBeneficiario();

        this.assistito = [];
        if (form=="#certificati") {
            $( ".esitoricercaok" ).hide();
            $( ".esitoricercako" ).hide();
            $( ".esitoricercagenerico" ).hide();
            $( ".elecertris" ).hide();
            $( ".eleeseris" ).hide();
            $( ".elericcertris" ).hide();
            $( ".eleassistiti" ).hide();

        } if (form=="#esenzioni") {
            $( ".esitoricercaok" ).hide();
            $( ".esitoricercako" ).hide();
            $( ".esitoricercagenerico" ).hide();
            $( ".elecertris" ).hide();
            $( ".eleeseris" ).hide();
            $( ".elericcertris" ).hide();
            $( ".eleassistiti" ).hide();

        }
        if (form=="#ricercarichiestacertificati") {
            $( ".esitoricercaok" ).hide();
            $( ".esitoricercako" ).hide();
            $( ".esitoricercagenerico" ).hide();
            $( ".elecertris" ).hide();
            $( ".eleeseris" ).hide();
            $( ".elericcertris" ).hide();
            $( ".eleassistiti" ).hide();

        }
    }

    Cerca() {
        $( ".elecertris" ).hide();
        $( ".eleeseris" ).hide();
        $( ".elericcertris" ).hide();
        $( ".eleassistiti" ).hide();
erca
        let a: string;
        this.loading = true;
        let ricesecit = new CittadinoRicerca();
        this.selectedBeneficiario = null;
        ricesecit.codFiscale = "";
        ricesecit.cognome = "";
        ricesecit.nome = "";
        ricesecit.dataDiNascita = "";
        ricesecit.ruolo ="";
        this.assistito = [];
        ((document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value="";

        if ( ( ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value ) != "" ) {
            ricesecit.codFiscale = ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value.toUpperCase();
        }
        else {
            ricesecit.cognome = ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
            ricesecit.nome = ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
        }
        ricesecit.ruolo = "Ricerca";
        if (ricesecit.codFiscale=="" &&  ricesecit.cognome =="" && ricesecit.nome=="")
        this.ApplicaFiltroCertificatiFirst();

        else {
        this.assistitoStoreService.findCittadiniPato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] || body.data == null ) {
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        $( ".eleassistiti" ).hide();
                        this.loading = false;
                        }
                        else if (body.data.length==1){

                            (document.getElementById( "idassistitobenric" ) as HTMLInputElement ).value=body.data[0].idAura;
                            ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value = body.data[0].codFiscale;
                            ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value = body.data[0].cognome;
                            ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value = body.data[0].nome;
                            $( ".eleassistiti" ).hide();
                            this.ApplicaFiltroCertificatiFirst();
                        }
                        else{
                            ( document.getElementById( "numtrovati" ).innerHTML ) = body.data.length;
                            ( document.getElementById( "datiassistito" ).innerHTML ) = ricesecit.cognome + " " + ricesecit.nome;
                            $( ".esitoricercaok" ).hide();
                            $( ".esitoricercako" ).hide();
                            $( ".esitoricercagenerico" ).hide();
                            $( ".eleassistiti" ).show();
                            $( ".mesric" ).hide();
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
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        $( ".eleassistiti" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                    $( ".esitoricercagenerico" ).show();
                    $( ".esitoricercaok" ).hide();
                    $( ".esitoricercako" ).hide();
                    $( ".elecertris" ).hide();
                    $( ".eleeseris" ).hide();
                    $( ".elericcertris" ).hide();
                    $( ".eleassistiti" ).hide();
                    this.loading = false;
                    break;
                }
            } )
        }
    }

    ScegliCittadino() {
        $( ".elecertris" ).hide();
        $( ".eleeseris" ).hide();
        $( ".elericcertris" ).hide();

        let a: string;
        this.loading = true;
        let ricesecit = new CittadinoRicerca();
        this.selectedBeneficiario = null;
        ricesecit.codFiscale = "";
        ricesecit.cognome = "";
        ricesecit.nome = "";
        ricesecit.dataDiNascita = "";
        ricesecit.ruolo ="";
        ((document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value="";

        if ( ( ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value ) != "" ) {
            ricesecit.codFiscale = ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value.toUpperCase();
        }
        else {
            ricesecit.cognome = ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
            ricesecit.nome = ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value.toUpperCase();
        }
        ricesecit.ruolo = "Ricerca";

        this.assistitoStoreService.findCittadiniPato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] || body.data == null ) {
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".elericcertris" ).hide();
                        $( ".eleeseris" ).hide();
                        this.loading = false;
                        }
                        else {

                            (document.getElementById( "idassistitobenric" ) as HTMLInputElement ).value=body.data[0].idAura;
                            ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value = body.data[0].codFiscale;
                            ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value = body.data[0].cognome;
                            ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value = body.data[0].nome;
                            $( ".elecertris" ).hide();
                            $( ".eleeseris" ).hide();
                            $( ".elericcertris" ).hide();
                            this.ApplicaFiltroCertificatiFirst();
                        }
                }
            },
            errorCode => {
                this.statusCode = errorCode.status;
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                switch ( this.statusCode ) {
                    case 400:
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                    $( ".esitoricercagenerico" ).show();
                    $( ".esitoricercaok" ).hide();
                    $( ".esitoricercako" ).hide();
                    $( ".elecertris" ).hide();
                    $( ".eleeseris" ).hide();
                    $( ".elericcertris" ).hide();
                    this.loading = false;
                    break;
                }
            } )
    }

    selectBeneficiario(i:number) {
        this.selectedBeneficiario = i;
        ( document.getElementById( "codfiscbenfiltro" ) as HTMLInputElement ).value = this.assistito[i].codFiscale;
        ( document.getElementById( "cognomebenfiltro" ) as HTMLInputElement ).value = this.assistito[i].cognome;
        ( document.getElementById( "nomebenfiltro" ) as HTMLInputElement ).value = this.assistito[i].nome;
        ( document.getElementById( "idassistitobenric" ) as HTMLInputElement ).value = this.assistito[i].idAura;
    }


    key: string = '';
    reverse: boolean = true;
    sort1( key ) {
        this.key = key;
        this.reverse = !this.reverse;
        this.listaesenzioni = this.orderPipe.transform(this.listaesenzioni, this.key, this.reverse);
    }

    sort( key ) {
        this.pagina = 1;
        this.key = key;

        this.reverse = !this.reverse;

        this.ApplicaFiltroCertificati();
        this.listaesenzioni = this.orderPipe.transform(this.listaesenzioni, this.key, this.reverse);
    }
    p: number = 1;

    ApplicaFiltroCertificatiFirst(){
        this.pagina = 1;
        $( ".esitoricercako" ).hide();
        $( ".esitoricercaok" ).hide();
        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = "";
        var cognomebeneficiario = ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value;
        var nomebeneficiario = ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value;
        var codfiscbenfiltro = ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value;
        var idAuraBeneficiario = ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value;
        if (cognomebeneficiario=="" || nomebeneficiario=="" ||codfiscbenfiltro==""){
        ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value="";
        }
        this.ApplicaFiltroCertificati();


    }

    ApplicaFiltroCertificati() {

        this.loading = true;
        let a: string;

        var selEsenzionefiltro = ""

        if (this.selectedPatologia != undefined) {
;
            selEsenzionefiltro = this.selectedPatologia.esenzionetipologia.codEsenzione;
        };

        var cognomebeneficiario = ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value;
        var nomebeneficiario = ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value;
        var codfiscbenfiltro = ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value;
        var idAuraBeneficiario = ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value;
        var datainifiltro = ( document.getElementById( "validada" ) as HTMLInputElement ).value;
        a = datainifiltro;

        var datafinefiltro = ( document.getElementById( "validaa" ) as HTMLInputElement ).value;
        a = datafinefiltro;
        var activeTab =  this.tabService.getCurrentTab();
        if (activeTab=="certificati") {

            var e = ( document.getElementById( "statofiltro" ) ) as HTMLSelectElement;
            var sel = e.selectedIndex;
            var statofiltro = "";
            if ( sel !== -1 ) {
                var opt = e.options[sel];
                statofiltro = ( <HTMLOptionElement>opt ).value;
            };
            this.getCertificatoPatologiaPaginato( selEsenzionefiltro, statofiltro, datainifiltro, datafinefiltro, idAuraBeneficiario,this.pagina, this.orderBy, this.asc );
        }
        else if (activeTab=="esenzioni"){

            var e = ( document.getElementById( "statofiltrop" ) ) as HTMLSelectElement;
            var sel = e.selectedIndex;
            var statofiltro = "";
            if ( sel !== 0 ) {
                var opt = e.options[sel];
                statofiltro = ( <HTMLOptionElement>opt ).value;
            };
            this.getEsenzionePatologiaPaginato( selEsenzionefiltro, statofiltro, datainifiltro, datafinefiltro, idAuraBeneficiario,this.pagina, this.orderBy, this.asc );
        }
        else if (activeTab=="ricercarichiestacertificati"){

              var e = ( document.getElementById( "statofiltror" ) ) as HTMLSelectElement;
              var sel = e.selectedIndex;
              var statofiltro = "";
              if ( sel !== -1 ) {
                  var opt = e.options[sel];
                  statofiltro = ( <HTMLOptionElement>opt ).value;
              };
              this.getRicercaCertificatoPaginato( selEsenzionefiltro, statofiltro, datainifiltro, datafinefiltro, idAuraBeneficiario,this.pagina, this.orderBy, this.asc );
          }
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

    SetPage( i, event: any ) {
        if ( i != -2 ) {
            event.preventDefault();
            this.pagina = i;
            this.costruisciBarra( this.pagina, this.lunghezzaarray )
            this.ApplicaFiltroCertificati();
        }
    }

    getCertificatoPatologiaPaginato( selEsenzionefiltro: string, statofiltro: string, datainifiltro: string, datafinefiltro: string,  idAuraBeneficiario: string, pagina: number, orderBy: string, asc: boolean ) {

        this.loading = true;
        let ricesecit = new RicercaCertificatoPatologia();

        var nota = "";
        var trovato = false;

        if ( selEsenzionefiltro != '' ) {
            ricesecit.codCertificato = selEsenzionefiltro;
            nota = nota + " Codice Certificato";
        }
        if ( statofiltro != '' ) {
            ricesecit.codStatoCertificato = statofiltro;
            nota = nota + " Stato";
        }
        if ( datainifiltro != '' ) {
            ricesecit.dataInizioValidita = datainifiltro;
            nota = nota + " Data Inizio";
        }
        if ( datafinefiltro != '' ) {
            ricesecit.dataFineValidita = datafinefiltro;
            nota = nota + " Data Scadenza";
        }
        if ( idAuraBeneficiario != null ) {
            ricesecit.idAuraBeneficiario = idAuraBeneficiario;
        }

y;
        ricesecit.pagina = pagina;
        document.getElementById( "componitesto" ).innerHTML = "Tutti i certificati";
        this.esenzioneStoreService.getCertificatoPatologiaPaginato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] || body.data == null) {

                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();

                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;

                    } else {
                        var numeroElementiPagina = +this.parametroTmp[0].valoreParametro;
                        this.lunghezzaarray = Math.ceil(( body.data[0].numeroTotaleElementi / numeroElementiPagina ) );
                        this.numerototpagine = new Array( this.lunghezzaarray );
                        this.costruisciBarra( this.pagina, this.lunghezzaarray );
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        $( ".mesric" ).hide();
                        ( document.getElementById( "numcertificatitrovati" ).innerHTML ) = body.data[0].numeroTotaleElementi;
                        this.loading = false;
                        this.listacertificati = body.data;
                        $( ".elecertris" ).show();
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
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        $( ".esitoricercagenerico" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                }
            } );
    }

    getEsenzionePatologiaPaginato( selEsenzionefiltro: string, statofiltro: string, datainifiltro: string, datafinefiltro: string,  idAuraBeneficiario: string, pagina: number, orderBy: string, asc: boolean ) {

        this.loading = true;
        let ricesecit = new RicercaCertificatoPatologia();

        var nota = "";
        var trovato = false;

        if ( selEsenzionefiltro != '' ) {
            ricesecit.codCertificato = selEsenzionefiltro;
            nota = nota + " Codice Certificato";
        }
        if ( statofiltro != '' ) {
            ricesecit.codStatoCertificato = statofiltro;
            nota = nota + " Stato";
        }
        if ( datainifiltro != '' ) {
            ricesecit.dataInizioValidita = datainifiltro;
            nota = nota + " Data Inizio";
        }
        if ( datafinefiltro != '' ) {
            ricesecit.dataFineValidita = datafinefiltro;
            nota = nota + " Data Scadenza";
        }
        if ( idAuraBeneficiario != null ) {
            ricesecit.idAuraBeneficiario = idAuraBeneficiario;
        }
        let cf:string = (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value.trim();
        if(cf.length > 0) {
            ricesecit.codFiscaleBeneficiario = cf;
        }


        ricesecit.pagina = pagina;
        document.getElementById( "componitesto" ).innerHTML = "Tutte le esenzioni";
        this.esenzioneStoreService.getEsenzionePatologiaPaginato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] || body.data == null) {
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
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
                        this.listaesenzioni = body.data;
                        $( ".elecertris" ).hide();
                        $( ".elericcertris" ).hide();
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
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        $( ".esitoricercagenerico" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                }
            } );
    }

    getRicercaCertificatoPaginato( selEsenzionefiltro: string, statofiltro: string, datainifiltro: string, datafinefiltro: string,  idAuraBeneficiario: string, pagina: number, orderBy: string, asc: boolean ) {

        this.loading = true;
        let ricesecit = new RicercaCertificatoPatologia();
        var nota = "";
        var trovato = false;

        if ( selEsenzionefiltro != '' ) {
            ricesecit.codCertificato = selEsenzionefiltro;
            nota = nota + " Codice Certificato";
        }
        if ( statofiltro != '' ) {
            ricesecit.codStatoCertificato = statofiltro;
            nota = nota + " Stato";
        }
        if ( datainifiltro != '' ) {
            ricesecit.dataInizioValidita = datainifiltro;
            nota = nota + " Data Inizio";
        }
        if ( datafinefiltro != '' ) {
            ricesecit.dataFineValidita = datafinefiltro;
            nota = nota + " Data Scadenza";
        }
        if ( idAuraBeneficiario != null ) {
            ricesecit.idAuraBeneficiario = idAuraBeneficiario;
        }

        ricesecit.asc = asc;
        ricesecit.orderBy = orderBy;
        ricesecit.pagina = pagina;

        document.getElementById( "componitesto" ).innerHTML = "Tutte le esenzioni";
        this.esenzioneStoreService.getRicercaCertificatoPaginato( ricesecit )
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if ( body.data == "" || body.data == [] || body.data == null) {
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;

                    } else {
                        var numeroElementiPagina = +this.parametroTmp[0].valoreParametro;
                        this.lunghezzaarray = Math.ceil(( body.data[0].numeroTotaleElementi / numeroElementiPagina ) );
                        this.numerototpagine = new Array( this.lunghezzaarray );
                        this.costruisciBarra( this.pagina, this.lunghezzaarray );
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".elericcertris" ).show();
                        $( ".mesric" ).hide();
                        ( document.getElementById( "numricercacertificatitrovati" ).innerHTML ) = body.data[0].numeroTotaleElementi;
                        this.listaesenzioni = body.data;
                        if(this.key != '') {
                            this.listaesenzioni = this.orderPipe.transform(this.listaesenzioni, this.key, this.reverse);
                        }
                        this.loading = false;
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
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                        $( ".esitoricercagenerico" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercako" ).hide();
                        $( ".elecertris" ).hide();
                        $( ".eleeseris" ).hide();
                        $( ".elericcertris" ).hide();
                        this.loading = false;
                        break;
                }
            } );
    }

    verificauguale(index, certificati:any[]) {
        if (certificati.length==1){
            return true;
        }

        if (index==0){
            var cfprec = certificati[index].beneficiario.codice_fiscale;
            if (cfprec==certificati[index+1].beneficiario.codice_fiscale){
                return false;
            }
            else {
                return true;
                }
            }
        else
            {
            var cfprec = certificati[index-1].beneficiario.codice_fiscale;
            if (cfprec==certificati[index].beneficiario.codice_fiscale)
                return false;
            else
                return true;
            }
    }


    values = '';
    onKeyPress(event: any,campo:string) {
        var valueCF = ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value
            var valueCG = ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value;
        if (valueCF!=null && valueCG!=null){
            if ((valueCF.length == 16) || (valueCG.length >=3)){
                $( "#cerca" ).prop( 'disabled',false );
            }
            else
            $( "#cerca" ).prop( 'disabled',true );
            }
        else if (valueCG!=null && valueCF==null){
            if (valueCG.length >=3 && campo=="CG" ){
                $( "#cerca" ).prop( 'disabled',false );
            }
            else
            $( "#cerca" ).prop( 'disabled',true );
            }
        else if (valueCG==null && valueCF!=null){
            if (valueCF.length ==16 && campo=="CF" ){
                $( "#cerca" ).prop( 'disabled',false );
            }
            else
            $( "#cerca" ).prop( 'disabled',true );
            }
            else
                $( "#cerca" ).prop( 'disabled',true );
    };


    getParam( id: string ) {
        this.parametroStoreService.getParametro( id )
            .subscribe( parametro => this.parametroTmp = parametro );
    }
    getRicercaAssistitoIsOn() {
        return this.ricAss.isOn;
    }

    setRicercaAssistitoIsOn() {
        this.ricAss.isOn = !this.ricAss.isOn;
    }

    AnnullaRicerca() {
        this.SvuotaFiltroRicerca();
        this.listacertificati = [];
        this.pagina = 1;
        $( ".elecertris" ).hide();
        $( ".eleeseris" ).hide();
        $( ".elericcertris" ).hide();
    }

    resetCercaBeneficiario(){
        $( ".eleassistiti" ).hide();
        this.assistito = [];
        ( document.getElementById( "numtrovati" ).innerHTML ) = "0";
        ( document.getElementById( "datiassistito" ).innerHTML ) = "";
        this.AnnullaRicerca();
    }

    getDettaglioCertificatoIsOn() {
        return this.dettCert.isOn;
    }

    setDettaglioCertificatoIsOn() {
        this.dettCert.isOn = !this.dettCert.isOn;
    }

    SvuotaFiltroRicerca() {
        ( ( document.getElementById( "cognomebenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "nomebenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "codfiscbenfiltro" ) ) as HTMLInputElement ).value = "";
        ( ( document.getElementById( "idassistitobenric" ) ) as HTMLInputElement ).value = "";
  ;

        $( '#startdateda' ).datepicker( 'update', '' );
        $( '#startdatea' ).datepicker( 'update', '' );
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

    getDettaglioCertificato(index: number) {
        this.loading = true;
        this.esenzioneStoreService.getDettaglioCertificato(this.listacertificati[index].beneficiario.codice_fiscale, this.listacertificati[index].sk_documento)
        .subscribe(data => {
            if(data._body != null && data._body) {
                 this.router.navigate( ["../dettaglio-certificato"], { relativeTo: this.route,skipLocationChange: true } );
            }
            this.loading = false;
        },
         errorCode => {
             var str = errorCode._body;
             var objmsg = JSON.parse( str );
             console.log(objmsg);
             this.loading = false;
         }
        );

    }


    getDettaglioEsenzione(index: number) {

        let filtro = new FiltriDettaglioEsenzione();
        if (this.listaesenzioni[index].beneficiario.codice_fiscale!=null)
        filtro.cf_beneficiario = this.listaesenzioni[index].beneficiario.codice_fiscale;
        if (this.listaesenzioni[index].codice_esenzione!=null)
        filtro.cod_esenzione = this.listaesenzioni[index].codice_esenzione;
        if (this.listaesenzioni[index].valida_dal!=null)
        filtro.data_emissione = this.listaesenzioni[index].valida_dal;
        if (this.listaesenzioni[index].sk_pratica_esenzione!=null)
        filtro.sk_esenzione = this.listaesenzioni[index].sk_pratica_esenzione;

        this.loading = true;
        this.esenzioneStoreService.getDettaglioEsenzione(filtro)
        .subscribe(data => {
            if(data._body != null && data._body) {
                 this.router.navigate( ["../dettaglio-esenzione"], { relativeTo: this.route,skipLocationChange: true } );
            }
            this.loading = false;
        },
         errorCode => {
             var str = errorCode._body;
             var objmsg = JSON.parse( str );
             console.log(objmsg);
             this.loading = false;
         }
        );

    }

    navigateToRicerca(){
        this.router.navigate( ["../ricerca-assistito", "cert"], {relativeTo: this.route,skipLocationChange: true} );
    }

    ApriCronologia(index:number){
        this.storicoEsenzione = [];
        this.loading = true;
        this.esenzioneStoreService.getCronologia(this.listaesenzioni[index].beneficiario.codice_fiscale, this.listaesenzioni[index].sk_pratica_esenzione)
        .subscribe( response => {
            this.storicoEsenzione = response;
            this.loading = false;
       });

    }

    ApriAttestato(index:number){
        this.loading = true;
        this.esenzioneStoreService.scaricaAttestatoLegale(this.listaesenzioni[index].beneficiario.codice_fiscale).subscribe(
          ( res ) => {
            let name: string = res.get("name") as string;
            let file: Blob = res.get("file") as Blob;
            var fileURL = URL.createObjectURL( file );
            var link = document.createElement( 'a' );
            link.href = window.URL.createObjectURL( file );
            link.download = name;
            document.body.appendChild( link );
            link.click();
            document.body.removeChild( link );
            this.loading = false;
          },
          errorCode => {
                  ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = "Nessun elemento trovato";
                  $( ".esitoricercako" ).show();
                  $( ".esitoricercaok" ).hide();
                  $( ".esitoricercagenerico" ).hide();
                  this.loading = false;
          }
        );
      }
}
