import { Component, OnInit, Input } from '@angular/core';

import { EsenFormData, DocumentiEsenzione } from '../data/esenFormData.model';
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { Assistito } from "app/assistito/assistito";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { InitEsenFields } from "app/esenzione-wizard/initalizeEsenFields.service";
import { EsenErrorHandler } from "app/esenzione-wizard/esenErrorHandler";
import { DatePipe } from '@angular/common';
import { ConfigService } from "app/services/config.service";
import { Http, RequestOptions, Headers } from "@angular/http";
import { Observable } from "rxjs";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-riepilogo-esen',
  templateUrl: './riepilogo-esen.component.html',
  styleUrls: ['./riepilogo-esen.component.css']
})
export class RiepilogoEsenComponent implements OnInit {


    @Input() esenFormData: EsenFormData;
    isFormValid: boolean = false;
    assistito: Assistito;
    noteServizio: string;
    noteInterne: string;
    loading: boolean = false;
    prestazioniEsenti: string;

    lista_documenti: DocumentiEsenzione[];

    baseUrl = this.config.getBEServerContext();

    constructor(private esenFormDataService: EsenFormDataService,
            private workFlowService: EsenWorkflowService,
            private assistitoStoreService: AssistitoStoreService,
            private initEsenFields: InitEsenFields,
            private config: ConfigService,
            private http: Http,
            private errorHandler: EsenErrorHandler){
    }

    ngOnInit() {
        this.lista_documenti = this.esenFormDataService.getDocumentiEsen();
        this.assistito = this.assistitoStoreService.getAssistito();
        this.esenFormData = this.esenFormDataService.getFormData();
        this.isFormValid = this.esenFormDataService.isFormValid();
        this.getPrestazioniEsenti();
        this.errorHandler.setHidden(true);
        console.log('Riepilogo feature loaded!');
    }

    getPrestazioniEsenti() {

        this.loading = true;

        this.initEsenFields.getPrestazioniEsenti(this.esenFormData.diagnosi.codice_diagnosi)
        .subscribe(
               result => {
                   console.log('success!');
                   let statusCode = result.status;
                   var str = result._body;
                   var objmsg = JSON.parse( str );
                   if(objmsg != undefined && objmsg[0] != undefined) {
                        this.prestazioniEsenti = objmsg[0].descrizione
                            .replace('/\n/g', "&#10;")
                            .replace('/\t/g', "&#09;");
                   }

                   this.loading = false;
                },
                error => {
                   console.log('error occured!');

                   let statusCode = error.status;

                   if(statusCode === 0){
                       this.errorHandler.setHidden(false);
                       this.errorHandler.setErrorMessage("ATTENZIONE! Servizio non disponibile. Riprovare pi&ugrave; tardi.");
                   }else{
                       var str = error._body;
                       var objmsg = JSON.parse( str );
                       this.errorHandler.setHidden(false);
                       this.errorHandler.setErrorMessage(objmsg.message);
                   }

                   this.loading = false;
                }
        );
    }


    submit() {

        let malattia = {
            "gruppoEsenzione": this.esenFormData.gruppoEsenzione,
            "codDiagnosi": this.esenFormData.diagnosi.codice_diagnosi,
            "codEsenzione": this.esenFormData.diagnosi.codice_esenzione
        };

        let postData = {
                "assistito": this.assistito,
                "malattia": malattia,
                "documenti" : this.lista_documenti,
                "noteServizio": this.noteServizio,
                "noteInterne": this.noteInterne
                };

        this.loading=true;
        this.creaEsenzione(JSON.stringify(postData))
            .subscribe(
                result => {
                    console.log("success");
                    let statusCode = result.status;
                    var str = result._body;
                    var objmsg = JSON.parse( str );

                    this.esenFormData = this.esenFormDataService.resetFormData();
                    this.isFormValid = false;
                    this.workFlowService.setEsenNumber(objmsg.num_esenzione);
                    this.workFlowService.setCurrentStep(5);
                    this.loading = false;
                 },
                 error => {
                     console.log('error occured!');

                     let statusCode = error.status;

                     if(statusCode === 0){
                         this.errorHandler.setHidden(false);
                         this.errorHandler.setErrorMessage("ATTENZIONE! Servizio non disponibile. Riprovare più tardi.");
                     }else{
                         var str = error._body;
                         var objmsg = JSON.parse( str );
                         this.errorHandler.setHidden(false);
                         this.errorHandler.setErrorMessage(objmsg.message);
                     }

                     this.loading = false;
                  }
         );

    }

    creaEsenzione(postData): Observable<Response | any> {
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/inviaRichiestaEsenzione`;
        this.loading = true;
        return this.http.post(url,postData, options);
    }

    goToBeneficiario() {
        //TODO
        console.log('go to edit beneficiario');
    }

    goToGruppoEsenzione() {
        this.workFlowService.setCurrentStep(1);
    }

    goToDiagnosi() {
        this.workFlowService.setCurrentStep(2);
    }

    goToDocumenti() {
        this.workFlowService.setCurrentStep(3);
    }

    goToBack(){
        this.workFlowService.setCurrentStep(3);
    }
  }

