import { Component, OnInit, Input } from '@angular/core';

import { FormData } from '../data/formData.model';
import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { Assistito } from "app/assistito/assistito";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { Certificato } from "app/certificato-wizard/data/certificatoData";
import { Http, RequestOptions, Headers } from "@angular/http";
import { ConfigService } from "app/services/config.service";
import { DatePipe } from '@angular/common';
import { Observable } from "rxjs";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-riepilogo-cert',
  templateUrl: './riepilogo-cert.component.html',
  styleUrls: ['./riepilogo-cert.component.css']
})
export class RiepilogoCertComponent implements OnInit {

    @Input() formData: FormData;
    isFormValid: boolean = false;
    assistito: Assistito;
    hideError = true;
    errorMessage: string;
    noteAsl: string;
    loading = false;

    baseUrl = this.config.getBEServerContext();

    constructor(private formDataService: FormDataService,
                private workFlowService: WorkflowService,
                private assistitoStoreService: AssistitoStoreService,
                private http: Http, private config: ConfigService,
                private errorHandler: CertErrorHandler) {
    }

    ngOnInit() {
        this.errorHandler.setHidden(true);

        this.assistito = this.assistitoStoreService.getAssistito();
        this.formData = this.formDataService.getFormData();
        this.isFormValid = this.formDataService.isFormValid();
        console.log('Riepilogo feature loaded!');
    }


    submit() {
        const format = 'dd/MM/yyyy';

        var datePipe = new DatePipe('en-US');
        let dateNow = datePipe.transform(new Date(), format);

        let certificato = {
            "data_invio": dateNow,
            "gruppoEsenzione": this.formData.gruppoEsenzione,
            "codDiagnosi": this.formData.diagnosi.codice_diagnosi,
            "codEsenzione": this.formData.esenzione.codice_esenzione,
            "notePatologia": this.formData.patologia,
            "noteAsl": this.noteAsl
        };

        let postData = {
                "certificato": certificato,
                "assistito": this.assistito
                };

        this.loading=true;
        this.creaCertificato(JSON.stringify(postData))
            .subscribe(
                result => {
                    console.log("success");
                    let statusCode = result.status;
                    var str = result._body;
                    var objmsg = JSON.parse( str );
                    this.workFlowService.setCodDiagnosi(this.formData.diagnosi.codice_diagnosi);
                    this.workFlowService.setCodEsenzione(this.formData.esenzione.codice_esenzione);
                    this.formData = this.formDataService.resetFormData();
                    this.isFormValid = false;
                    this.workFlowService.setCertNumber(objmsg.num_certificato);
                    this.workFlowService.setCurrentStep(4);

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

    goToBack(){
        this.workFlowService.setCurrentStep(2);
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

    creaCertificato(postData): Observable<Response | any> {
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' ,'Accept': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        const url = `${this.baseUrl}/nuovoCertificato`;
        this.loading = true;
        return this.http.post(url,postData, options);
    }

  }
