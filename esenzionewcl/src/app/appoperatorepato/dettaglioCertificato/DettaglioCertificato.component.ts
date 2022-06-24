import { Component, ɵConsole, ViewChild, ElementRef, SystemJsNgModuleLoader, OnInit } from "@angular/core";
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import * as $ from 'jquery';
import { Http, RequestOptions, Headers } from "@angular/http";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { Assistito } from "app/assistito/assistito";
import { UserInfo } from "app/assistito/userinfo";
import { certificato } from "app/esenzione/certificato";
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { CertificatoPatologia } from "app/esenzione/CertificatoPatologia";
import { Router, ActivatedRoute } from "@angular/router";
import { FiltriDettaglioEsenzione } from "app/esenzione/FiltriDettaglioEsenzione";
import { DocumentiEsenzione } from "app/esenzione-wizard/data/esenFormData.model";
import { Observable } from "rxjs";
import { ConfigService } from "app/services/config.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { FiltroValidaEsenzionePatologia } from "app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia";
import { beneficiario } from "app/assistito/beneficiario";
declare var $;
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-dettaglio-certificato',
    templateUrl: './DettaglioCertificato.component.html',

})
export class DettaglioCertificato  implements OnInit {
    public isOn : boolean = false;

    loading = false;
    esenzioneCreata = false;
    esenzioneCreataValidata = false;
    num_pratica_da_validare = '';
    id_pratica_da_validare = '';

    statusCode: number;


    ricValida = new FiltroValidaEsenzionePatologia();

    hidden = true;
    errorMessage: string;
    idCertificato: string;
    cfBeneficiario: string;

    dettaglioCertificato: CertificatoPatologia;
    abilitatoRichiedi: boolean = false;
    abilitatoRichiediValida: boolean = false;

    ngOnInit() {
        this.hidden = true;
        this.dettaglioCertificato = this.esenzioneStoreService.dettaglioCertificato;
        this.abilitatoRichiedi = this.assistitoStoreService.abilitatoAdAzione("17");
        this.abilitatoRichiediValida = this.assistitoStoreService.abilitatoAdAzione("27");
        window.scrollTo(0, 0);
    }

    constructor(
        private http: Http,

        private esenzioneStoreService: EsenzioneStoreService,
        private assistitoStoreService: AssistitoStoreService,

        private workFlowService: EsenWorkflowService,
        private router: Router,
        private route: ActivatedRoute,
        private config: ConfigService
    ) {

    }

    getDettaglioEsenzione() {
        this.loading = true;
        let filter = new FiltriDettaglioEsenzione;
        filter.cf_beneficiario = this.dettaglioCertificato.beneficiario.codice_fiscale;
        if(this.dettaglioCertificato.sk_pratica != null)
            filter.sk_esenzione = this.dettaglioCertificato.sk_pratica.toString();
        if(this.dettaglioCertificato.data_emissione != null)
            filter.data_emissione = this.dettaglioCertificato.data_emissione;
        if(this.dettaglioCertificato.esenzione != null && this.dettaglioCertificato.esenzione.codice != null)
            filter.cod_esenzione = this.dettaglioCertificato.esenzione.codice;
        this.esenzioneStoreService.getDettaglioEsenzione(filter)
        .subscribe( (res) => {
            this.router.navigate( ["../dettaglio-esenzione"], {relativeTo: this.route,skipLocationChange: true} );
            this.loading = false;

        },
        errorCode => {
            var str = errorCode._body;
            var objmsg = JSON.parse( str );
            console.log(objmsg);
            this.loading = false;
        } );

    }

