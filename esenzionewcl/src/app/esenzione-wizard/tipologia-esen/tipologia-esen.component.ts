import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import { Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";
import { TipologiaEsen } from "app/esenzione-wizard/data/esenFormData.model";
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { InitCertFields } from "app/certificato-wizard/initalizeCertFields.service";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { DatePipe } from '@angular/common';
import { esenzioneAssistito } from "app/esenzione/esenzioneAssistito";
import { EsenErrorHandler } from "app/esenzione-wizard/esenErrorHandler";
import {forEach} from '@angular/router/src/utils/collection';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-tipologia-esen',
  templateUrl: './tipologia-esen.component.html',
  styleUrls: ['./tipologia-esen.component.css']
})
export class TipologiaEsenComponent implements OnInit {

    public loading = false;

    tipologiaEsen: TipologiaEsen;

    lista_esen: Esenzione[];

    lista_diagnosi: Diagnosi[];

    registro_interregionale: string;

    trattamento_dati: string;

    lista_stati: string [] = ['RINNOVO-RESPINTA', 'RINNOVO-ANNULLATA', 'RESPINTA', 'SCADUTA', 'ANNULLATA', 'REVOCATA'];

    configDiagnosi = {
      displayKey: "label", //if objects array passed which key to be displayed defaults to description
      search:true, //true/false for the search functionlity defaults to false,
      height: '300px', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
      placeholder:'Inserire la diagnosi', // text to be displayed when no item is selected defaults to Select,
      //customComparator: ()=>{} // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
      //limitTo: 0, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
      moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
      noResultsFound: 'Diagnosi non trovata', // text to be displayed when no items are found while searching
      searchPlaceholder:'cerca' // label thats displayed in search input,
      //searchOnKey: 'name' // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
    }

    configEsenzioni = {
      displayKey: "label", //if objects array passed which key to be displayed defaults to description
      search:true, //true/false for the search functionlity defaults to false,
      height: '300px', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
      placeholder: "Inserire l'esenzione", // text to be displayed when no item is selected defaults to Select,
      //customComparator: ()=>{} // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
      //limitTo: 0, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
      moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
      noResultsFound: 'Esenzione non trovata', // text to be displayed when no items are found while searching
      searchPlaceholder:'cerca' // label thats displayed in search input,
      //searchOnKey: 'name' // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
    }

    constructor(
        private esenFormDataService: EsenFormDataService,
        private workFlowService: EsenWorkflowService,
        private initCertFields: InitCertFields,
        private errorHandler: EsenErrorHandler,
        private assistitoStore: AssistitoStoreService)
    {}

    ngOnInit() {

        //se la lista diagnosi è vuota, richiedila al backend
        if(this.initCertFields.lista_diagnosi.length <= 0){
            this.loading=true;
            this.initCertFields.getDiagnosiFromServer()
            .subscribe(
                   result => {
                       console.log('success!');
                       let statusCode = result.status;
                       var str = result._body;
                       var objmsg = JSON.parse( str );
                       this.initCertFields.lista_diagnosi = objmsg.data;
                       this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                               (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
                       this.lista_diagnosi.forEach( (diagnosi) => {
                               diagnosi['label'] = `${diagnosi.codice_diagnosi} - ${diagnosi.descrizione_diagnosi}`;
                        });
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
        }else{
            this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                    (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
            this.lista_diagnosi.forEach( (diagnosi) => {
                    diagnosi['label'] = `${diagnosi.codice_diagnosi} - ${diagnosi.descrizione_diagnosi}`;
            });
        }

        if(this.initCertFields.lista_esen.length <= 0){
            this.loading=true;
            this.initCertFields.getEsenzioniFromServer()
            .subscribe(
                   result => {
                       console.log("success");
                       let statusCode = result.status;
                       var str = result._body;
                       var objmsg = JSON.parse( str );
                       this.initCertFields.lista_esen = objmsg.data;
                       this.lista_esen = this.initCertFields.lista_esen.filter(
                               (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
                       this.lista_esen.forEach( (esen) => {
                                esen['label'] = `${esen.codice_esenzione} - ${esen.descrizione_esenzione}`;
                       });
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
        }else{
            this.lista_esen = this.initCertFields.lista_esen.filter(
                    (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
            this.lista_esen.forEach( (esen) => {
                    esen['label'] = `${esen.codice_esenzione} - ${esen.descrizione_esenzione}`;
            });
        }

        //this.tipologiaEsen = this.esenFormDataService.getTipologiaEsen();
        this.tipologiaEsen =  new TipologiaEsen();
        if(this.esenFormDataService.getTipologiaEsen() != undefined &&
            this.esenFormDataService.getTipologiaEsen().diagnosi != undefined &&
            this.esenFormDataService.getTipologiaEsen().diagnosi.codice_diagnosi != undefined &&
            this.esenFormDataService.getTipologiaEsen().esenzione != undefined &&
            this.esenFormDataService.getTipologiaEsen().esenzione.codice_esenzione != undefined)
          this.tipologiaEsen = this.esenFormDataService.getTipologiaEsen();
        console.log('TipologiaCert feature loaded!');
    }


    onEsenzioneSelected(esenzione: Esenzione){
        if (esenzione){
            console.log('esenzione: ' + esenzione.codice_esenzione);
            this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                    (item)=>item.codice_esenzione==esenzione.codice_esenzione && item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
        }else{
            this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                    (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
        }
    }

    onDiagnosiSelected(diagnosi: Diagnosi){
        if(diagnosi){
            console.log('diagnosi: ' + diagnosi.codice_diagnosi);
          this.lista_esen = this.initCertFields.lista_esen.filter(
                  (item)=>item.codice_esenzione == diagnosi.codice_esenzione && item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
        }else{
            this.lista_esen = this.initCertFields.lista_esen.filter(
                    (item)=>item.gruppo == this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione);
        }
    }

  save(form: any): boolean {
      if (!form.valid) {
          return false;
      }

      this.esenFormDataService.setTipologiaEsen(this.tipologiaEsen);
      return true;
  }

  goToNext(tipoEsenForm: NgForm) {
i
    this.errorHandler.hidden = true;
    for (let esen of this.assistitoStore.getListaEsenzioni()) {
      let valido: boolean = true;


      if (esen.codice_esenzione == this.tipologiaEsen.diagnosi.codice_esenzione) {
        if (this.lista_stati.indexOf(esen.stato_pratica.toUpperCase()) == -1) {
          valido = false;

        }
      }

      if (!valido) {
        this.errorHandler.setErrorMessage('ATTENZIONE! Non è possibile richiedere l\'esenzione: poiché è già presente');
        this.errorHandler.setHidden(false);
        return;
      }
    }
    if (this.save(tipoEsenForm)) {
      this.workFlowService.setCurrentStep(3);
    }
  }

  goToBack(){
      this.workFlowService.setCurrentStep(1);
      this.errorHandler.hidden = true;
  }

  process(date): Date{
      if(date == null)
          return null;

      var parts = date.split("/");
      return new Date(parts[2], parts[1] - 1, parts[0]);
   }
}
