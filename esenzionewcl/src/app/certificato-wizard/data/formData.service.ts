import { Injectable } from '@angular/core';
import { FormData, GruppoEsenzione, TipologiaCert } from './formData.model';
import { WorkflowService } from '../workflow/workflow.service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class FormDataService {

    private formData: FormData = new FormData();
    private isGruppoEsenzioneFormValid: boolean = false;
    private isTipologiaCertFormValid: boolean = false;

    constructor(private workflowService: WorkflowService) {
    }

    getGruppoEsenzione(): GruppoEsenzione {
        var gruppoEsenzione: GruppoEsenzione =
            {
                gruppoEsenzione: this.formData.gruppoEsenzione
            };

        return gruppoEsenzione;
    }

    setGruppoEsenzione(data: GruppoEsenzione) {
        this.isGruppoEsenzioneFormValid = true;

        this.formData.gruppoEsenzione = data.gruppoEsenzione;

        console.log(this.formData);
    }

    getTipologiaCert(): TipologiaCert {
        var tipologiaCert: TipologiaCert =
            {
                diagnosi: this.formData.diagnosi,
                esenzione: this.formData.esenzione,
                patologia: this.formData.patologia
            };

        return tipologiaCert;
    }

    setTipologiaCert(data: TipologiaCert) {
        this.isTipologiaCertFormValid = true;

        this.formData.diagnosi = data.diagnosi;
        this.formData.esenzione = data.esenzione;
        this.formData.patologia = data.patologia;

        console.log(this.formData);
    }



    getFormData(): FormData {
        // Return the entire Form Data
        return this.formData;
    }

    resetFormData(): FormData {
        // Return the form data after all this.* members had been reset
        this.formData.clear();
        this.isGruppoEsenzioneFormValid = this.isTipologiaCertFormValid = false;
        return this.formData;
    }

    isFormValid() {
        // Return true if all forms had been validated successfully; otherwise, return false
        return  this.isGruppoEsenzioneFormValid &&
                this.isTipologiaCertFormValid
    }
}
