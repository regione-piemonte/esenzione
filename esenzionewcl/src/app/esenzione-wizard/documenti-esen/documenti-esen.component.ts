import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TipoDocumento } from "../tipologia-esen/tipo-documento.model";
import { DocumentiEsenzione } from "../data/esenFormData.model";
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { InitEsenFields } from "app/esenzione-wizard/initalizeEsenFields.service";
import { EsenErrorHandler } from "app/esenzione-wizard/esenErrorHandler";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
    selector: 'app-documenti-esen',
    templateUrl: './documenti-esen.component.html',
    styleUrls: ['./documenti-esen.component.css']
})
export class DocumentiEsenComponent implements OnInit {

    isDataRilascioValid: boolean  = true;
    isDataValiditaValid: boolean  = true;

    fileSelected: boolean = false;

    loading: boolean = false;

    @ViewChild('dataRilascio') dataRilascio: ElementRef;
    @ViewChild('dataValidita') dataValidita : ElementRef;
    @ViewChild('inputFile') myInputFile : ElementRef;


//    documentiEsenzione: DocumentiEsenzione;

    lista_documenti: DocumentiEsenzione [];

    fileToUpload: File = null;
    tipoDocumentoCode: string;
    tipoDocumentoSel: {codice:string, descrizione: string};
    nomeFile: string;
    base64File: string;

    constructor(private esenformDataService: EsenFormDataService,
                private workFlowService: EsenWorkflowService,
                private initEsenFields: InitEsenFields,
                private errorHandler: EsenErrorHandler) { }

    ngOnInit() {

        this.initializeTipiDocumento();
        this.errorHandler.setHidden(true);

        this.lista_documenti  = [];
        if(this.esenformDataService.getDocumentiEsen() != undefined &&
            this.esenformDataService.getDocumentiEsen().length != 0)
        this.lista_documenti = this.esenformDataService.getDocumentiEsen();
        console.log('DocumentiEsenificato feature loaded!');
    }

    handleFileInput(files: FileList) {
        this.fileSelected = true;
        this.fileToUpload = files.item(0);
        this.nomeFile = this.fileToUpload.name;
        this.getBase64();
    }

    addDocumento(){
        let checkDataRilascio = this.dataRilascio.nativeElement.value;
        let checkDatavalidita = this.dataValidita.nativeElement.value;

        if(this.validDate(checkDataRilascio, checkDatavalidita)){
            this.tipoDocumentoCode = this.tipoDocumentoSel.codice;
            let documento : DocumentiEsenzione =
            {
                    documentoId: "",
                    filename: this.nomeFile,
                    fileBase64: this.base64File,
                    tipologia: this.tipoDocumentoCode,
                    dataRilascio: this.dataRilascio.nativeElement.value,
                    dataFineValidita: this.dataValidita.nativeElement.value,
                    descrizione: this.tipoDocumentoSel.descrizione
            };

            this.lista_documenti.push(documento);

          //reset form documento
            this.tipoDocumentoCode = '';
            this.tipoDocumentoSel = {
                codice: "",
                descrizione: ""
            };
            this.dataRilascio.nativeElement.value = '';
            this.dataValidita.nativeElement.value = '' ;
            this.myInputFile.nativeElement.value = '' ;
            this.base64File = ''
            this.nomeFile = ''
            this.fileSelected = false;
            this.isDataRilascioValid = true;
            this.isDataValiditaValid = true;
        }
    }

    removeDocument(index: number){
        console.log('rimuoviamo: ' + index);
        this.lista_documenti.splice(index,1);
    }

    validDate(dataRilascio: string, dataValidita: string){

        this.isDataRilascioValid = true;
        this.isDataRilascioValid = true;

        //es. dataRilascio: 19/01/2020 (GG/MM/YYYY)
        if(dataRilascio == null || dataRilascio == ''){
            this.isDataRilascioValid = false;
            return false;
        }
        let dateNow = new Date();
        let data_rilascio = this.process(dataRilascio);

        if(dateNow < data_rilascio){
            this.isDataRilascioValid = false;
            return false;
        }


        if(dataValidita != null && dataValidita != ''){
            let data_validita = this.process(dataValidita);

            if(data_validita < dateNow || data_validita < data_rilascio){
                this.isDataValiditaValid = false;
                return false;
            }
        }

        return true;
    }

    process(date): Date{
        var parts = date.split("/");
        return new Date(parts[2], parts[1] - 1, parts[0]);
    }

    goToNext(){

        this.esenformDataService.setDocumentiEsen(this.lista_documenti);

        this.workFlowService.setCurrentStep(4);

        this.errorHandler.setHidden(true);
   }

    goToBack(){
        this.workFlowService.setCurrentStep(2);
        this.errorHandler.setHidden(true);
    }

    getBase64() {
        let me = this;
        let reader = new FileReader();
        reader.readAsDataURL(this.fileToUpload);
        reader.onload = function () {
          me.base64File = reader.result as string;
          me.base64File  = me.base64File.split(",")[1]
        };
        reader.onerror = function (error) {
          console.log('Error: ', error);
        };
     }

    initializeTipiDocumento(){

        //se la lista diagnosi è vuota, richiedila al backend
        //if(this.initEsenFields.tipi_documento.length <= 0){
            this.loading=true;
            this.initEsenFields.getTipiDocumentoFromServer(this.esenformDataService.getGruppoEsenzione().gruppoEsenzione)
            .subscribe(
                   result => {
                       console.log('success!');
                       let statusCode = result.status;
                       var str = result._body;
                       var objmsg = JSON.parse( str );
                       this.initEsenFields.tipi_documento = objmsg.data;

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
        //}
    }
}
