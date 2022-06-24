import { Component, OnInit, Input } from '@angular/core';
import { FormDataService } from 'app/certificato-wizard/data/formData.service';
import { WorkflowService } from 'app/certificato-wizard/workflow/workflow.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CertErrorHandler } from 'app/certificato-wizard/certErrorHandler';
import { Observable } from 'rxjs/Rx';
import {Http, RequestOptions, Headers, Response} from '@angular/http';
import { ConfigService } from 'app/services/config.service';
import { FiltroValidaEsenzionePatologia } from 'app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia';
import { beneficiario } from 'app/assistito/beneficiario';
import * as $ from 'jquery';
import { Motivazione } from 'app/esenzione/Motivazione';
import {EsenzioneStoreService} from '../../esenzione/esenzione.service';
import {MotivazioneAnnRevRes} from '../../esenzione/MotivazioneAnnRevRes';
import {RichiestaRettificaDati} from '../../esenzione/richiestaRettificaDati';
import {AssistitoStoreService} from '../../assistito/assistito.service';
import {Assistito} from '../../assistito/assistito';
import {FiltriDettaglioEsenzione} from '../../esenzione/FiltriDettaglioEsenzione';
import { statodocumentoStoreService } from 'app/statodocumento/statodocumento.service';
import { firmadocumento } from 'app/statodocumento/firmadocumento';
import { UserInfo } from 'app/assistito/userinfo';
import { DocumentiEsenzione } from 'app/esenzione-wizard/data/esenFormData.model';
import { EsenWorkflowService } from 'app/esenzione-wizard/workflow/workflow.service';
import { CertificatoPatologia } from 'app/esenzione/CertificatoPatologia';
import {datiFirmaDigitale} from '../../statodocumento/datiFirmaDigitale';
import { CaModel, OtpTypeModel } from 'app/statodocumento/caModel';
import {datiRichiestaOtp} from '../../statodocumento/datiRichiestaOtp';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
declare var $;

@Component({
  selector: 'app-cert-creato',
  templateUrl: './certificato-creato.component.html',
  styleUrls: ['./certificato-creato.css']
})
export class CertificatoCreatoComponent implements OnInit {

    numeroCertificato = '';
    firmato = false;
    loading = false;
    notaBeneficiario = '';
    notaServizio = '';
    notaInterna = '';
    statusCode: number;
    motivazioni: Motivazione[] = [];
    utenteMedicoCas = false;
    utenteOperatoreAslDistretto = false;
    utenteMedicoSpecialistaAslDistretto = false;
    utenteAmministratoreCsi = false;
    utente: UserInfo;
    esenzioneCreata = false;
    cod_diagnosi = '';
    cod_esenzione = '';
    id_pratica_da_validare = '';
    num_pratica_da_validare = '';
    ricValida = new FiltroValidaEsenzionePatologia();
    listaCa: CaModel[];
    selectedCa: CaModel = null;
    selectedOtpType: OtpTypeModel = null;
    toggleOtp: boolean = false;
    alias: string;
    pin: string;
    otp: string;
    fieldTextType: boolean;

  constructor(private formDataService: FormDataService,
                private workFlowService: WorkflowService,
                private router: Router,
                private route: ActivatedRoute,
                private errorHandler: CertErrorHandler,
                private statodocumentoService: statodocumentoStoreService,
                private http: Http, private config: ConfigService,
                private esenzioneStoreService: EsenzioneStoreService,
                private assistitoStoreService: AssistitoStoreService,
                private esenworkflowService: EsenWorkflowService

    ) { }


