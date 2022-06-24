import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import { Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";
import { TipologiaCert } from "app/certificato-wizard/data/formData.model";
import { InitCertFields } from "app/certificato-wizard/initalizeCertFields.service";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { HttpErrorResponse } from "@angular/common/http";
import { SelectDropDownModule, SelectDropDownComponent } from 'assets/custom-modules/ngx-select-dropdown/dist';
import { AssistitoStoreService } from 'app/assistito/assistito.service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-tipologia-cert',
  templateUrl: './tipologia-cert.component.html',
  styleUrls: ['./tipologia-cert.component.css']
})
export class TipologiaCertComponent implements OnInit {

    public loading = false;

    tipologiaCert: TipologiaCert;

    lista_esen: Esenzione[];

    lista_diagnosi: Diagnosi[];

    diagnosiSelected: boolean = false;
    esenzioneSelected: boolean = false;

    @ViewChild('diagnosi') fieldDiagnosi : SelectDropDownComponent;
    @ViewChild('esenzione') fieldEsenzioni : SelectDropDownComponent;

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
          private formDataService: FormDataService,
          private workFlowService: WorkflowService,
          private initCertFields: InitCertFields,
          private errorHandler: CertErrorHandler,
          private assistitoStoreService: AssistitoStoreService,) {
   }


  ngOnInit() {

      this.errorHandler.setHidden(true);
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
                             (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
                     this.lista_diagnosi.forEach( (diagnosi) => {
                             diagnosi['label'] = `${diagnosi.codice_diagnosi} - ${diagnosi.descrizione_diagnosi}`;
                     });

                     this.initCertFields.lista_diagnosi.filter(
                             (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);

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
            // this.fieldDiagnosi.selectedItems = [];
            this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                   (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
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
                            (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
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
        // this.fieldEsenzioni.selectedItems = [];
        this.lista_esen= this.initCertFields.lista_esen.filter(
            (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
         this.lista_esen.forEach( (esen) => {
                 esen['label'] = `${esen.codice_esenzione} - ${esen.descrizione_esenzione}`;
         });
    }

    this.tipologiaCert = new TipologiaCert();
    if(this.formDataService.getTipologiaCert() != undefined &&
        this.formDataService.getTipologiaCert().diagnosi != undefined &&
        this.formDataService.getTipologiaCert().diagnosi.codice_diagnosi != undefined &&
        this.formDataService.getTipologiaCert().esenzione != undefined &&
        this.formDataService.getTipologiaCert().esenzione.codice_esenzione != undefined)
        this.tipologiaCert = this.formDataService.getTipologiaCert();
    console.log('TipologiaCert feature loaded!');
  }


  onEsenzioneSelected(esenzione: Esenzione){
      if (esenzione){
          console.log('esenzione: ' + esenzione.codice_esenzione);
          this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                  (item)=>item.codice_esenzione==esenzione.codice_esenzione && item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
      }else{
          this.lista_diagnosi = this.initCertFields.lista_diagnosi.filter(
                  (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
      }

  }

  onDiagnosiSelected(diagnosi: Diagnosi){
      if(diagnosi){
          console.log('diagnosi: ' + diagnosi.codice_diagnosi);
        this.lista_esen = this.initCertFields.lista_esen.filter(
                (item)=>item.codice_esenzione == diagnosi.codice_esenzione && item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
      }else{
          this.lista_esen = this.initCertFields.lista_esen.filter(
                  (item)=>item.gruppo == this.formDataService.getGruppoEsenzione().gruppoEsenzione);
      }


  }

  save(form: any): boolean {
      if (!form.valid) {
          return false;
      }

      this.formDataService.setTipologiaCert(this.tipologiaCert);
      return true;
  }

  goToNext(tipoCertForm: NgForm){
      if (this.save(tipoCertForm)) {
          this.workFlowService.setCurrentStep(3);
      }
  }

  goToBack(){
      this.workFlowService.setCurrentStep(1);
  }

}
