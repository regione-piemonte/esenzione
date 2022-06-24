import { Component, OnInit } from '@angular/core';
import { GruppoEsenzione, TipologiaCert } from "app/certificato-wizard/data/formData.model";
import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { AssistitoStoreService } from 'app/assistito/assistito.service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-gruppo-esenzione-cert',
  templateUrl: './gruppo-esenzione.component.html',
  styleUrls: ['./gruppo-esenzione.component.css']
})
export class GruppoEsenzioneComponent implements OnInit {
  title = 'Gruppo Esenzione';
  gruppoEsenzione: GruppoEsenzione;
  form: any;



  constructor(private formDataService: FormDataService, private workFlowService: WorkflowService, private errorHandler: CertErrorHandler, private assistitoStoreService: AssistitoStoreService,) { }

  ngOnInit() {
      this.errorHandler.setHidden(true);
      this.gruppoEsenzione = this.formDataService.getGruppoEsenzione();
      console.log('GruppoEsenzione feature loaded!');
  }

  goToNext(gruppoEsen: string) {
        this.gruppoEsenzione = {gruppoEsenzione: gruppoEsen};

        if (this.save()) {

            this.workFlowService.setCurrentStep(2);
        }

  }

  save(): boolean {
    if(this.formDataService.getGruppoEsenzione().gruppoEsenzione != this.gruppoEsenzione.gruppoEsenzione) {
      this.formDataService.setTipologiaCert(new TipologiaCert());
    }
      this.formDataService.setGruppoEsenzione(this.gruppoEsenzione);
      return true;
  }

}
