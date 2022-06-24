import { Component, OnInit } from '@angular/core';
import { GruppoEsenzione } from "app/certificato-wizard/data/formData.model";
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { EsenErrorHandler } from '../esenErrorHandler';
import { TipologiaEsen, DocumentiEsenzione } from '../data/esenFormData.model';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-gruppo-esenzione-esen',
  templateUrl: './gruppo-esenzione.component.html',
  styleUrls: ['./gruppo-esenzione.component.css']
})
export class GruppoEsenComponent implements OnInit {
  title = 'Gruppo Esenzione';
  gruppoEsenzione: GruppoEsenzione;
  form: any;



  constructor(
          private esenFormDataService: EsenFormDataService,
          private workFlowService: EsenWorkflowService,
          private assistitoStoreService: AssistitoStoreService,
          private errorHandler: EsenErrorHandler) { }

  ngOnInit() {
      this.gruppoEsenzione = this.esenFormDataService.getGruppoEsenzione();
      console.log('GruppoEsenzione feature loaded!');
  }

  goToNext(gruppoEsen: string) {
        this.gruppoEsenzione = {gruppoEsenzione: gruppoEsen};

        if (this.save()) {
            // Navigate to page 2
            this.workFlowService.setCurrentStep(2);
        }
        this.errorHandler.setHidden(true);
  }

  save(): boolean {
      if(this.esenFormDataService.getGruppoEsenzione().gruppoEsenzione != this.gruppoEsenzione.gruppoEsenzione) {
        this.esenFormDataService.setTipologiaEsen(new TipologiaEsen());
        let list:DocumentiEsenzione[];
        this.esenFormDataService.setDocumentiEsen(list);
      }
      this.esenFormDataService.setGruppoEsenzione(this.gruppoEsenzione);
      return true;
  }

}