    ngOnInit() {
      this.loading = true;
            this.errorHandler.setHidden(true);
            this.numeroCertificato = this.workFlowService.getCertNumber();
            this.cod_diagnosi = this.workFlowService.getCodDiagnosi();
            this.cod_esenzione = this.workFlowService.getCodEsenzione();
            $( '.esitoricercaok' ).hide();
            $( '.esitoricercako' ).hide();
            $( '.esitoricercagenerico' ).hide();
            this.utenteMedicoCas = false;
            this.utenteOperatoreAslDistretto = false;
            this.utenteMedicoSpecialistaAslDistretto = false;
            this.utenteAmministratoreCsi = false;
            this.utente = this.assistitoStoreService.getUser();
            this.utente.ruoli.forEach(ruolo => {
                if (ruolo.codiceRuolo === 'MEDICO_SPECIALISTA_CAS') { //MEDICO CAS
                    this.utenteMedicoCas = true;
                }
                if (ruolo.codiceRuolo === 'OPERATORE_ASL_DISTRETTO') { //OPERATORE ASL DISTRETTO
                    this.utenteOperatoreAslDistretto = true;
                }
                if (ruolo.codiceRuolo === 'MEDICO_SPECIALISTA_ASL_DISTRETTO') { //MEDICO SPECIALISTA ASL DISTRETTO
                    this.utenteMedicoSpecialistaAslDistretto = true;
                }
                if (ruolo.codiceRuolo === 'AMMINISTRATORE_CSI') { //AMMINISTRATORE CSI
                    this.utenteAmministratoreCsi = true;
                }
            });

            this.statodocumentoService.getCaOtpTypes().subscribe(res => {
              this.listaCa = res;
              this.loading = false;
            },
            err => {
              this.loading = false;
            })
    }

    goToHome() {
        this.workFlowService.resetWorkFlow();
        this.esenworkflowService.resetWorkFlow();
        this.formDataService.resetFormData();
        this.router.navigate( ['../home'], {relativeTo: this.route, skipLocationChange: true} );
    }

    chiudiModal() {
        $('#rettifica-esenzione').modal('hide');
    }

    ChiudiFirma() {
            $('#firmacertificato').modal('hide');
    }

procediRettificaEsenzione() {
      this.loading = true;
      (document.getElementById('combo-motivazione') as HTMLInputElement).classList.remove('inputError');
      const tmp = (document.getElementById('combo-motivazione')) as HTMLSelectElement;
      const index = tmp.selectedIndex;

      const filtri: RichiestaRettificaDati = new RichiestaRettificaDati();
      filtri.nota = this.notaServizio;
      filtri.notabeneficiario = this.notaBeneficiario;
      filtri.notainterna = this.notaInterna;
      if (index == 0) {
        filtri.codiceMotivazioneTipo = null;
      } else {
        filtri.codiceMotivazioneTipo = this.motivazioni[index - 1].codice;
      }
      const assistito = this.assistitoStoreService.getAssistito() as Assistito;
      const ben = new beneficiario();
      ben.codice_fiscale = assistito.codFiscale;
      filtri.beneficiario = ben;

      filtri.skPraticaEsenzione = this.esenzioneStoreService.dettaglioEsenzione.id;
      filtri.skDocumento = this.numeroCertificato;
      this.esenzioneStoreService.rettificaEsenzione(filtri)
        .subscribe( successCode => {
            const statusCode = successCode.status;
            const body = successCode.json();
            if ( statusCode == 200 ) {
              ( document.getElementById( 'messaggioesitoricercaok' ).innerHTML ) = body.message;
              $( '.esitoricercaok' ).show();
              $( '#rettifica-esenzione').modal('hide');
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.getDettaglioEsenzione();
            }
          },
          errorCode => {
            const statusCode = errorCode.status;
            const str = errorCode._body;
            const objmsg = JSON.parse( str );
            switch ( statusCode ) {
              case 400:
                ( document.getElementById( 'messaggioesitoricercako' ).innerHTML ) = objmsg.message;
                $( '.esitoricercako' ).show();
                $( '.esitoricercaok' ).hide();
                $( '#rettifica-esenzione').modal('hide');
                $( '.esitoricercagenerico' ).hide();
                this.loading = false;
                break;
              default:
                ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = objmsg.message;
                $( '.esitoricercako' ).hide();
                $( '.esitoricercaok' ).hide();
                $( '#rettifica-esenzione').modal('hide');
                $( '.esitoricercagenerico' ).show();
                this.loading = false;
                break;
            }
          }
        )
    }

      rimandaAdOperatore(stato: string) {
      this.loading = true;
      this.esenzioneStoreService.getMotivazioni(stato.substring(0, 3)).subscribe(
        (res) => {
          const body = res.json();
          if (body.code == 200) {
            this.notaBeneficiario = (document.getElementById( 'nota-beneficiario' ) as HTMLInputElement ).value.trim();
            this.notaServizio =  (document.getElementById( 'nota-servizio' ) as HTMLInputElement ).value.trim();
            this.notaInterna =  (document.getElementById( 'nota-interna' ) as HTMLInputElement ).value.trim();
            this.motivazioni = body.data as Motivazione[];
            $('#rettifica-esenzione').modal('show');
            this.loading = false;
          }
        },
        errorCode => {
          const str = errorCode._body;
          const objmsg = JSON.parse( str );
          console.log(objmsg);
          this.loading = false;
        }
      );
    }


