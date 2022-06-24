import { Component, ɵConsole, ViewChild, ElementRef, SystemJsNgModuleLoader, OnInit } from "@angular/core";
import { CittadinoRicerca } from "app/assistito/cittadinoricerca";
import * as $ from 'jquery';
import { Http } from "@angular/http";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { Assistito } from "app/assistito/assistito";
import { UserInfo } from "app/assistito/userinfo";
import { FiltroValidaEsenzionePatologia } from "app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia";
import { certificato } from "app/esenzione/certificato";
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { CertificatoPatologia } from "app/esenzione/CertificatoPatologia";
import { Router, ActivatedRoute } from "@angular/router";
import { PraticaEsenzione } from "app/esenzione/PraticaEsenzione";
import { beneficiario } from "app/assistito/beneficiario";
import { FiltriDettaglioEsenzione } from "app/esenzione/FiltriDettaglioEsenzione";
import { Motivazione } from "app/esenzione/Motivazione";
import { MotivazioneAnnRevRes } from "app/esenzione/MotivazioneAnnRevRes";
import { RichiestaRettificaDati } from "app/esenzione/richiestaRettificaDati";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { GruppoEsenzione, TipologiaCert } from "app/certificato-wizard/data/formData.model";
import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import { Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";
import { StoricoEsenzione } from "app/esenzione/storicoEsenzione";
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { DocumentiEsenzione } from "app/esenzione-wizard/data/esenFormData.model";
import { Prestazioni } from "app/esenzione/Prestazioni";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
declare var $;

@Component( {
    selector: 'app-dettaglio-esenzione',
    templateUrl: './DettaglioEsenzione.component.html',

})
export class DettaglioEsenzione  implements OnInit {
    public isOn : boolean = false;

    loading = false;

    statusCode: number;

    idEsenzione: string;
    cfBeneficiario: string;
    utente: UserInfo;
    dettaglioEsenzione: PraticaEsenzione;
    private operazione: string;
    statoPratica: string = "";
    utenteMedicoCas:boolean = false;
    utenteOperatoreAslDistretto:boolean = false;
    utenteMedicoSpecialistaAslDistretto:boolean = false;
    utenteAmministratoreCsi:boolean = false;
    motivazioni: Motivazione[] = [];
    sceltaModal: string = "";
    sceltaModalVerb: string = "";
    notaBen: string = "";
    notaSer: string = "";
    notaInt: string = "";
    filtroSalvaInBozza: FiltroValidaEsenzionePatologia;
    patologia: string = "";
    storicoEsenzione: StoricoEsenzione[] = [];
    lista_documenti: DocumentiEsenzione[];
    maxLength = 1200;
    estensibile:boolean = true;

    constructor( //http: Http,

            private esenzioneStoreService: EsenzioneStoreService,
            private assistitoStoreService: AssistitoStoreService,
            private workflowService: WorkflowService,
            private formDataService: FormDataService,
            private esenFormDataService: EsenFormDataService,

            private router: Router,
            private route: ActivatedRoute,


        ) {

        }

    ngOnInit() {
        this.loading = true;
        $( ".esitoricercaok" ).hide();
        $( ".esitoricercako" ).hide();
        $( ".esitoricercagenerico" ).hide();
        $( ".esitoricercarinnovako" ).hide();

        this.utenteMedicoCas = false;
        this.utenteOperatoreAslDistretto = false;
        this.utenteMedicoSpecialistaAslDistretto = false;
        this.utenteAmministratoreCsi = false;

        this.dettaglioEsenzione = this.esenzioneStoreService.dettaglioEsenzione;
        this.statoPratica = this.dettaglioEsenzione.stato.codice;
        this.utente = this.assistitoStoreService.getUser();
        this.ApriCronologia();
        this.utente.ruoli.forEach(ruolo => {
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
        if(this.dettaglioEsenzione.malattia != null && this.dettaglioEsenzione.malattia.prestazioni != null && this.dettaglioEsenzione.malattia.prestazioni.length > 0) {
            this.estensibile = this.isEstensibile(this.dettaglioEsenzione.malattia.prestazioni[0]);
            this.showLess(this.dettaglioEsenzione.malattia.prestazioni[0]);
        }

        //per rinnova
        let gruppo: GruppoEsenzione = {gruppoEsenzione: this.dettaglioEsenzione.tipologia.codice};
        this.esenFormDataService.setGruppoEsenzione(gruppo);

        console.log(this.maxLength);
        this.filtroSalvaInBozza = new FiltroValidaEsenzionePatologia();
        window.scrollTo(0, 0);
    }

    isEstensibile(prestazione : Prestazioni) {
        let l = (prestazione.descrizione.match(new RegExp('\n', "g")) || []).length;
        console.log(l);
        return l > 4;
    }

    showMore(prestazione : Prestazioni) {
        this.maxLength = prestazione.descrizione.length;
    }

    showLess(prestazione: Prestazioni) {
        this.maxLength = prestazione.descrizione.split('\n', 4).join('\n').length;
    }

    postCaricaDettaglio() {
        this.loading = true;
        this.utenteMedicoCas = false;
        this.utenteOperatoreAslDistretto = false;
        this.utenteMedicoSpecialistaAslDistretto = false;
        this.utenteAmministratoreCsi = false;

        this.dettaglioEsenzione = this.esenzioneStoreService.dettaglioEsenzione;
        this.statoPratica = this.dettaglioEsenzione.stato.codice;
        this.utente = this.assistitoStoreService.getUser();
        this.ApriCronologia();
        this.utente.ruoli.forEach(ruolo => {
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

        this.filtroSalvaInBozza = new FiltroValidaEsenzionePatologia();

        window.scrollTo(0, 0);
    }


    ApriCronologia(){
        if (this.dettaglioEsenzione.id!=null){
        this.storicoEsenzione = [];
        this.esenzioneStoreService.getCronologia(this.dettaglioEsenzione.beneficiario.codice_fiscale, this.dettaglioEsenzione.id)
        .subscribe( response => {
            this.storicoEsenzione = response;
            this.loading = false;
       });
    }
        else
            this.loading = false;
    }

 getDettaglioEsenzione() {


        let filtro = new FiltriDettaglioEsenzione();
        if (this.dettaglioEsenzione.beneficiario.codice_fiscale!=null)
        filtro.cf_beneficiario = this.dettaglioEsenzione.beneficiario.codice_fiscale;
        if (this.dettaglioEsenzione.codice.codice!=null)
        filtro.cod_esenzione = this.dettaglioEsenzione.codice.codice;
        if (this.dettaglioEsenzione.data_inizio_validita!=null)
        filtro.data_emissione = this.dettaglioEsenzione.data_inizio_validita;
        if (this.dettaglioEsenzione.id!=null)
        filtro.sk_esenzione = this.dettaglioEsenzione.id;

        this.esenzioneStoreService.getDettaglioEsenzione(filtro)
        .subscribe(data => {
            if(data._body != null && data._body) {

                ( (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value="");
                ( (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value="");
                ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value="");
                this.postCaricaDettaglio();
            }

        },
         errorCode => {
             var str = errorCode._body;
             var objmsg = JSON.parse( str );
             console.log(objmsg);
             this.loading = false;
         }
        );

    }

 getDettaglioEsenzioneRinnova(filtro : FiltriDettaglioEsenzione) {

     this.esenzioneStoreService.getDettaglioEsenzione(filtro)
     .subscribe(data => {
         if(data._body != null && data._body) {

             ( (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value="");
             ( (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value="");
             ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value="");
             this.postCaricaDettaglio();
         }

     },
      errorCode => {
          var str = errorCode._body;
          var objmsg = JSON.parse( str );
          console.log(objmsg);
          this.loading = false;
      }
     );

 }

    scaricaAllegati() {
        this.loading = true;
        this.esenzioneStoreService.createZipAllegati(this.dettaglioEsenzione.beneficiario.codice_fiscale, this.dettaglioEsenzione.id).subscribe(
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
            this.loading = false;
            var str = errorCode._body;
            var objmsg = JSON.parse( str );
            console.log(objmsg);
        }
    );
    }

    scaricaCertificato() {
        $( ".esitoricercako" ).hide();
        this.loading = true;
        this.esenzioneStoreService.scaricaCertificato(this.dettaglioEsenzione.beneficiario.codice_fiscale, this.dettaglioEsenzione.certificato_id).subscribe(
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
            var str = errorCode.statusText;

            console.log(str);
            $( ".esitoricercako" ).show();
            ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = str;
            this.loading = false;
        }
    );
    }

    ChiudiValida(){
        $('#validacertificato').modal('hide');
    }

     ValidaCertificato(azione : string) {
         this.operazione=azione;
         let a: string;
         let ricValida = new FiltroValidaEsenzionePatologia();
         this.loading = true;
         let assistito = new beneficiario();
         if (this.dettaglioEsenzione.beneficiario.codice_fiscale!="" && this.dettaglioEsenzione.beneficiario.codice_fiscale!=null)
         assistito.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
         assistito.cognome = "";
         assistito.nome = "";
         if (this.dettaglioEsenzione.beneficiario.id_aura!="" && this.dettaglioEsenzione.beneficiario.id_aura!=null)
             assistito.id_aura = this.dettaglioEsenzione.beneficiario.id_aura ;
         ricValida.beneficiario = assistito;
         if (this.dettaglioEsenzione.codice.codice!=null && this.dettaglioEsenzione.codice.codice!="")
         ricValida.codEsenzione = this.dettaglioEsenzione.codice.codice;
         ricValida.dataFineValidita = "";
         ricValida.dataInizioValidita = "";
         ricValida.azione = azione;
         if ( (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value!="")
             ricValida.nota = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();
         else
             ricValida.nota ="";
         if ( (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value!="")
             ricValida.notabeneficiario = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
         else
             ricValida.notabeneficiario ="";
         if ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value!="")
            ricValida.notainterna = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();
         else
         ricValida.notainterna = "";
         if (this.dettaglioEsenzione.id!=null && this.dettaglioEsenzione.id!="")
         ricValida.skPraticaEsenzione = this.dettaglioEsenzione.id;
         if (azione=="VALIDA" || azione=="APPROVAEVALIDA"){
         this.esenzioneStoreService.getValidaEsenzione(ricValida)
             .subscribe( successCode => {
                 this.statusCode = successCode.status;
                 let body = successCode.json();
                 if ( this.statusCode == 200 ) {
                     if (body.message.toUpperCase() == "OK-PROCEDI VALIDA"){
                            //caso in cui deve apparire la modal
                         $('#validacertificato').modal('show');
                         if (azione=="VALIDA"){
                         ( document.getElementById( "statofasepratica" ).innerHTML ) = "VALIDA";
                       ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler validare la pratica " + this.dettaglioEsenzione.numero_pratica +" ?";
                         }
                         else  if (azione=="APPROVAEVALIDA"){
                             ( document.getElementById( "statofasepratica" ).innerHTML ) = "APPROVA E VALIDA";
                             ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler approvare e validare la pratica " + this.dettaglioEsenzione.numero_pratica +" ?";
                         }
                       $( ".nascondinota" ).hide();
                       if (ricValida.notabeneficiario.trim()=="")
                           $( ".nascondinotaben" ).hide();
                       else {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaben" ).show();
                       }
                       if (ricValida.nota.trim()=="")
                           $( ".nascondinotaser" ).hide();
                       else {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaser" ).show();
                       }
                       if (ricValida.notainterna.trim()=="")
                           $( ".nascondinotaint" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaint" ).show();
                       }
                       ( document.getElementById( "notabeneficiario" ).innerHTML ) = ricValida.notabeneficiario.trim();
                       ( document.getElementById( "notaservizio" ).innerHTML ) = ricValida.nota.trim();
                       ( document.getElementById( "notainterna" ).innerHTML ) = ricValida.notainterna.trim();
                       ( document.getElementById( "idaurabeneficiario" ).innerHTML ) = ricValida.beneficiario.id_aura;
                       ( document.getElementById( "cfbeneficiario" ).innerHTML ) = ricValida.beneficiario.codice_fiscale;
                         this.loading = false;
                 }
                     else
                         {

                         ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                         $( ".esitoricercaok" ).show();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercako" ).hide();
                         $( ".esitoricercagenerico" ).hide();
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
                         ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                         $( ".esitoricercako" ).show();
                         $( ".esitoricercaok" ).hide();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercagenerico" ).hide();
                         this.loading = false;
                         break;
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
         else if (azione=="APPROVA"){
             this.esenzioneStoreService.getApprovaEsenzione(ricValida)
             .subscribe( successCode => {
                 this.statusCode = successCode.status;
                 let body = successCode.json();
                 if ( this.statusCode == 200 ) {
                     if (body.message.toUpperCase() == "OK-PROCEDI APPROVA"){

                         ( document.getElementById( "statofasepratica" ).innerHTML ) = "APPROVA";
                         $('#validacertificato').modal('show');
                       ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler approvare la pratica " + this.dettaglioEsenzione.numero_pratica +" ?";
                       $( ".nascondinota" ).hide();
                       if (ricValida.notabeneficiario.trim()=="")
                           $( ".nascondinotaben" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaben" ).show();
                       }
                       if (ricValida.nota.trim()=="")
                           $( ".nascondinotaser" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaser" ).show();
                       }
                       if (ricValida.notainterna.trim()=="")
                           $( ".nascondinotaint" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaint" ).show();
                       }
                       ( document.getElementById( "notabeneficiario" ).innerHTML ) = ricValida.notabeneficiario.trim();
                       ( document.getElementById( "notaservizio" ).innerHTML ) = ricValida.nota.trim();
                       ( document.getElementById( "notainterna" ).innerHTML ) = ricValida.notainterna.trim();
                       ( document.getElementById( "idaurabeneficiario" ).innerHTML ) = ricValida.beneficiario.id_aura;
                       ( document.getElementById( "cfbeneficiario" ).innerHTML ) = ricValida.beneficiario.codice_fiscale;
                         this.loading = false;
                 }
                     else
                         {

                         ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                         $( ".esitoricercaok" ).show();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercako" ).hide();
                         $( ".esitoricercagenerico" ).hide();
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
                         ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                         $( ".esitoricercako" ).show();
                         $( ".esitoricercaok" ).hide();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercagenerico" ).hide();
                         this.loading = false;
                         break;
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
         else if (azione=="RINNOVA"){
             this.esenzioneStoreService.getRinnovaEsenzione(ricValida)
             .subscribe( successCode => {
                 this.statusCode = successCode.status;
                 let body = successCode.json();
                 if ( this.statusCode == 200 ) {
                     if (body.message.toUpperCase() == "OK-PROCEDI RINNOVA ESENZIONE"){

                         ( document.getElementById( "statofasepratica" ).innerHTML ) = azione;
                         $('#validacertificato').modal('show');
                       ( document.getElementById( "skpratica" ).innerHTML ) = "Sei sicuro di voler rinnovare la pratica " + this.dettaglioEsenzione.numero_pratica +" ?";
                       $( ".nascondinota" ).hide();
                       if (ricValida.notabeneficiario.trim()=="")
                           $( ".nascondinotaben" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaben" ).show();
                       }
                       if (ricValida.nota.trim()=="")
                           $( ".nascondinotaser" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaser" ).show();
                       }
                       if (ricValida.notainterna.trim()=="")
                           $( ".nascondinotaint" ).hide();
                       else
                       {
                           $( ".nascondinota" ).show();
                           $( ".nascondinotaint" ).show();
                       }
                       ( document.getElementById( "notabeneficiario" ).innerHTML ) = ricValida.notabeneficiario.trim();
                       ( document.getElementById( "notaservizio" ).innerHTML ) = ricValida.nota.trim();
                       ( document.getElementById( "notainterna" ).innerHTML ) = ricValida.notainterna.trim();
                       ( document.getElementById( "idaurabeneficiario" ).innerHTML ) = ricValida.beneficiario.id_aura;
                       ( document.getElementById( "cfbeneficiario" ).innerHTML ) = ricValida.beneficiario.codice_fiscale;
                         this.loading = false;
                 }
                     else
                         {

                         ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                         $( ".esitoricercaok" ).show();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercako" ).hide();
                         $( ".esitoricercagenerico" ).hide();
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
                         ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                         $( ".esitoricercako" ).show();
                         $( ".esitoricercaok" ).hide();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercagenerico" ).hide();
                         this.loading = false;
                         break;
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
     }

     ProcediValidaCertificato(azione : string) {

         let a: string;
         let ricValida = new FiltroValidaEsenzionePatologia();
         this.loading = true;
         this.lista_documenti = this.esenFormDataService.getDocumentiEsen();
         let assistito = new beneficiario();
         if (this.dettaglioEsenzione.beneficiario.codice_fiscale!="" && this.dettaglioEsenzione.beneficiario.codice_fiscale!=null)
             assistito.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
             assistito.cognome = "";
             assistito.nome = "";
             if (this.dettaglioEsenzione.beneficiario.id_aura!="" && this.dettaglioEsenzione.beneficiario.id_aura!=null)
                 assistito.id_aura = this.dettaglioEsenzione.beneficiario.id_aura ;
             ricValida.beneficiario = assistito;
             if (this.dettaglioEsenzione.codice.codice!=null && this.dettaglioEsenzione.codice.codice!="")
             ricValida.codEsenzione = this.dettaglioEsenzione.codice.codice;
             ricValida.dataFineValidita = "";
             ricValida.dataInizioValidita = "";
             ricValida.azione = azione;
             ricValida.documentiallegati = this.lista_documenti;
             if ( (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value!="")
                 ricValida.nota = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();
             else
                 ricValida.nota ="";
             if ( (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value!="")
                 ricValida.notabeneficiario = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
             else
                 ricValida.notabeneficiario ="";
             if ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value!="")
                ricValida.notainterna = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();
             else
             ricValida.notainterna = "";
             if (this.dettaglioEsenzione.id!=null && this.dettaglioEsenzione.id!="")
             ricValida.skPraticaEsenzione = this.dettaglioEsenzione.id;
             if (azione=="VALIDA" || azione=="APPROVAEVALIDA"){
                 this.esenzioneStoreService.getProcediValidaEsenzione(ricValida)
             .subscribe( successCode => {
                 this.statusCode = successCode.status;
                 let body = successCode.json();
                 if ( this.statusCode == 200 ) {

                         ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                         $( ".esitoricercaok" ).show();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercako" ).hide();
                         $( ".esitoricercagenerico" ).hide();
                         $( ".esitoricercarinnovako" ).hide();
                         this.getDettaglioEsenzione();


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
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercagenerico" ).hide();
                         $( ".esitoricercarinnovako" ).hide();
                         this.loading = false;
                         break;
                     default:
                         ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                         $( ".esitoricercako" ).hide();
                         $( ".esitoricercaok" ).hide();
                         $('#validacertificato').modal('hide');
                         $( ".esitoricercagenerico" ).show();
                         $( ".esitoricercarinnovako" ).hide();
                         this.loading = false;
                         break;
                 }
             } )
             }
             else if (azione=="APPROVA"){
                 this.esenzioneStoreService.getProcediApprovaEsenzione(ricValida)
                 .subscribe( successCode => {
                     this.statusCode = successCode.status;
                     let body = successCode.json();
                     if ( this.statusCode == 200 ) {

                             ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                             $( ".esitoricercaok" ).show();
                             $('#validacertificato').modal('hide');
                             $( ".esitoricercako" ).hide();
                             $( ".esitoricercagenerico" ).hide();
                             $( ".esitoricercarinnovako" ).hide();
                             this.getDettaglioEsenzione();

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
                             $('#validacertificato').modal('hide');
                             $( ".esitoricercagenerico" ).hide();
                             $( ".esitoricercarinnovako" ).hide();
                             this.loading = false;
                             break;
                         default:
                             ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                             $( ".esitoricercako" ).hide();
                             $( ".esitoricercaok" ).hide();
                             $('#validacertificato').modal('hide');
                             $( ".esitoricercagenerico" ).show();
                             $( ".esitoricercarinnovako" ).hide();
                             this.loading = false;
                             break;
                     }
                 } )
             }
             else if (azione=="RINNOVA"){
                 this.esenzioneStoreService.getProcediRinnovaEsenzione(ricValida)
                 .subscribe( successCode => {
                     this.statusCode = successCode.status;
                     let body = successCode.json();
                     if ( this.statusCode == 200 ) {

                             ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
                             $( ".esitoricercaok" ).show();
                             $('#validacertificato').modal('hide');
                             $( ".esitoricercako" ).hide();
                             $( ".esitoricercagenerico" ).hide();
                             $( ".esitoricercarinnovako" ).hide();
                               let filtro = new FiltriDettaglioEsenzione();
                             if (body.data.cf_beneficiario!=null)
                             filtro.cf_beneficiario = body.data.cf_beneficiario;
                             if (body.data.codEsenzione!=null)
                             filtro.cod_esenzione = body.data.codEsenzione;
                             if (body.data.data_emissione!=null)
                             filtro.data_emissione = body.data.data_emissione;
                             if (body.data.sk_esenzione!=null)
                             filtro.sk_esenzione = body.data.sk_esenzione;
                             this.getDettaglioEsenzioneRinnova(filtro);

                 }
                 },
                 errorCode => {
                     this.statusCode = errorCode.status;
                     var str = errorCode._body;
                     var objmsg = JSON.parse( str );
                     switch ( this.statusCode ) {
                         case 400:
                             ( document.getElementById( "messaggioesitoricercarinnovako" ).innerHTML ) = objmsg.message;
                             $( ".esitoricercako" ).hide();
                             $( ".esitoricercaok" ).hide();

                             $( ".esitoricercagenerico" ).hide();
                             $( ".esitoricercarinnovako" ).show();
                             this.loading = false;
                             break;
                         default:
                             ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                             $( ".esitoricercako" ).hide();
                             $( ".esitoricercaok" ).hide();
                             $('#validacertificato').modal('hide');
                             $( ".esitoricercagenerico" ).show();
                             $( ".esitoricercarinnovako" ).hide();
                             this.loading = false;
                             break;
                     }
                 } )
             }

     }

  scaricaAttestatoLegale() {
    this.loading = true;
    this.esenzioneStoreService.scaricaAttestatoLegale(this.dettaglioEsenzione.beneficiario.codice_fiscale).subscribe(
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

  verificaRettifica(stato: string) {
    this.loading = true;
    let request = new RichiestaRettificaDati();
    let ben = new beneficiario();
    let filtri: MotivazioneAnnRevRes = new MotivazioneAnnRevRes();

    ben.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
    request.beneficiario = ben;

    request.notabeneficiario = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
    request.notainterna = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();
    request.nota = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();

    request.skPraticaEsenzione = this.dettaglioEsenzione.id;
    if(stato == "REM")
        request.richiestaRettificaDestinatario = "MEDICO";
    else if(stato == "REO")
        request.richiestaRettificaDestinatario = "CITTADINO";
    this.esenzioneStoreService.setRichiestaRettifica(request)
    .subscribe(res => {
        let body = res.json();
        if(body.code == 200) {
            this.loading = false;
            this.prepareModal(stato);
        }
    }, errorCode => {
        this.statusCode = errorCode.status;
        var str = errorCode._body;
        var objmsg = JSON.parse( str );
        switch ( this.statusCode ) {
            case 400:
                ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                $( ".esitoricercako" ).show();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercagenerico" ).hide();
                this.loading = false;
                break;
            default:
                ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                $( ".esitoricercako" ).hide();
                $( ".esitoricercaok" ).hide();
                $( ".esitoricercagenerico" ).show();
                this.loading = false;
                break;
        }
    })
  }

  prepareModal(stato:string) {
    this.loading = true;
    this.sceltaModal = stato == "REO" ? "RETTIFICA BENEFICIARIO": stato == "REM" ? "RETTIFICA MEDICO": stato;
    this.sceltaModalVerb = stato == "ANNULLA"? "annullare" : stato == "REO"? "rettificare" : stato == "REM"? "rettificare" : stato == "REVOCA" ? "revocare" : stato == "RESPINGI" ? "respingere": null;
    this.esenzioneStoreService.getMotivazioni(stato.substring(0,3)).subscribe(
        (res) => {
            let body = res.json();
            if(body.code == 200) {
                this.motivazioni = body.data as Motivazione[];
                $( ".nascondinota" ).hide();
                if ((document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim()=="")
                    $( ".nascondinotaben" ).hide();
                else {
                    $( ".nascondinota" ).show();
                    $( ".nascondinotaben" ).show();
                }
                if ((document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim()=="")
                    $( ".nascondinotaser" ).hide();
                else {
                    $( ".nascondinota" ).show();
                    $( ".nascondinotaser" ).show();
                }
                if ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim()=="")
                    $( ".nascondinotaint" ).hide();
                else
                {
                    $( ".nascondinota" ).show();
                    $( ".nascondinotaint" ).show();
                }
              (  document.getElementById( "notabeneficiarior" ).innerHTML ) = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
            ( document.getElementById( "notaservizior" ).innerHTML ) = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();
            ( document.getElementById( "notainternar" ).innerHTML ) = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();

                this.notaBen = (  document.getElementById( "notabeneficiarior" ).innerHTML );
                this.notaSer =  ( document.getElementById( "notaservizior" ).innerHTML ) ;
                this.notaInt =  ( document.getElementById( "notainternar" ).innerHTML );
                this.loading = false;
                $('.annulla-revoca-respingi').modal('toggle');
            }
        },
        errorCode => {
            var str = errorCode._body;
            var objmsg = JSON.parse( str );
            console.log(objmsg);
            this.loading = false;
        }
    );
  }

  chiudiModal() {
      $('.annulla-revoca-respingi').modal('toggle');
  }

  procediAnnullaRevocaRespingi(sceltaModal:string) {
    (document.getElementById("combo-motivazione") as HTMLInputElement).classList.remove("inputError");

      let filtri: MotivazioneAnnRevRes = new MotivazioneAnnRevRes();
      let tmp = (document.getElementById("combo-motivazione")) as HTMLSelectElement;
      let index = tmp.selectedIndex;
      if(index == 0) {
          (document.getElementById("combo-motivazione") as HTMLInputElement).classList.add("inputError");
            return;
      }

      this.loading = true;

      filtri.motivazione = this.motivazioni[index-1];
      filtri.nota_beneficiario = this.notaBen;
      filtri.nota_servizio = this.notaSer;
      filtri.nota_interna = this.notaInt;

      switch(sceltaModal.toUpperCase()) {
          case "ANNULLA": {
            this.esenzioneStoreService.setAnnullamentoEsenzione(filtri,
                this.dettaglioEsenzione.id, this.dettaglioEsenzione.beneficiario.codice_fiscale)
                .subscribe(res => {
                    let data = res.json() as PraticaEsenzione;
                    this.statusCode = res.status;
                    if(this.statusCode == 200) {
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = "Pratica " + data.numero_pratica + " annullata con successo";
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.getDettaglioEsenzione();
                    }
                },
                    errorCode => {
                        this.statusCode = errorCode.status;
                        var str = errorCode._body;
                        var objmsg = JSON.parse( str );
                        switch ( this.statusCode ) {
                            case 400:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).show();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).hide();

                                this.loading = false;
                                break;
                            default:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).hide();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).show();

                                this.loading = false;
                                break;
                    }
                }
                  );

              break;
          }
          case "REVOCA": {
              this.esenzioneStoreService.setRevocaEsenzione( filtri,
                  this.dettaglioEsenzione.id, this.dettaglioEsenzione.beneficiario.codice_fiscale )
                  .subscribe( res => {
                    let data = res.json() as PraticaEsenzione;
                    this.statusCode = res.status;
                    if(this.statusCode == 200) {
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = "Pratica " + data.numero_pratica + " revocata con successo";
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.getDettaglioEsenzione();
                    }
                },
                    errorCode => {
                        this.statusCode = errorCode.status;
                        var str = errorCode._body;
                        var objmsg = JSON.parse( str );
                        switch ( this.statusCode ) {
                            case 400:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).show();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).hide();
                                this.loading = false;
                                break;
                            default:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).hide();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).show();
                                this.loading = false;
                                break;
                    }
                }
                  );

              break;
          }
          case "RESPINGI": {
              this.esenzioneStoreService.setRespingiEsenzione( filtri,
                  this.dettaglioEsenzione.id, this.dettaglioEsenzione.beneficiario.codice_fiscale )
                  .subscribe(res => {
                    this.statusCode = res.status;
                    if(this.statusCode == 200) {
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = "Pratica " + this.dettaglioEsenzione.numero_pratica + " respinta con successo";
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.getDettaglioEsenzione();
                    }
                },
                    errorCode => {
                        this.statusCode = errorCode.status;
                        var str = errorCode._body;
                        var objmsg = JSON.parse( str );
                        switch ( this.statusCode ) {
                            case 400:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).show();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).hide();
                                this.getDettaglioEsenzione();
                                break;
                            default:
                                this.chiudiModal();
                                ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                                $( ".esitoricercako" ).hide();
                                $( ".esitoricercaok" ).hide();
                                $( ".esitoricercagenerico" ).show();
                                this.getDettaglioEsenzione();
                                break;
                    }
                }
                  );

              break;
          }
          case "RETTIFICA MEDICO": {
              let request = new RichiestaRettificaDati();
              let ben = new beneficiario();

              ben.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
              request.beneficiario = ben;
              request.codiceMotivazioneTipo = filtri.motivazione.codice;
              request.notabeneficiario = filtri.nota_beneficiario;
              request.notainterna = filtri.nota_interna;
              request.nota = filtri.nota_servizio;
              request.skPraticaEsenzione = this.dettaglioEsenzione.id;
              request.richiestaRettificaDestinatario = "MEDICO";

              this.esenzioneStoreService.setProcediRichiestaRettifica(request)
                  .subscribe( res => {
                    this.statusCode = res.status;
                    let body = res.json();
                    if(this.statusCode == 200) {
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message as string;
                        $( ".esitoricercaok" ).show();
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.getDettaglioEsenzione();
                    }
                  },
                  errorCode => {
                    this.statusCode = errorCode.status;
                    var str = errorCode._body;
                    var objmsg = JSON.parse( str );
                    switch ( this.statusCode ) {
                        case 400:
                            this.chiudiModal();
                            ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                            $( ".esitoricercako" ).show();
                            $( ".esitoricercaok" ).hide();
                            $( ".esitoricercagenerico" ).hide();
                            this.getDettaglioEsenzione();
                            break;
                        default:
                            this.chiudiModal();
                            ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                            $( ".esitoricercako" ).hide();
                            $( ".esitoricercaok" ).hide();
                            $( ".esitoricercagenerico" ).show();
                            this.getDettaglioEsenzione();
                            break;
                }
            }
              );

              break;
            }
          case "RETTIFICA BENEFICIARIO": {
              let request = new RichiestaRettificaDati();
              let ben = new beneficiario();

              ben.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
              request.beneficiario = ben;
              request.codiceMotivazioneTipo = filtri.motivazione.codice;
              request.notabeneficiario = filtri.nota_beneficiario;
              request.notainterna = filtri.nota_interna;
              request.nota = filtri.nota_servizio;
              request.skPraticaEsenzione = this.dettaglioEsenzione.id;
              request.richiestaRettificaDestinatario = "CITTADINO";

              this.esenzioneStoreService.setProcediRichiestaRettifica(request)
              .subscribe( res => {
                this.statusCode = res.status;
                let body = res.json();
                if(this.statusCode == 200) {
                    this.chiudiModal();
                    ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message as string;
                    $( ".esitoricercaok" ).show();
                    $( ".esitoricercako" ).hide();
                    $( ".esitoricercagenerico" ).hide();
                    this.getDettaglioEsenzione();
                }
              },
              errorCode => {
                this.statusCode = errorCode.status;
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                switch ( this.statusCode ) {
                    case 400:
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercako" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).show();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).hide();
                        this.getDettaglioEsenzione();
                        break;
                    default:
                        this.chiudiModal();
                        ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                        $( ".esitoricercako" ).hide();
                        $( ".esitoricercaok" ).hide();
                        $( ".esitoricercagenerico" ).show();
                        this.getDettaglioEsenzione();
                        break;
            }
        }
          );
              break;
          }
          default: {
              console.error( "errore procedi annulla-revoca-respingi-rettifica" );
              break;
          }
      }
  }


  scaricaAttestato() {
    this.loading = true;
    let id = '0';
    if(this.dettaglioEsenzione.id != null) {
        id = this.dettaglioEsenzione.id;
    }
    this.esenzioneStoreService.scaricaAttestato(this.dettaglioEsenzione.beneficiario.codice_fiscale, id, this.dettaglioEsenzione.codice.codice, this.dettaglioEsenzione.data_inizio_validita).subscribe(
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



  salvaInBozza() {
    this.loading = true;

    if (this.dettaglioEsenzione.data_inizio_validita!=null)
      this.filtroSalvaInBozza.dataInizioValidita = this.dettaglioEsenzione.data_inizio_validita;
    if (this.dettaglioEsenzione.data_fine_validita!=null)
      this.filtroSalvaInBozza.dataFineValidita = this.dettaglioEsenzione.data_fine_validita;
    if (this.operazione!=null)
      this.filtroSalvaInBozza.azione = this.operazione;
    if (this.dettaglioEsenzione.id!=null)
      this.filtroSalvaInBozza.skPraticaEsenzione = this.dettaglioEsenzione.id;

    if (this.dettaglioEsenzione.codice.codice!=null && this.dettaglioEsenzione.codice.codice!="")
      this.filtroSalvaInBozza.codEsenzione = this.dettaglioEsenzione.codice.codice;

    let assistito = new beneficiario();
    if (this.dettaglioEsenzione.beneficiario.codice_fiscale!="" && this.dettaglioEsenzione.beneficiario.codice_fiscale!=null)
      assistito.codice_fiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
    assistito.cognome = "";
    assistito.nome = "";
    if (this.dettaglioEsenzione.beneficiario.id_aura!="" && this.dettaglioEsenzione.beneficiario.id_aura!=null)
      assistito.id_aura = this.dettaglioEsenzione.beneficiario.id_aura ;
    this.filtroSalvaInBozza.beneficiario = assistito;

    if ( (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value!="")
      this.filtroSalvaInBozza.nota = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();
    else
      this.filtroSalvaInBozza.nota ="";
    if ( (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value!="")
      this.filtroSalvaInBozza.notabeneficiario = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
    else
      this.filtroSalvaInBozza.notabeneficiario ="";
    if ( (document.getElementById( "nota-interna" ) as HTMLInputElement ).value!="")
      this.filtroSalvaInBozza.notainterna = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();
    else
      this.filtroSalvaInBozza.notainterna = "";
    if (this.dettaglioEsenzione.id!=null && this.dettaglioEsenzione.id!="")
      this.filtroSalvaInBozza.skPraticaEsenzione = this.dettaglioEsenzione.id;

        this.esenzioneStoreService.salvaInBozza(this.filtroSalvaInBozza).subscribe(
          (res) => {
            let body = res.json();
            if(body.code == 200) {
              this.motivazioni = body.data as Motivazione[];
              this.notaBen = (document.getElementById( "nota-beneficiario" ) as HTMLInputElement ).value.trim();
              this.notaSer = (document.getElementById( "nota-servizio" ) as HTMLInputElement ).value.trim();
              this.notaInt = (document.getElementById( "nota-interna" ) as HTMLInputElement ).value.trim();
              this.loading = false;
              $('#modalSalvaInBozza').modal('toggle');
            }
          },
          errorCode => {
            var str = errorCode._body;
            var objmsg = JSON.parse( str );
            console.log(objmsg);
            this.loading = false;
          }
        );
  }

  procediSalvaInBozza() {
    this.loading = true;

      this.esenzioneStoreService.procediSalvaInBozza(this.filtroSalvaInBozza)
        .subscribe( successCode => {
            this.statusCode = successCode.status;
            let body = successCode.json();
            if ( this.statusCode == 200 ) {
              ( document.getElementById( "messaggioesitoricercaok" ).innerHTML ) = body.message;
              $( ".esitoricercaok" ).show();
              $('#modalSalvaInBozza').modal('hide');
              $( ".esitoricercako" ).hide();
              $( ".esitoricercagenerico" ).hide();
              this.getDettaglioEsenzione();

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
                $('#modalSalvaInBozza').modal('hide');
                $( ".esitoricercagenerico" ).hide();
                this.loading = false;
                break;
              default:
                ( document.getElementById( "messaggioesitoricercagenerico" ).innerHTML ) = objmsg.message;
                $( ".esitoricercako" ).hide();
                $( ".esitoricercaok" ).hide();
                $('#modalSalvaInBozza').modal('hide');
                $( ".esitoricercagenerico" ).show();
                this.loading = false;
                break;
            }
          } )

  }

  creaCertificatoDaEsenzione() {

      this.workflowService.setFromEsenzioni(true);
      this.workflowService.setCurrentStep(3);

      var assistito: Assistito = new Assistito();
      assistito.nome = this.dettaglioEsenzione.beneficiario.nome;
      assistito.cognome = this.dettaglioEsenzione.beneficiario.cognome;
      assistito.codFiscale = this.dettaglioEsenzione.beneficiario.codice_fiscale;
      assistito.idAura = this.dettaglioEsenzione.beneficiario.id_aura;
      assistito.dataNascita = this.dettaglioEsenzione.beneficiario.data_nascita;
      assistito.comuneNascita= this.dettaglioEsenzione.beneficiario.comune_nascita;
      assistito.codSesso= this.dettaglioEsenzione.beneficiario.sesso;

      this.assistitoStoreService.setAssistito(assistito);

      var ricRettifica = new FiltroValidaEsenzionePatologia();
      ricRettifica.beneficiario = this.dettaglioEsenzione.beneficiario;
      ricRettifica.codEsenzione = this.dettaglioEsenzione.codice.codice;

      this.workflowService.setFiltroRettifica(ricRettifica);

      var gruppoesen = new GruppoEsenzione();
      gruppoesen.gruppoEsenzione = this.dettaglioEsenzione.tipologia.codice;

      this.formDataService.setGruppoEsenzione(gruppoesen);

      var tipologiaCert: TipologiaCert = new TipologiaCert();
      tipologiaCert.patologia = this.patologia;

      var diagnosi: Diagnosi = new Diagnosi();
      diagnosi.codice_diagnosi = this.dettaglioEsenzione.malattia.codice;
      diagnosi.descrizione_diagnosi = this.dettaglioEsenzione.malattia.descrizione;
      tipologiaCert.diagnosi = diagnosi;

      var es: Esenzione = new Esenzione();
      es.codice_esenzione = this.dettaglioEsenzione.codice.codice;
      es.descrizione_esenzione = this.dettaglioEsenzione.codice.descrizione;
      tipologiaCert.esenzione= es;

      this.formDataService.setTipologiaCert(tipologiaCert);

      this.router.navigate( ["../nuovo-certificato"], {relativeTo: this.route,skipLocationChange: true} );
  }

  chiudiModalSalvaInBozza() {
    $('#modalSalvaInBozza').modal('hide');
  }

  getDettaglioCertificato() {
    this.loading = true;
    this.esenzioneStoreService.getDettaglioCertificato(this.dettaglioEsenzione.beneficiario.codice_fiscale, this.dettaglioEsenzione.certificato_id)
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

returnHome() {
    this.router.navigate( ["../home"],
    {
    relativeTo: this.route, skipLocationChange: true} );
}

}
