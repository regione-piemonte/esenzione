import { Component, Input, OnInit, Output, Directive, ElementRef, ViewChild, OnDestroy} from '@angular/core';
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
import { FiltroPaginazione } from "app/esenzione/filtropaginazione";
import { certificato } from "app/esenzione/certificato";
import { DettaglioCertificato } from "app/appoperatorepato/dettaglioCertificato/DettaglioCertificato.component";
import { CertificatoPatologia } from "app/esenzione/CertificatoPatologia";
import { Ruolo } from "app/assistito/ruolo";
import { esenzioneAssistito } from 'app/esenzione/esenzioneAssistito';
import { FiltriRicercaPratiche } from 'app/esenzione/FiltriRicercaPratiche';
import { FiltriDettaglioEsenzione } from 'app/esenzione/FiltriDettaglioEsenzione';
import { CheckItem } from './CheckItem';
import { StoricoEsenzione } from 'app/esenzione/storicoEsenzione';
import { FiltroValidaEsenzionePatologia } from "app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia";
import { beneficiario } from "app/assistito/beneficiario";
import {Subject} from 'rxjs/Subject';
import { TabService } from "app/appoperatorepato/home/tab.service";
import { Subscription } from "rxjs/Subscription";
import { UserInfo } from 'app/assistito/userinfo';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { OrderPipe } from 'ngx-order-pipe';
declare var $;
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-operatorepato-home-esen',
    templateUrl: './home-esen.component.html'
} )


export class HomeAppOperatorePatoEsen implements OnInit, OnDestroy{

    pagina_: number;
    assistito: Assistito[] = [];
    on: boolean = false;
    listacertificati : certificato[] = [];
    listaPratiche: esenzioneAssistito[] = [];
    loading = false;

    @ViewChild('codfiscben') codfiscben: ElementRef;
    @ViewChild('cognomeben') cognomeben: ElementRef;
    @ViewChild('nomeben') nomeben: ElementRef;
    assistitoBen: string;
    statusCode: number;
    public pagina: number = 1;
    private numerototpagine: Array<number>;
    public orderBy: string = "beneficiario.cognome";
    public asc: boolean = true;
    private lunghezzaarray: number;
    parametroTmp: parametro[] = [];
    selectedBeneficiario: number;
    tabActive: string;
    storicoEsenzione: StoricoEsenzione[] = [];
    checkList: CheckItem[] = [];
    listadavalidarepratica: Array<esenzioneAssistito>;
    conta:number;
    numerovaloriperpagina:number;
    numerototaleelementi:number;
    selezionaTutti: boolean = false;

    @Input() notifier: Subject<string>;
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

    constructor(private http: Http,
        private router: Router,
        private route: ActivatedRoute,
        private assistitoStoreService: AssistitoStoreService,
        private statodocumentoStoreService: statodocumentoStoreService,
        private esenzioneStoreService: EsenzioneStoreService,
        private parametroStoreService: parametroStoreService,
        private config: ConfigService,
        private orderPipe: OrderPipe,
        private tabService: TabService)
        {
        }

    ngOnInit() {
        var currentTab = this.tabService.getCurrentTab();
        let utente: UserInfo = this.assistitoStoreService.getUser();

        if(this.tabService.getReturnFrom()) {
            let tabSel = '';
            if(currentTab === "daFare") {
                tabSel = "daFareTab"
            } else if(currentTab === "tutteLePratiche") {
                tabSel = "tutPraTab"
            }
            if(tabSel != '') {
                let tab: HTMLElement = document.getElementById(tabSel) as HTMLElement;
                tab.click();
            }

        } else {
            utente.ruoli.forEach(ruolo => {
                if (ruolo.codiceRuolo === 'OPERATORE_ASL_DISTRETTO') {
                    let tab: HTMLElement = document.getElementById("daFareTab") as HTMLElement;
                    tab.click();
                }
            });
        }

        $(".eleese").show();
        $(".esitoricercaok").hide();
        $(".esitoricercako").hide();
        $(".esitoricercagenerico").hide();
        $(".eledafareris").hide();
        $(".eletuttelepraticheris").hide();
        $(".eleassistiti").hide();
        $(".statofiltrop").hide();
        $(".statofiltroc").show();
        this.getParam("ELEMENTI_RICERCA");

        this.tabActive = currentTab;
        this.conta=0;
        this.listadavalidarepratica = [];
        this.selezionaTutti = false;

        this.subscription = this.notifier.subscribe(
                data => this.resetForm(data));

        if(this.tabService.getReturnFrom()) {
            $(document).ready( elem => {
                this.returnSetting();
            });
        }
    }