      inviaRichiestaEsenzione() {
          const assistito = this.assistitoStoreService.getAssistito() as Assistito;
          const assist: Assistito = {
                  codFiscale : assistito.codFiscale,
                  cognome : assistito.cognome,
                  nome : assistito.nome,
                  codSesso : '',
                  dataNascita : '',
                  comuneNascita : '',
                  provinciaNascita : '',
                  idAura : assistito.idAura,
                  stato: '',
                  datascadenzaSSN: '',
                  codStatoNascita: '',
                  codAsl: ''
              }

          const malattia = {
              'gruppoEsenzione': '',
              'codDiagnosi': this.cod_diagnosi,
              'codEsenzione': this.cod_esenzione,
          };

          const lista_documenti: DocumentiEsenzione[] = new Array();

          const documento: DocumentiEsenzione = {
                  documentoId: this.numeroCertificato,
                  filename: '',
                  fileBase64: '',
                  tipologia: '',
                  dataRilascio: '',
                  dataFineValidita: '',
                  descrizione: ''
          };

          lista_documenti.push(documento);
          const postData = {
                  'assistito': assist,
                  'malattia': malattia,
                  'documenti' : lista_documenti,
                  'noteServizio': '',
                  'noteInterne': ''
                  };

          this.loading = true;

          this.esenzioneStoreService.creaEsenzione(JSON.stringify(postData))
              .subscribe(
                  result => {
                      console.log('success');
                      const statusCode = result.status;
                      const str = result._body;
                      const objmsg = JSON.parse( str );
                      this.esenworkflowService.setEsenNumber(objmsg.num_esenzione);

                      this.id_pratica_da_validare = objmsg.id_esenzione;
                      this.num_pratica_da_validare = objmsg.num_esenzione;

                      $( '.esitoricercaok' ).hide();
                      $( '.esitoricercako' ).hide();
                      $( '.esitoricercagenerico' ).hide();
                      ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = '';
                      ( document.getElementById( 'messaggioesitoricercako' ).innerHTML ) = '';
                      ( document.getElementById( 'messaggioesitoricercaok' ).innerHTML ) = '';
                      this.checkApprovaEsenzione();
                      },
                   error => {
                       console.log('error occured!');

                       const statusCode = error.status;

                       if (statusCode === 0) {
                           ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = 'ATTENZIONE! Servizio non disponibile. Riprovare più tardi.';
                           $( '.esitoricercaok' ).hide();
                           $( '.esitoricercako' ).hide();
                           $( '.esitoricercagenerico' ).show();
                       } else {
                           const str = error._body;
                           const objmsg = JSON.parse( str );
                           ( document.getElementById( 'messaggioesitoricercako' ).innerHTML ) = objmsg.message;
                           $( '.esitoricercaok' ).hide();
                           $( '.esitoricercako' ).show();
                           $( '.esitoricercagenerico' ).hide();
                       }

                       this.loading = false;
                    }
           );
      }

      scaricaCertificato() {
          this.loading = true;
          const assistito = this.assistitoStoreService.getAssistito() as Assistito;
          this.esenzioneStoreService.scaricaCertificato(assistito.codFiscale, this.numeroCertificato).subscribe(
              ( res ) => {
                  const name: string = res.get('name') as string;
                  const file: Blob = res.get('file') as Blob;
                  const fileURL = URL.createObjectURL( file );
                  const link = document.createElement( 'a' );
                  link.href = window.URL.createObjectURL( file );
                  link.download = name;
                  document.body.appendChild( link );
                  link.click();
                  document.body.removeChild( link );
                  this.loading = false;
          },
          errorCode => {
              const str = errorCode._body;
              const objmsg = JSON.parse( str );
              console.log(objmsg);
              this.loading = false;
          }
      );
      }

    firmaCertificato() {

        ( document.getElementById( 'testofirma' ).innerHTML ) = 'Per procedere alla firma del certificato n. ' +  this.numeroCertificato + ', compilare i campi sottostanti secondo il metodo di firma previsto dalla propria ASL di appartenenza.';
        ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = '';
        ( document.getElementById( 'messaggioesitoricercafirmaok' ).innerHTML ) = '';


      $( '.esitoricercafirmaok' ).hide();
        $( '.esitoricercafirmako' ).hide();
        $('#firmacertificato').modal('show');
    }