    inviaRichiestaEsenzione(){

        let malattia = {
            "gruppoEsenzione": "",
            "codDiagnosi": this.dettaglioCertificato.diagnosi.codice,
            "codEsenzione": this.dettaglioCertificato.esenzione.codice,
        };

        let lista_documenti: DocumentiEsenzione[] = new Array();

        let documento : DocumentiEsenzione =
        {
                documentoId: this.dettaglioCertificato.id_certificato,
                filename: "",
                fileBase64: "",
                tipologia: "",
                dataRilascio: "",
                dataFineValidita: "",
                descrizione: ""
        };

        lista_documenti.push(documento);

        let assist: Assistito = {
            codFiscale : this.dettaglioCertificato.beneficiario.codice_fiscale,
            cognome : this.dettaglioCertificato.beneficiario.cognome,
            nome : this.dettaglioCertificato.beneficiario.nome,
            codSesso : '',
            dataNascita : '',
            comuneNascita : '',
            provinciaNascita : '',
            idAura : this.dettaglioCertificato.beneficiario.id_aura,
            stato: '',
            datascadenzaSSN: '',
            codStatoNascita: '',
            codAsl: ''
        }

        let postData = {
                "assistito": assist,
                "malattia": malattia,
                "documenti" : lista_documenti,
                "noteServizio": "",
                "noteInterne": ""
                };

        this.loading=true;

        this.esenzioneStoreService.creaEsenzione(JSON.stringify(postData))
            .subscribe(
                result => {
                    console.log("success");
                    let statusCode = result.status;
                    var str = result._body;
                    var objmsg = JSON.parse( str );

                    this.loading = false;
                    this.hidden = true;
                    this.workFlowService.setEsenNumber(objmsg.num_esenzione);
                    this.workFlowService.setFromCert(true);
                    this.esenzioneCreata = true;
                 },
                 error => {
                     console.log('error occured!');

                     let statusCode = error.status;

                     if(statusCode === 0){
                         this.hidden=false;
                         this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                     }else{
                         var str = error._body;
                         var objmsg = JSON.parse( str );
                         this.hidden=false;
                         this.errorMessage = objmsg.message;
                     }

                     this.loading = false;
                  }
         );
    }



    inviaRichiestaCreaValidaEsenzione(){
        let malattia = {
            "gruppoEsenzione": "",
            "codDiagnosi": this.dettaglioCertificato.diagnosi.codice,
            "codEsenzione": this.dettaglioCertificato.esenzione.codice,
        };

        let lista_documenti: DocumentiEsenzione[] = new Array();

        let documento : DocumentiEsenzione =
        {
                documentoId: this.dettaglioCertificato.id_certificato,
                filename: "",
                fileBase64: "",
                tipologia: "",
                dataRilascio: "",
                dataFineValidita: "",
                descrizione: ""
        };

        lista_documenti.push(documento);

        let assist: Assistito = {
            codFiscale : this.dettaglioCertificato.beneficiario.codice_fiscale,
            cognome : this.dettaglioCertificato.beneficiario.cognome,
            nome : this.dettaglioCertificato.beneficiario.nome,
            codSesso : '',
            dataNascita : '',
            comuneNascita : '',
            provinciaNascita : '',
            idAura : this.dettaglioCertificato.beneficiario.id_aura,
            stato: '',
            datascadenzaSSN: '',
            codStatoNascita: '',
            codAsl: ''
        }

        let postData = {
                "assistito": assist,
                "malattia": malattia,
                "documenti" : lista_documenti,
                "noteServizio": "",
                "noteInterne": ""
                };

        this.loading=true;

        this.esenzioneStoreService.creaEsenzione(JSON.stringify(postData))
            .subscribe(
                result => {
                    console.log("success");
                    let statusCode = result.status;
                    var str = result._body;
                    var objmsg = JSON.parse( str );

                    this.hidden = true;
                    this.id_pratica_da_validare = objmsg.id_esenzione;
                    this.num_pratica_da_validare = objmsg.num_esenzione;


                    this.dettaglioCertificato.utilizzabile = false;


                    this.checkApprovaEsenzione();
                },
                 error => {
                     console.log('error occured!');

                     let statusCode = error.status;

                     if(statusCode === 0){
                         this.hidden=false;
                         this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                     }else{
                         var str = error._body;
                         var objmsg = JSON.parse( str );
                         this.hidden=false;
                         this.errorMessage = objmsg.message;
                     }

                     this.loading = false;
                  }
         );
    }