    returnSetting() {
        if(this.tabService.lastSearch.filtri.stati_pratica.length === 1) {
;

            ((document.getElementById("statofiltro")) as HTMLSelectElement).selectedIndex = this.tabService.lastSearch.statoIndex;

        }

        ((document.getElementById("codEsenzionePatologiafiltro")) as HTMLSelectElement).selectedIndex = this.tabService.lastSearch.codEsenIndex;

        ((document.getElementById("cognomebenfiltro")) as HTMLInputElement).value = this.tabService.lastSearch.filtri.cognome_beneficiario;
        ((document.getElementById("nomebenfiltro")) as HTMLInputElement).value = this.tabService.lastSearch.filtri.nome_beneficiario;
        ((document.getElementById("codfiscbenfiltro")) as HTMLInputElement).value = this.tabService.lastSearch.filtri.cf_beneficiario;
        ((document.getElementById("idassistitobenric")) as HTMLInputElement).value = this.tabService.lastSearch.filtri.id_aura;
        (document.getElementById("validada") as HTMLInputElement).value = this.tabService.lastSearch.filtri.valida_dal;
        (document.getElementById("validaa") as HTMLInputElement).value = this.tabService.lastSearch.filtri.valida_al;

        var numeroElementiPagina = this.tabService.lastSearch.numeroElementiPagina;
        this.numerovaloriperpagina = this.tabService.lastSearch.numerovaloriperpagina;
        this.lunghezzaarray = this.tabService.lastSearch.lunghezzaarray;
        this.numerototpagine = this.tabService.lastSearch.numerototpagine;
        this.pagina = this.tabService.lastSearch.pagina;

        this.costruisciBarra(this.pagina, this.lunghezzaarray);

        $(".esitoricercaok").hide();
        $(".esitoricercako").hide();
        $(".esitoricercagenerico").hide();

        if(this.tabActive == "daFare") {
            $( ".eledafareris" ).show();
            $(".eletuttelepraticheris").hide();
            (document.getElementById("numpratichedafare").innerHTML) = this.tabService.lastSearch.numeroTotaleElementi.toString();
            this.numerototaleelementi = this.tabService.lastSearch.numeroTotaleElementi;
        }
        else if(this.tabActive == "tutteLePratiche") {
            $( ".eletuttelepraticheris" ).show();
            $(".eledafareris").hide();
            (document.getElementById("numtuttelepratiche").innerHTML) = this.tabService.lastSearch.numeroTotaleElementi.toString();
        }
        $(".mesric").hide();
        this.listaPratiche = this.tabService.lastSearch.listaPratiche;
        this.listadavalidarepratica = this.tabService.lastSearch.listadavalidarepratica;
        this.selezionaTutti = this.tabService.lastSearch.selezionaTutti;
        this.parametroTmp = this.tabService.lastSearch.parametroTmp;
        this.isCheckedAll();
        this.loading = true;
        this.getPratichePaginato(this.tabService.lastSearch.filtri, this.orderBy, this.asc);
        this.tabService.setReturnFrom(false);
    }

    ngOnDestroy(){
        this.subscription.unsubscribe();
    }