    ProcediFirmaCertificato() {
        this.loading = true;
       const firmadoc = new firmadocumento();
       firmadoc.alias =  ( document.getElementById( 'inpalias' ) as HTMLInputElement ).value;
       firmadoc.pin =  ( document.getElementById( 'inppin' ) as HTMLInputElement ).value;
       firmadoc.pkDocumento = this.numeroCertificato;
        this.statodocumentoService.FirmaDocumento(firmadoc)
        .subscribe( successCode => {
            this.statusCode = successCode.status;
            const body = successCode.json();
            if ( this.statusCode == 200 ) {

                    ( document.getElementById( 'messaggioesitoricercaok' ).innerHTML ) = body.message;
                    $( '.esitoricercaok' ).show();
                    $('#firmacertificato').modal('hide');
                    $( '.esitoricercako' ).hide();
                    $( '.esitoricercagenerico' ).hide();
                    this.firmato = true;
                    this.loading = false;

        }
        },
        errorCode => {
            this.statusCode = errorCode.status;
            const str = errorCode._body;
            const objmsg = JSON.parse( str );
            switch ( this.statusCode ) {
                case 400:
                    ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
                    $( '.esitoricercafirmako' ).show();
                    $( '.esitoricercaok' ).hide();
                    $( '.esitoricercako' ).hide();
                    $( '.esitoricercagenerico' ).hide();
                    this.loading = false;
                    break;
                case 404:
                    ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
                    $( '.esitoricercafirmako' ).show();
                    $( '.esitoricercaok' ).hide();
                    $( '.esitoricercako' ).hide();
                    $( '.esitoricercagenerico' ).hide();
                    this.loading = false;
                    break;
                default:
                    ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
                    $( '.esitoricercafirmako' ).show();
                    $( '.esitoricercaok' ).hide();
                    $( '.esitoricercako' ).hide();
                    $( '.esitoricercagenerico' ).hide();
                    this.loading = false;
                    break;
            }
        } )
    }

  getDettaglioEsenzione() {

    const filtro = new FiltriDettaglioEsenzione();
    if (this.esenzioneStoreService.dettaglioEsenzione.beneficiario.codice_fiscale != null) {
      filtro.cf_beneficiario = this.esenzioneStoreService.dettaglioEsenzione.beneficiario.codice_fiscale;
    }
    if (this.esenzioneStoreService.dettaglioEsenzione.codice.codice != null) {
      filtro.cod_esenzione = this.esenzioneStoreService.dettaglioEsenzione.codice.codice;
    }
    if (this.esenzioneStoreService.dettaglioEsenzione.data_inizio_validita != null) {
      filtro.data_emissione = this.esenzioneStoreService.dettaglioEsenzione.data_inizio_validita;
    }
    if (this.esenzioneStoreService.dettaglioEsenzione.id != null) {
      filtro.sk_esenzione = this.esenzioneStoreService.dettaglioEsenzione.id;
    }

    this.esenzioneStoreService.getDettaglioEsenzione(filtro)
      .subscribe(data => {
          if (data._body != null && data._body) {
            this.router.navigate( ['../dettaglio-esenzione'], { relativeTo: this.route, skipLocationChange: true } );
          }
          this.loading = false;
        },
        errorCode => {
          const str = errorCode._body;
          const objmsg = JSON.parse( str );
          console.log(objmsg);
          this.loading = false;
        }
      );
}



checkApprovaEsenzione() {
  const assis = this.assistitoStoreService.getAssistito();
  const assistito = new beneficiario();
  assistito.codice_fiscale = assis.codFiscale;
  assistito.cognome = '';
  assistito.nome = '';
  assistito.id_aura = assis.idAura;
  this.ricValida.beneficiario = assistito;

  this.ricValida.codEsenzione = this.cod_esenzione;
  this.ricValida.dataFineValidita = '';
  this.ricValida.dataInizioValidita = '';
  this.ricValida.nota = '';
  this.ricValida.notabeneficiario = '';
  this.ricValida.notainterna = '';
  this.ricValida.skPraticaEsenzione = this.id_pratica_da_validare;

  this.esenzioneStoreService.getApprovaEsenzione(this.ricValida)
  .subscribe(
      successCode => {
          this.statusCode = successCode.status;
          const body = successCode.json();

          if (body.message.toUpperCase() == 'OK-PROCEDI APPROVA') {

              $('#validacertificato').modal('show');
              this.loading = false;
          } else {

              (document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = body.message;
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).show();

              this.loading = false;
          }
      },
      error => {
          console.log('error occured!');

          const statusCode = error.status;

          if (statusCode === 0) {
              ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = 'ATTENZIONE! Servizio non disponibile. Riprovare più tardi.';
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).show();
          } else {
              const str = error._body;
              const objmsg = JSON.parse( str );
              ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = error.message;
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).show();
          }
          this.loading = false;
       }
  )
}