    checkApprovaEsenzione(){
        let assistito = new beneficiario();
        assistito.codice_fiscale = this.dettaglioCertificato.beneficiario.codice_fiscale;
        assistito.cognome = "";
        assistito.nome = "";
        assistito.id_aura = this.dettaglioCertificato.beneficiario.id_aura ;
        this.ricValida.beneficiario = assistito;

        this.ricValida.codEsenzione = this.dettaglioCertificato.esenzione.codice;
        this.ricValida.dataFineValidita = "";
        this.ricValida.dataInizioValidita = "";
        this.ricValida.nota ="";
        this.ricValida.notabeneficiario ="";
        this.ricValida.notainterna = "";
        this.ricValida.skPraticaEsenzione = this.id_pratica_da_validare;

        this.esenzioneStoreService.getApprovaEsenzione(this.ricValida)
        .subscribe(
            successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();

                if (body.message.toUpperCase() == "OK-PROCEDI APPROVA"){

                    $('#validacertificato').modal('show');
                    this.loading = false;
                }
                else{

                    this.hidden=false;
                    this.errorMessage = body.message;

                    this.loading = false;
                }
            },
            error => {
                console.log('error occured!');

                let statusCode = error.status;

                if(statusCode === 0){
                    this.hidden=false;
                    this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                }else{
                    var str = error._body;
                    var objmsg = JSON.parse( str );
                    this.hidden=false;
                    this.errorMessage = objmsg.message;
                }
                this.loading = false;
             }
        )
    }



    procediApprovaEsenzione(){
        this.chiudiValida();
        this.loading = true;

        this.esenzioneStoreService.getProcediApprovaEsenzione(this.ricValida)
        .subscribe(
            successCode => {
                this.statusCode = successCode.status;
                let body = successCode.json();

                this.checkValidaEsenzione()

            },
            error => {
                console.log('error occured!');

                let statusCode = error.status;

                if(statusCode === 0){
                    this.hidden=false;
                    this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                }else{
                    var str = error._body;
                    var objmsg = JSON.parse( str );
                    this.hidden=false;
                    this.errorMessage = objmsg.message;
                }
                this.loading = false;
             }
        )
    }


    checkValidaEsenzione() {
        this.ricValida.azione = "VALIDA";
        this.esenzioneStoreService.getValidaEsenzione(this.ricValida)
            .subscribe(
                successCode => {
                    this.statusCode = successCode.status;
                    let body = successCode.json();

                    if (body.message.toUpperCase() == "OK-PROCEDI VALIDA"){

                        this.procediValidaEsenzione();
                    }
                    else{

                        this.hidden=false;
                        this.errorMessage = body.message;

                        this.loading = false;
                    }
                },
                error => {
                    console.log('error occured!');

                    let statusCode = error.status;

                    if(statusCode === 0){
                        this.hidden=false;
                        this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                    }else{
                        var str = error._body;
                        var objmsg = JSON.parse( str );
                        this.hidden=false;
                        this.errorMessage = objmsg.message;
                    }
                    this.loading = false;
                 }
            )
    }

    procediValidaEsenzione(){
            this.ricValida.azione = "VALIDA";
            this.esenzioneStoreService.getProcediValidaEsenzione(this.ricValida).subscribe(
                successCode => {
                    console.log("success");
                    this.esenzioneCreataValidata = true;

                    this.loading = false;
                    this.hidden = true;
                    this.workFlowService.setEsenNumber(this.num_pratica_da_validare);
                },
                error => {
                    console.log('error occured!');

                    let statusCode = error.status;

                    if(statusCode === 0){
                        this.hidden=false;
                        this.errorMessage = "ATTENZIONE! Servizio non disponibile. Riprovare più tardi.";
                    }else{
                        var str = error._body;
                        var objmsg = JSON.parse( str );
                        this.hidden=false;
                        this.errorMessage = objmsg.message;
                    }
                    this.loading = false;
                 }
            )
    }




    chiudiValida(){
        $('#validacertificato').modal('hide');
    }

    scaricaCertificato() {
        this.loading = true;
        this.esenzioneStoreService.scaricaCertificato(this.dettaglioCertificato.beneficiario.codice_fiscale, this.dettaglioCertificato.id_certificato).subscribe(
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

    returnHome() {
        this.router.navigate( ["../home"], {relativeTo: this.route, skipLocationChange: true} );
    }
}