    resetForm(form: string) {

        (document.getElementById("codfiscbenfiltro") as HTMLInputElement).classList.remove("inputError");
        (document.getElementById("cognomebenfiltro") as HTMLInputElement).classList.remove("inputError");
        var e = (document.getElementById("codEsenzionePatologiafiltro")) as HTMLSelectElement
        e.selectedIndex = 0;
        this.resetCercaBeneficiario();
        this.key = "";
        this.orderBy = "beneficiario.cognome";
        this.reverse = true;
        this.assistito = [];
        this.listadavalidarepratica = [];
        this.selezionaTutti = false;
        if (form == "#daFare") {
            this.tabActive = "daFare";
            $(".esitoricercaok").hide();
            $(".esitoricercako").hide();
            $(".esitoricercagenerico").hide();

            $(".eledafareris").hide();
            $(".eleassistiti").hide();
            $(".statofiltrop").hide();
            $(".statofiltroc").show();


        } if (form == "#tutteLePratiche") {
            this.tabActive = "tutteLePratiche";
            $(".esitoricercaok").hide();
            $(".esitoricercako").hide();
            $(".esitoricercagenerico").hide();

            $(".eletuttelepraticheris").hide();
            $(".eleassistiti").hide();
            $(".statofiltrop").show();
            $(".statofiltroc").hide();


        }

    }


getDettaglioEsenzione(index: number) {
        this.tabService.lastSearch.listadavalidarepratica = this.listadavalidarepratica;

        let filtro = new FiltriDettaglioEsenzione();
        if (this.listaPratiche[index].beneficiario.codice_fiscale!=null)
        filtro.cf_beneficiario = this.listaPratiche[index].beneficiario.codice_fiscale;
        if (this.listaPratiche[index].codice_esenzione!=null)
        filtro.cod_esenzione = this.listaPratiche[index].codice_esenzione;
        if (this.listaPratiche[index].valida_dal!=null)
        filtro.data_emissione = this.listaPratiche[index].valida_dal;
        if (this.listaPratiche[index].sk_pratica_esenzione!=null)
        filtro.sk_esenzione = this.listaPratiche[index].sk_pratica_esenzione;

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

    Cerca() {
        this.listadavalidarepratica = [];
        this.selezionaTutti = false;
        $(".eleeseris").hide();
        $(".eleassistiti").hide();
        (document.getElementById("codfiscbenfiltro") as HTMLInputElement).classList.remove("inputError");
        (document.getElementById("cognomebenfiltro") as HTMLInputElement).classList.remove("inputError");

        let a: string;
        this.loading = true;
        let ricesecit = new CittadinoRicerca();
        this.selectedBeneficiario = null;
        ricesecit.codFiscale = "";
        ricesecit.cognome = "";
        ricesecit.nome = "";
        ricesecit.dataDiNascita = "";
        ricesecit.ruolo = "";
        this.assistito = [];
        ((document.getElementById("idassistitobenric")) as HTMLInputElement).value = "";

        if (((document.getElementById("codfiscbenfiltro") as HTMLInputElement).value) != "") {
            ricesecit.codFiscale = (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value.toUpperCase();
        }
        else {
            ricesecit.cognome = (document.getElementById("cognomebenfiltro") as HTMLInputElement).value.toUpperCase();
            ricesecit.nome = (document.getElementById("nomebenfiltro") as HTMLInputElement).value.toUpperCase();
        }

        if (this.tabActive == "daFare") {

            if (ricesecit.codFiscale == "" && ricesecit.cognome == "" && ricesecit.nome == "")
                this.ApplicaFiltroPraticheFirst();

            else {
                this.assistitoStoreService.findCittadiniPato(ricesecit)
                    .subscribe(successCode => {
                        this.statusCode = successCode.status;
                        let body = successCode.json();
                        if (this.statusCode == 200) {
                            if (body.data == "" || body.data == [] || body.data == null) {
                                (document.getElementById("messaggioesitoricercaok").innerHTML) = body.message;
                                $(".esitoricercaok").show();
                                $(".esitoricercako").hide();
                                $(".esitoricercagenerico").hide();
                                $(".eledafareris").hide();
                                $(".eletuttelepraticheris").hide();
                                $(".eleassistiti").hide();
                                this.loading = false;
                            }
                            else if (body.data.length == 1) {

                                (document.getElementById("idassistitobenric") as HTMLInputElement).value = body.data[0].idAura;
                                (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value = body.data[0].codFiscale;
                                (document.getElementById("cognomebenfiltro") as HTMLInputElement).value = body.data[0].cognome;
                                (document.getElementById("nomebenfiltro") as HTMLInputElement).value = body.data[0].nome;
                                $(".eleassistiti").hide();
                                this.ApplicaFiltroPraticheFirst();
                            }
                            else {
                                (document.getElementById("numtrovati").innerHTML) = body.data.length;
                                (document.getElementById("datiassistito").innerHTML) = ricesecit.cognome + " " + ricesecit.nome;
                                $(".esitoricercaok").hide();
                                $(".esitoricercako").hide();
                                $(".esitoricercagenerico").hide();
                                $(".eleassistiti").show();
                                $(".mesric").hide();
                                this.loading = false;
                                this.assistito = body.data;
                            }
                        }
                    },
                        errorCode => {
                            this.statusCode = errorCode.status;
                            var str = errorCode._body;
                            var objmsg = JSON.parse(str);
                            switch (this.statusCode) {
                                case 400:
                                    (document.getElementById("messaggioesitoricercako").innerHTML) = objmsg.message;
                                    $(".esitoricercako").show();
                                    $(".esitoricercaok").hide();
                                    $(".esitoricercagenerico").hide();
                                    $(".eleeseris").hide();
                                    $(".eleassistiti").hide();
                                    this.loading = false;
                                    break;
                                default:
                                    (document.getElementById("messaggioesitoricercagenerico").innerHTML) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                                    $(".esitoricercagenerico").show();
                                    $(".esitoricercaok").hide();
                                    $(".esitoricercako").hide();
                                    $(".eleeseris").hide();
                                    $(".eleassistiti").hide();
                                    this.loading = false;
                                    break;
                            }
                        })
            }
        } if (this.tabActive == "tutteLePratiche") {
            if (ricesecit.codFiscale == "" && ricesecit.cognome == "") {
                (document.getElementById("codfiscbenfiltro") as HTMLInputElement).classList.add("inputError");
                (document.getElementById("cognomebenfiltro") as HTMLInputElement).classList.add("inputError");
                this.loading = false;
            } else {
                this.assistitoStoreService.findCittadiniPato(ricesecit)
                    .subscribe(successCode => {
                        this.statusCode = successCode.status;
                        let body = successCode.json();
                        if (this.statusCode == 200) {
                            if (body.data == "" || body.data == [] || body.data == null) {
                                (document.getElementById("messaggioesitoricercaok").innerHTML) = body.message;
                                $(".esitoricercaok").show();
                                $(".esitoricercako").hide();
                                $(".esitoricercagenerico").hide();
                                $(".eleeseris").hide();
                                $(".eleassistiti").hide();
                                this.loading = false;
                            }
                            else if (body.data.length == 1) {

                                (document.getElementById("idassistitobenric") as HTMLInputElement).value = body.data[0].idAura;
                                (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value = body.data[0].codFiscale;
                                (document.getElementById("cognomebenfiltro") as HTMLInputElement).value = body.data[0].cognome;
                                (document.getElementById("nomebenfiltro") as HTMLInputElement).value = body.data[0].nome;
                                $(".eleassistiti").hide();
                                this.ApplicaFiltroPraticheFirst();
                            }
                            else {
                                (document.getElementById("numtrovati").innerHTML) = body.data.length;
                                (document.getElementById("datiassistito").innerHTML) = ricesecit.cognome + " " + ricesecit.nome;
                                $(".esitoricercaok").hide();
                                $(".esitoricercako").hide();
                                $(".esitoricercagenerico").hide();
                                $(".eleassistiti").show();
                                $(".mesric").hide();
                                this.loading = false;
                                this.assistito = body.data;
                            }
                        }
                    },
                        errorCode => {
                            this.statusCode = errorCode.status;
                            var str = errorCode._body;
                            var objmsg = JSON.parse(str);
                            switch (this.statusCode) {
                                case 400:
                                    (document.getElementById("messaggioesitoricercako").innerHTML) = objmsg.message;
                                    $(".esitoricercako").show();
                                    $(".esitoricercaok").hide();
                                    $(".esitoricercagenerico").hide();
                                    $(".eleeseris").hide();
                                    $(".eleassistiti").hide();
                                    this.loading = false;
                                    break;
                                default:
                                    (document.getElementById("messaggioesitoricercagenerico").innerHTML) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                                    $(".esitoricercagenerico").show();
                                    $(".esitoricercaok").hide();
                                    $(".esitoricercako").hide();
                                    $(".eleeseris").hide();
                                    $(".eleassistiti").hide();
                                    this.loading = false;
                                    break;
                            }
                        })
            }

        }
    }

    ScegliCittadino() {
        $(".eleeseris").hide();

        let a: string;
        this.loading = true;
        let ricesecit = new CittadinoRicerca();
        this.selectedBeneficiario = null;
        ricesecit.codFiscale = "";
        ricesecit.cognome = "";
        ricesecit.nome = "";
        ricesecit.dataDiNascita = "";
        ricesecit.ruolo = "";
        ((document.getElementById("idassistitobenric")) as HTMLInputElement).value = "";

        if (((document.getElementById("codfiscbenfiltro") as HTMLInputElement).value) != "") {
            ricesecit.codFiscale = (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value.toUpperCase();
        }
        else {
            ricesecit.cognome = (document.getElementById("cognomebenfiltro") as HTMLInputElement).value.toUpperCase();
            ricesecit.nome = (document.getElementById("nomebenfiltro") as HTMLInputElement).value.toUpperCase();
        }
        ricesecit.ruolo = "Ricerca";

        this.assistitoStoreService.findCittadiniPato(ricesecit)
            .subscribe(successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if (this.statusCode == 200) {
                    if (body.data == "" || body.data == [] || body.data == null) {
                        (document.getElementById("messaggioesitoricercaok").innerHTML) = body.message;
                        $(".esitoricercaok").show();
                        $(".esitoricercako").hide();
                        $(".esitoricercagenerico").hide();
                        $(".eleeseris").hide();
                        this.loading = false;
                    }
                    else {

                        (document.getElementById("idassistitobenric") as HTMLInputElement).value = body.data[0].idAura;
                        (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value = body.data[0].codFiscale;
                        (document.getElementById("cognomebenfiltro") as HTMLInputElement).value = body.data[0].cognome;
                        (document.getElementById("nomebenfiltro") as HTMLInputElement).value = body.data[0].nome;
                        $(".eleeseris").hide();
                        this.ApplicaFiltroPraticheFirst();
                    }
                }
            },
                errorCode => {
                    this.statusCode = errorCode.status;
                    var str = errorCode._body;
                    var objmsg = JSON.parse(str);
                    switch (this.statusCode) {
                        case 400:
                            (document.getElementById("messaggioesitoricercako").innerHTML) = objmsg.message;
                            $(".esitoricercako").show();
                            $(".esitoricercaok").hide();
                            $(".esitoricercagenerico").hide();
                            $(".eleeseris").hide();
                            this.loading = false;
                            break;
                        default:
                            (document.getElementById("messaggioesitoricercagenerico").innerHTML) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                            $(".esitoricercagenerico").show();
                            $(".esitoricercaok").hide();
                            $(".esitoricercako").hide();
                            $(".eleeseris").hide();
                            this.loading = false;
                            break;
                    }
                })
    }

    selectBeneficiario(i: number) {
        this.selectedBeneficiario = i;
        (document.getElementById("codfiscbenfiltro") as HTMLInputElement).value = this.assistito[i].codFiscale;
        (document.getElementById("cognomebenfiltro") as HTMLInputElement).value = this.assistito[i].cognome;
        (document.getElementById("nomebenfiltro") as HTMLInputElement).value = this.assistito[i].nome;
        (document.getElementById("idassistitobenric") as HTMLInputElement).value = this.assistito[i].idAura;
    }


    //sorting
    key: string = '';
    reverse: boolean = true;
    sort1(key) {
        this.key = key;
        this.reverse = !this.reverse;
        this.listaPratiche = this.orderPipe.transform(this.listaPratiche, this.key, this.reverse);
    }

    sort(key) {
        this.pagina = 1;
        this.key = key;
        //this.orderBy = key;
        this.reverse = !this.reverse;
        //this.asc = this.reverse;
        this.ApplicaFiltroPratiche();
        this.listaPratiche = this.orderPipe.transform(this.listaPratiche, this.key, this.reverse);
    }
    p: number = 1;

    ApplicaFiltroPraticheFirst() {
        this.pagina = 1;
        $(".esitoricercako").hide();
        $(".esitoricercaok").hide();
        (document.getElementById("messaggioesitoricercako").innerHTML) = "";
        var cognomebeneficiario = ((document.getElementById("cognomebenfiltro")) as HTMLInputElement).value;
        var nomebeneficiario = ((document.getElementById("nomebenfiltro")) as HTMLInputElement).value;
        var codfiscbenfiltro = ((document.getElementById("codfiscbenfiltro")) as HTMLInputElement).value;
        var idAuraBeneficiario = ((document.getElementById("idassistitobenric")) as HTMLInputElement).value;
        if (cognomebeneficiario == "" || nomebeneficiario == "" || codfiscbenfiltro == "") {
            ((document.getElementById("idassistitobenric")) as HTMLInputElement).value = "";
        }
        this.ApplicaFiltroPratiche();


    }

    ApplicaFiltroPratiche() {

        this.loading = true;
        let a: string;


        var selEsenzionefiltro = ""

        if (this.selectedPatologia != undefined) {

            selEsenzionefiltro = this.selectedPatologia.esenzionetipologia.codEsenzione;
        };

        var e = (document.getElementById("statofiltro")) as HTMLSelectElement;
        var sel = e.selectedIndex;
        var statofiltro = "";
        if (sel !== 0) {
            var opt = e.options[sel];
            statofiltro = (<HTMLOptionElement>opt).value;
        };

        var cognomebeneficiario = ((document.getElementById("cognomebenfiltro")) as HTMLInputElement).value;
        var nomebeneficiario = ((document.getElementById("nomebenfiltro")) as HTMLInputElement).value;
        var codfiscbenfiltro = ((document.getElementById("codfiscbenfiltro")) as HTMLInputElement).value;
        var idAuraBeneficiario = ((document.getElementById("idassistitobenric")) as HTMLInputElement).value;
        var datainifiltro = (document.getElementById("validada") as HTMLInputElement).value;
        a = datainifiltro;

        var datafinefiltro = (document.getElementById("validaa") as HTMLInputElement).value;
        a = datafinefiltro;

        let filtri = new FiltriRicercaPratiche();
        filtri.cf_beneficiario = codfiscbenfiltro;
        filtri.cognome_beneficiario = cognomebeneficiario;
        filtri.nome_beneficiario = nomebeneficiario;
        filtri.id_aura = idAuraBeneficiario;
        filtri.valida_dal = datainifiltro;
        filtri.valida_al = datafinefiltro;
        filtri.cod_esenzione = selEsenzionefiltro;
        filtri.pagina = this.pagina;
        filtri.tab = this.tabActive;

        if(statofiltro === "") {
            if(this.tabActive === "daFare") {
                filtri.stati_pratica = this.statodocumentoStoreService.listaStatoPraticaDaFare;
            } else if(this.tabActive === "tutteLePratiche") {
                filtri.stati_pratica = this.statodocumentoStoreService.listaStatoPraticaTutteLePratiche;
            }
        } else {
            if(this.tabActive === "daFare") {
                this.statodocumentoStoreService.listaStatoPraticaDaFare.forEach(statopratica => {
                    if(statopratica.descStato === statofiltro) {
                        filtri.stati_pratica.push(statopratica);
                    }
                });
            } else if(this.tabActive === "tutteLePratiche") {
                this.statodocumentoStoreService.listaStatoPraticaTutteLePratiche.forEach(statopratica => {
                    if(statopratica.descStato === statofiltro) {
                        filtri.stati_pratica.push(statopratica);
                    }
                });
            }
        }


        this.getPratichePaginato(filtri, this.orderBy, this.asc);

    }

    costruisciBarra(currentPage: number, numTotPage: number) {
        for (var i = 0; i < this.lunghezzaarray; i++) {
            this.numerototpagine[i] = (-1);
        }
        this.numerototpagine[0] = -1;
        if (numTotPage <= 9) {
            for (var i = 0; i < this.lunghezzaarray; i++) {
                this.numerototpagine[i + 1] = i + 1;

            }
        } else {
            var diffine = numTotPage - currentPage;
            var difini = currentPage - 1;


            this.numerototpagine[1] = 1;
            if (diffine < 3) {


                for (var i = currentPage - 3 - (3 - diffine); i < currentPage + diffine; i++) {
                    if (i != 1 && i != numTotPage) {
                        this.numerototpagine[i] = i;

                    }
                }
                this.numerototpagine[currentPage - 3 - (3 - diffine)] = -2;
            }
            else if (difini < 3) {
                for (var i = currentPage - difini; i < currentPage + 3 + (3 - difini); i++) {
                    if (i != 1 && i != numTotPage) {
                        this.numerototpagine[i] = i;

                    }
                }
                this.numerototpagine[currentPage + 3 + (3 - difini)] = -2;
            }
            else {
                if (currentPage - 3 > 1) {
                    this.numerototpagine[currentPage - 3] = -2;
                }
                for (var i = currentPage - 2; i <= currentPage + 3; i++) {
                    if (i != 1 && i != numTotPage) {
                        this.numerototpagine[i] = i;

                    }
                }
                if (currentPage + 3 != numTotPage) {
                    this.numerototpagine[currentPage + 3] = -2;
                }
            }


            this.numerototpagine[numTotPage] = numTotPage;
        }
    }

    SetPage(i, event: any) {
        if (i != -2) {
            event.preventDefault();
            this.pagina = i;
            this.costruisciBarra(this.pagina, this.lunghezzaarray);
            this.ApplicaFiltroPratiche();
        }
    }

    getPratichePaginato(filtri: FiltriRicercaPratiche, orderBy: string, asc: boolean) {

        this.loading = true;
        this.esenzioneStoreService.getPratichePaginato(filtri)
            .subscribe(successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if (this.statusCode == 200) {
                    if (body.data == "" || body.data == [] || body.data == null) {
                        (document.getElementById("messaggioesitoricercaok").innerHTML) = body.message;
                        $(".esitoricercaok").show();
                        $(".esitoricercako").hide();
                        $(".esitoricercagenerico").hide();
                        $(".eledafareris").hide();
                        $(".eletuttelepraticheris").hide();
                        this.loading = false;

                    } else {
                        var numeroElementiPagina = +this.parametroTmp[0].valoreParametro;
                        this.numerovaloriperpagina = numeroElementiPagina;
                        this.lunghezzaarray = Math.ceil((body.data[0].numeroTotaleElementi / numeroElementiPagina));
                        this.numerototpagine = new Array(this.lunghezzaarray);
                        this.costruisciBarra(this.pagina, this.lunghezzaarray);

                        $(".esitoricercaok").hide();
                        $(".esitoricercako").hide();
                        $(".esitoricercagenerico").hide();

                        if(this.tabActive == "daFare") {
                            $( ".eledafareris" ).show();
                            $(".eletuttelepraticheris").hide();
                            (document.getElementById("numpratichedafare").innerHTML) = body.data[0].numeroTotaleElementi;
                            this.numerototaleelementi = body.data[0].numeroTotaleElementi;
                        }
                        else if(this.tabActive == "tutteLePratiche") {
                            $( ".eletuttelepraticheris" ).show();
                            $(".eledafareris").hide();
                            (document.getElementById("numtuttelepratiche").innerHTML) = body.data[0].numeroTotaleElementi;
                        }
                        $(".mesric").hide();
                        this.listaPratiche = body.data;
                        if(this.key != '') {
                            this.listaPratiche = this.orderPipe.transform(this.listaPratiche, this.key, this.reverse);
                        }
                        this.loading = false;
                        this.isCheckedAll();

                        this.tabService.lastSearch = {
                            filtri: filtri,
                            numeroElementiPagina: numeroElementiPagina,
                            pagina: this.pagina,
                            lunghezzaarray: this.lunghezzaarray,
                            numerototpagine: this.numerototpagine,
                            numerovaloriperpagina: this.numerovaloriperpagina,
                            numeroTotaleElementi: body.data[0].numeroTotaleElementi,
                            listaPratiche: this.listaPratiche,
                            selezionaTutti: this.selezionaTutti,
                            listadavalidarepratica: this.listadavalidarepratica,
                            parametroTmp: this.parametroTmp,
                            statoIndex: ((document.getElementById("statofiltro")) as HTMLSelectElement).selectedIndex,
                            codEsenIndex: ((document.getElementById("codEsenzionePatologiafiltro")) as HTMLSelectElement).selectedIndex

                        }

                    }
                }

            },
                errorCode => {
                    var str = errorCode._body;
                    var objmsg = JSON.parse(str);
                    this.statusCode = errorCode.status;
                    switch (this.statusCode) {
                        case 400:
                            (document.getElementById("messaggioesitoricercako").innerHTML) = objmsg.message;
                            $(".esitoricercako").show();
                            $(".esitoricercaok").hide();
                            $(".esitoricercagenerico").hide();
                            $(".eledafareris").hide();
                            $(".eletuttelepraticheris").hide();
                            this.loading = false;
                            break;
                        default:
                            (document.getElementById("messaggioesitoricercagenerico").innerHTML) = "ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.";
                            $(".esitoricercagenerico").show();
                            $(".esitoricercaok").hide();
                            $(".esitoricercako").hide();
                            $(".eledafareris").hide();
                            $(".eletuttelepraticheris").hide();
                            this.loading = false;
                            break;
                    }
                });
    }


    values = '';
    onKeyPress(event: any, campo: string) {
        var valueCF = ((document.getElementById("codfiscbenfiltro")) as HTMLInputElement).value
        var valueCG = ((document.getElementById("cognomebenfiltro")) as HTMLInputElement).value;
        if (valueCF != null && valueCG != null) {
            if ((valueCF.length == 16) || (valueCG.length >= 3)) {
                $("#cerca").prop('disabled', false);
            }
            else
                $("#cerca").prop('disabled', true);
        }
        else if (valueCG != null && valueCF == null) {
            if (valueCG.length >= 3 && campo == "CG") {
                $("#cerca").prop('disabled', false);
            }
            else
                $("#cerca").prop('disabled', true);
        }
        else if (valueCG == null && valueCF != null) {
            if (valueCF.length == 16 && campo == "CF") {
                $("#cerca").prop('disabled', false);
            }
            else
                $("#cerca").prop('disabled', true);
        }
        else
            $("#cerca").prop('disabled', true);
    };




    getParam(id: string) {
        this.parametroStoreService.getParametro(id)
            .subscribe(parametro => this.parametroTmp = parametro);
    }


    AnnullaRicerca() {
        this.SvuotaFiltroRicerca();
        this.listaPratiche = [];
        this.pagina = 1;
        $(".eledafareris").hide();
        $(".eletuttelepraticheris").hide();
    }

    resetCercaBeneficiario() {
        $(".eleassistiti").hide();
        (document.getElementById("codfiscbenfiltro") as HTMLInputElement).classList.remove("inputError");
        (document.getElementById("cognomebenfiltro") as HTMLInputElement).classList.remove("inputError");
        this.assistito = [];
        (document.getElementById("numtrovati").innerHTML) = "0";
        (document.getElementById("datiassistito").innerHTML) = "";
        this.AnnullaRicerca();
    }


    SvuotaFiltroRicerca() {
        ((document.getElementById("cognomebenfiltro")) as HTMLInputElement).value = "";
        ((document.getElementById("nomebenfiltro")) as HTMLInputElement).value = "";
        ((document.getElementById("codfiscbenfiltro")) as HTMLInputElement).value = "";
        ((document.getElementById("idassistitobenric")) as HTMLInputElement).value = "";


        $('#startdateda').datepicker('update', '');
        $('#startdatea').datepicker('update', '');
    }

    ChiudiFiltroOp() {
        $(".divFiltri").hide();
        $(".aprifiltroOp").show();
        $(".chiudifiltroOp").hide();

    }

    ApriFiltroOp() {
        $(".divFiltri").show();
        $(".aprifiltroOp").hide();
        $(".chiudifiltroOp").show();

    }


    navigateToRicerca() {
        this.router.navigate(["../ricerca-assistito", "esen"], { relativeTo: this.route, skipLocationChange: true });


    }

    isDaValidare(sk_pratica_esenzione){
        var esen = this.listadavalidarepratica.find(
                esenzione => esenzione.sk_pratica_esenzione == sk_pratica_esenzione);
        if(esen)
            return true;
        else
            return false;
    }

    aggiungiDaValidare(pratica_esenzione){
        var esen: number = this.listadavalidarepratica.findIndex(
                esenzione => esenzione.sk_pratica_esenzione == pratica_esenzione.sk_pratica_esenzione);

        if(esen != -1)
            this.listadavalidarepratica.splice(esen,1);
        else
            this.listadavalidarepratica.push(pratica_esenzione);
    }

    checkAll(){
        if ((document.getElementById( "checker" ) as HTMLInputElement ).checked){
            for(let i = 0; i < this.listaPratiche.length; i++) {
                if(this.listaPratiche[i].stato_pratica.toUpperCase() == "DA VALIDARE"){
                    var esen: number = this.listadavalidarepratica.findIndex(
                            esenzione => esenzione.sk_pratica_esenzione == this.listaPratiche[i].sk_pratica_esenzione);

                    if(esen == -1)
                        this.listadavalidarepratica.push(this.listaPratiche[i]);
                }
            }
        }else {
            for(let i = 0; i < this.listaPratiche.length; i++) {
                if(this.listaPratiche[i].stato_pratica.toUpperCase() == "DA VALIDARE"){
                    var esen: number = this.listadavalidarepratica.findIndex(
                            esenzione => esenzione.sk_pratica_esenzione == this.listaPratiche[i].sk_pratica_esenzione);

                    if(esen != -1)
                        this.listadavalidarepratica.splice(esen,1);
                }
            }
        }
    }

    isCheckedAll(){
        this.selezionaTutti = false;
        if(this.listadavalidarepratica.length > 0){
            for(let i = 0; i < this.listaPratiche.length; i++) {
                if(this.listaPratiche[i].stato_pratica.toUpperCase() == "DA VALIDARE"){
                    var praticaCorrente = this.listaPratiche[i];
                    var esen: number = this.listadavalidarepratica.findIndex(
                            esenzione => esenzione.sk_pratica_esenzione == praticaCorrente.sk_pratica_esenzione);

                    if(esen == -1){
                        return;
                    }
                }
            }
            this.selezionaTutti = true;
        }
    }


    ValidaSelezionate() {
      let testoelencopratiche : string = "";
    let testoelencopraticheko : string = "";
        this.loading = true;
        let elencodavalidare = new Array<string>(this.listadavalidarepratica.length);
        for (var i = 0; i < this.listadavalidarepratica.length; i++) {
            elencodavalidare[i] = this.listadavalidarepratica[i].sk_pratica_esenzione.trim();
        }
                   this.esenzioneStoreService.getValidaSelezionate(elencodavalidare)
                       .subscribe( successCode => {
                           this.statusCode = successCode.status;
                           let body = successCode.json();
                           if ( this.statusCode == 200 ) {
                               if (body.message.toUpperCase() == "OK-PROCEDI VALIDA ESENZIONI SELEZIONATE"){
                                   this.listadavalidarepratica = [];

                                   $( ".esitoricercaselezionateko" ).hide();
                                   $('#validacertificato').modal('show');
                                   ( document.getElementById( "statofasepratica" ).innerHTML ) = "VALIDA";
                                   for (var i = 0; i < body.data.length; i++) {
                                       testoelencopratiche = testoelencopratiche + body.datanum[i].trim() + " ";
                                       let eseassistito = new esenzioneAssistito();
                                       eseassistito.sk_pratica_esenzione = body.data[i].trim();
                                       this.listadavalidarepratica[i]=eseassistito;
                                   }
                                 ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler validare le pratiche " + testoelencopratiche + "selezionate ?";
                                 $( ".nascondinota" ).hide();
                                     $( ".nascondinotaben" ).hide();
                                     $( ".nascondinotaser" ).hide();
                                     $( ".nascondinotaint" ).hide();
                                 ( document.getElementById( "notabeneficiario" ).innerHTML ) = null;
                                 ( document.getElementById( "notaservizio" ).innerHTML ) = null;
                                 ( document.getElementById( "notainterna" ).innerHTML ) = null;
                                 ( document.getElementById( "idaurabeneficiario" ).innerHTML ) = null;
                                 ( document.getElementById( "cfbeneficiario" ).innerHTML ) = null;
                                   this.loading = false;
                           }
                               else if (body.message.toUpperCase() == "OK PARZIALE-PROCEDI VALIDA ESENZIONI SELEZIONATE")
                                   {
                                   this.listadavalidarepratica = [];
                                   $( ".esitoricercaselezionateko" ).show();
                                   $('#validacertificato').modal('show');
                                   ( document.getElementById( "statofasepratica" ).innerHTML ) = "VALIDA";
                                   for (var i = 0; i < body.dataok.length; i++) {
                                       testoelencopratiche = testoelencopratiche + body.datanumok[i].trim() + " ";
                                       let eseassistito = new esenzioneAssistito();
                                       eseassistito.sk_pratica_esenzione = body.dataok[i].trim();
                                       this.listadavalidarepratica[i]=eseassistito;
                                   }
                                   for (var i = 0; i < body.datako.length; i++) {
                                       testoelencopraticheko = testoelencopraticheko + body.datanumko[i].trim() + " : " + body.messageko[i].trim() + "<br>";
                                   }
                                   ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler validare le pratiche " + testoelencopratiche + "selezionate ?";
                                   $( ".nascondinota" ).hide();
                                       $( ".nascondinotaben" ).hide();
                                       $( ".nascondinotaser" ).hide();
                                       $( ".nascondinotaint" ).hide();
                                   ( document.getElementById( "notabeneficiario" ).innerHTML ) = null;
                                   ( document.getElementById( "notaservizio" ).innerHTML ) = null;
                                   ( document.getElementById( "notainterna" ).innerHTML ) = null;
                                   ( document.getElementById( "idaurabeneficiario" ).innerHTML ) = null;
                                   ( document.getElementById( "cfbeneficiario" ).innerHTML ) = null;
                                   $( ".esitoricercaok" ).hide();
                                   $( ".esitoricercako" ).hide();
                                   $( ".esitoricercagenerico" ).hide();
                                   ( document.getElementById( "messaggioesitoricercaselezionateko" ).innerHTML ) = testoelencopraticheko;
                                   this.loading = false;
                                   }
                       }
                       },
                       errorCode => {
                           this.statusCode = errorCode.status;
                           var str = errorCode._body;
                           var objmsg = JSON.parse( str );
                           switch ( this.statusCode ) {
                               case 400:
                                   if (objmsg.message.toUpperCase() == "KO-PROCEDI VALIDA ESENZIONI SELEZIONATE"){
                                       for (var i = 0; i < objmsg.datanum.length; i++) {
                                           testoelencopraticheko = testoelencopraticheko + objmsg.data[i].trim() + " : " + objmsg.messageko[i].trim() + "<br>";
                                       }
                                       ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = testoelencopraticheko;
                                       $( ".esitoricercako" ).show();
                                       $( ".esitoricercaok" ).hide();
                                       $('#validacertificato').modal('hide');
                                       $( ".esitoricercagenerico" ).hide();
                                       this.loading = false;
                                       break;
                                   }
                                   else{
                                   ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                                   $( ".esitoricercako" ).show();
                                   $( ".esitoricercaok" ).hide();
                                   $('#validacertificato').modal('hide');
                                   $( ".esitoricercagenerico" ).hide();
                                   this.loading = false;
                                   break;
                                   }
                               default:
                                   ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                                   $( ".esitoricercako" ).hide();
                                   $( ".esitoricercaok" ).hide();
                                   $('#validacertificato').modal('hide');
                                   $( ".esitoricercagenerico" ).show();
                                   this.loading = false;
                                   break;
                           }
                       } )
    }

    ChiudiValida(){
        $('#validacertificato').modal('hide');
    }

    ProcediValidaSelezionate() {
        let a: string;
        this.loading = true;
        let testoelencopratiche : string = "";
        let testoelencopraticheko : string = "";
        let elencodavalidare = new Array<string>(this.listadavalidarepratica.length);
        for (var i = 0; i < this.listadavalidarepratica.length; i++) {
            elencodavalidare[i] = this.listadavalidarepratica[i].sk_pratica_esenzione.trim();
        }
                this.esenzioneStoreService.getProcediValidaSelezionate(elencodavalidare)
            .subscribe( successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();
                if ( this.statusCode == 200 ) {
                    if (body.message.toUpperCase() == "OK-PROCEDI VALIDA ESENZIONI SELEZIONATE"){
                        for (var i = 0; i < body.data.length; i++) {
                                testoelencopratiche = testoelencopratiche + body.datanum[i].trim() + " : " + body.messageok[i].trim() + "<br>";
                        }
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = testoelencopratiche;
                        $( ".esitoricercaok" ).show();
                        $('#validacertificato').modal('hide');
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.AnnullaRicerca();
                        this.loading = false;
                    }
                    else if (body.message.toUpperCase() == "OK PARZIALE-PROCEDI VALIDA ESENZIONI SELEZIONATE")
                    {
                        for (var i = 0; i < body.dataok.length; i++) {
                            testoelencopratiche = testoelencopratiche + body.datanumok[i].trim() + " : " + body.messageok[i].trim() + "<br>";
                        }
                        for (var i = 0; i < body.datako.length; i++) {
                            testoelencopraticheko = testoelencopraticheko + body.datanumko[i].trim() + " : " + body.messageko[i].trim() + "<br>";
                        }
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = testoelencopratiche;
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) =  testoelencopraticheko;
                        $( ".esitoricercaok" ).show();
                        $('#validacertificato').modal('hide');
                        $( ".esitoricercako" ).show();
                        $( ".esitoricercagenerico" ).hide();
                        this.AnnullaRicerca();
                        this.loading = false;
                    }
            }
            },
            errorCode => {
                this.statusCode = errorCode.status;
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                switch ( this.statusCode ) {
                    case 400:
                        if (objmsg.message.toUpperCase() == "KO-PROCEDI VALIDA ESENZIONI SELEZIONATE"){
                            for (var i = 0; i < objmsg.data.length; i++) {
                                testoelencopraticheko = testoelencopraticheko + objmsg.datanum[i].trim() + " : " + objmsg.messageko[i].trim() + "<br>";
                            }
                            ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = testoelencopraticheko;
                            $( ".esitoricercako" ).show();
                            $( ".esitoricercaok" ).hide();
                            $('#validacertificato').modal('hide');
                            $( ".esitoricercagenerico" ).hide();
                            this.loading = false;
                            break;
                        }
                        else{
                            ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                            $( ".esitoricercako" ).show();
                            $( ".esitoricercaok" ).hide();
                            $('#validacertificato').modal('hide');
                            $( ".esitoricercagenerico" ).hide();
                            this.loading = false;
                            break;
                        }
                    default:
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercaok" ).hide();
                        $('#validacertificato').modal('hide');
                        $( ".esitoricercagenerico" ).show();
                        this.loading = false;
                        break;
                }
            } )

    }

    getCronologia(index:number) {
        this.storicoEsenzione = [];
        this.loading = true;
        this.esenzioneStoreService.getCronologia(this.listaPratiche[index].beneficiario.codice_fiscale, this.listaPratiche[index].sk_pratica_esenzione)
        .subscribe( response => {
            this.storicoEsenzione = response;
            this.loading = false;
       });
    }
    scaricaAttestato(index:number) {
        this.loading = true;
        this.esenzioneStoreService.scaricaAttestatoLegale(this.listaPratiche[index].beneficiario.codice_fiscale).subscribe(
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
        var str = errorCode._body;
        var objmsg = JSON.parse( str );
        console.log(objmsg);
        this.loading = false;
      }
    );
    }
}