procediApprovaEsenzione() {
  this.chiudiValida();
  this.loading = true;

  this.esenzioneStoreService.getProcediApprovaEsenzione(this.ricValida)
  .subscribe(
      successCode => {
          this.statusCode = successCode.status;
          const body = successCode.json();

          this.checkValidaEsenzione()

      },
      error => {
          console.log('error occured!');

          const statusCode = error.status;

          if (statusCode === 0) {
            ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = 'ATTENZIONE! Servizio non disponibile. Riprovare più tardi.';
            $( '.esitoricercaok' ).hide();
            $( '.esitoricercako' ).hide();
            $( '.esitoricercagenerico' ).show();
        } else {
            const str = error._body;
            const objmsg = JSON.parse( str );
            ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = error.message;
            $( '.esitoricercaok' ).hide();
            $( '.esitoricercako' ).hide();
            $( '.esitoricercagenerico' ).show();
        }
          this.loading = false;
       }
  )
}


checkValidaEsenzione() {
  this.ricValida.azione = 'VALIDA';
  this.esenzioneStoreService.getValidaEsenzione(this.ricValida)
      .subscribe(
          successCode => {
              this.statusCode = successCode.status;
              const body = successCode.json();

              if (body.message.toUpperCase() == 'OK-PROCEDI VALIDA') {

                  this.procediValidaEsenzione();
              } else {

                  (document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = body.message;
                  $( '.esitoricercaok' ).hide();
                  $( '.esitoricercako' ).hide();
                  $( '.esitoricercagenerico' ).show();
                  this.loading = false;
              }
          },
          error => {
              console.log('error occured!');

              const statusCode = error.status;

              if (statusCode === 0) {
                ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = 'ATTENZIONE! Servizio non disponibile. Riprovare più tardi.';
                $( '.esitoricercaok' ).hide();
                $( '.esitoricercako' ).hide();
                $( '.esitoricercagenerico' ).show();
            } else {
                const str = error._body;
                const objmsg = JSON.parse( str );
                ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = error.message;
                $( '.esitoricercaok' ).hide();
                $( '.esitoricercako' ).hide();
                $( '.esitoricercagenerico' ).show();
            }
              this.loading = false;
           }
      )
}

procediValidaEsenzione() {
      this.ricValida.azione = 'VALIDA';
      this.esenzioneStoreService.getProcediValidaEsenzione(this.ricValida).subscribe(
          successCode => {
              console.log('success');
              this.esenzioneCreata = true;
              this.loading = false;
              this.esenworkflowService.setEsenNumber(this.num_pratica_da_validare);
          },
          error => {
              console.log('error occured!');

              const statusCode = error.status;

              if (statusCode === 0) {
                ( document.getElementById( 'messaggioesitoricercagenerico' ).innerHTML ) = 'ATTENZIONE! Servizio non disponibile. Riprovare più tardi.';
                $( '.esitoricercaok' ).hide();
                $( '.esitoricercako' ).hide();
                $( '.esitoricercagenerico' ).show();
            } else {
                const str = error._body;
                const objmsg = JSON.parse( str );
                ( document.getElementById( 'messaggioesitoricercako' ).innerHTML ) = objmsg.message;
                $( '.esitoricercaok' ).hide();
                $( '.esitoricercako' ).show();
                $( '.esitoricercagenerico' ).hide();
            }
              this.loading = false;
           }
      )
}

chiudiValida() {
  $('#validacertificato').modal('hide');
}



  richiestaOtpFirma() {
    this.loading = true;
    $( '.esitoricercafirmaok' ).hide();
    $( '.esitoricercafirmako' ).hide();
    const richiestaOtp = new datiRichiestaOtp();
    richiestaOtp.alias =  ( document.getElementById( 'inpalias' ) as HTMLInputElement ).value;
    richiestaOtp.pin =  ( document.getElementById( 'inppin' ) as HTMLInputElement ).value;
    richiestaOtp.ca = this.selectedCa.descrizione;
    richiestaOtp.typeOtp = this.selectedOtpType.descrizione;
    this.statodocumentoService.richiestaOtpFirma(richiestaOtp)
      .subscribe( successCode => {
          this.statusCode = successCode.status;
          const body = successCode.json();
          if ( this.statusCode == 200 ) {

            ( document.getElementById( 'messaggioesitoricercafirmaok' ).innerHTML ) = body.message;
            $( '.esitoricercafirmaok' ).show();
            $( '.esitoricercaok' ).hide();
            $( '.esitoricercako' ).hide();
            $( '.esitoricercagenerico' ).hide();
            $( '.esitoricercafirmako' ).hide();
            this.loading = false;
          }
        },
        errorCode => {
          this.statusCode = errorCode.status;
          const str = errorCode._body;
          const objmsg = JSON.parse( str );
          switch ( this.statusCode ) {
            case 400:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.loading = false;
              break;
            case 404:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.loading = false;
              break;
            default:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.loading = false;
              break;
          }
        } )
  }

  firmaDocumentoDigitale() {
    this.loading = true;
    $( '.esitoricercafirmaok' ).hide();
    $( '.esitoricercafirmako' ).hide();

    const datiFirma = new datiFirmaDigitale();
    datiFirma.alias =  ( document.getElementById( 'inpalias' ) as HTMLInputElement ).value;
    datiFirma.pin =  ( document.getElementById( 'inppin' ) as HTMLInputElement ).value;
    datiFirma.otp =  ( document.getElementById( 'inpotp' ) as HTMLInputElement ).value;
    datiFirma.pkDocumento = this.numeroCertificato;
    datiFirma.ca = this.selectedCa.descrizione;
    this.statodocumentoService.firmaDocumentoDigitale(datiFirma)
      .subscribe( successCode => {
          this.statusCode = successCode.status;
          const body = successCode.json();
          if ( this.statusCode == 200 ) {

            ( document.getElementById( 'messaggioesitoricercaok' ).innerHTML ) = body.message;
            $( '.esitoricercaok' ).show();
            $('#firmacertificato').modal('hide');
            $( '.esitoricercako' ).hide();
            $( '.esitoricercagenerico' ).hide();
            this.firmato = true;
            this.loading = false;

          }
        },
        errorCode => {
          this.statusCode = errorCode.status;
          const str = errorCode._body;
          const objmsg = JSON.parse( str );
          switch ( this.statusCode ) {
            case 400:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              console.log(objmsg.msgErrore);
              this.loading = false;
              break;
            case 404:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.loading = false;
              break;
            default:
              ( document.getElementById( 'messaggioesitoricercafirmako' ).innerHTML ) = objmsg.message;
              $( '.esitoricercafirmako' ).show();
              $( '.esitoricercaok' ).hide();
              $( '.esitoricercako' ).hide();
              $( '.esitoricercagenerico' ).hide();
              this.loading = false;
              break;
          }
        } )
  }


  validFormFirma: boolean = false;
  validFormOtp: boolean = false;
  isValidForm() {
    this.validFormOtp =  this.selectedCa != null && this.selectedCa != undefined
        && this.selectedOtpType != null && this.selectedOtpType != undefined
        && this.validAlias()
        && this.validPin();
    this.validFormFirma = this.selectedCa != null && this.selectedCa != undefined
        && this.validAlias()
        && this.validPin();
  }
  isValidForRichiestaOtp() {
    this.validFormOtp =  this.selectedCa != null && this.selectedCa != undefined
        && this.selectedOtpType != null && this.selectedOtpType != undefined
        && this.validAlias()
        && this.validPin();
  }

  isValidForFirma() {
    return
  }

  firstAlias:boolean = true;
  validAlias() {
    this.firstAlias = false;

    return this.alias != null && this.alias != undefined && this.alias != '' && this.alias != "";
  }

  firstPin:boolean = true;
  validPin() {
    this.firstPin = false;

    return this.pin != null && this.pin != undefined && this.pin != '' && this.pin != '' && !isNaN(+this.pin) && this.pin.length == 8;
  }

  firstOtp:boolean = true;
  validOtp() {
    this.firstOtp = false;

    return this.otp != null && this.otp != undefined && this.otp != '' && this.otp != '' && !isNaN(+this.otp) && this.otp.length == 10;
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }

}
