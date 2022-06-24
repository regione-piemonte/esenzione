import { Injectable } from '@angular/core';
import { EsenFormData, GruppoEsenzione, TipologiaEsen, DocumentiEsenzione } from './esenFormData.model';
import { EsenWorkflowService } from '../workflow/workflow.service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class EsenFormDataService {

    private esenFormData: EsenFormData = new EsenFormData();
    private isGruppoEsenzioneFormValid: boolean = false;
    private isTipologiaEsenFormValid: boolean = false;
    private isDocumentiEsenFormValid: boolean = false;

    constructor(private workflowService: EsenWorkflowService) {
    }

    getGruppoEsenzione(): GruppoEsenzione {
        var gruppoEsenzione: GruppoEsenzione =
            {
                gruppoEsenzione: this.esenFormData.gruppoEsenzione
            };

        return gruppoEsenzione;
    }

    setGruppoEsenzione(data: GruppoEsenzione) {
        this.isGruppoEsenzioneFormValid = true;

        this.esenFormData.gruppoEsenzione = data.gruppoEsenzione;

        console.log(this.esenFormData);
    }

    getTipologiaEsen(): TipologiaEsen {
        var tipologiaEsen: TipologiaEsen =
            {
                diagnosi: this.esenFormData.diagnosi,
                esenzione: this.esenFormData.esenzione,
                registro_interregionale: this.esenFormData.registro_interregionale,
                trattamento_dati: this.esenFormData.trattamento_dati
            };
        return tipologiaEsen;
    }

    setTipologiaEsen(data: TipologiaEsen) {
        this.isTipologiaEsenFormValid = true;

        this.esenFormData.diagnosi = data.diagnosi;
        this.esenFormData.esenzione = data.esenzione;
        this.esenFormData.registro_interregionale = data.registro_interregionale;
        this.esenFormData.trattamento_dati = data.trattamento_dati;

        console.log(this.esenFormData);
    }

    getDocumentiEsen(): DocumentiEsenzione [] {


        return this.esenFormData.lista_documenti;
    }

    setDocumentiEsen(lista_documenti: DocumentiEsenzione []) {
        this.isDocumentiEsenFormValid = true;


        this.esenFormData.lista_documenti = lista_documenti;

        console.log(this.esenFormData);
    }

    getFormData(): EsenFormData {

        return this.esenFormData;
    }

    resetFormData(): EsenFormData {

        this.esenFormData.clear();
        this.isGruppoEsenzioneFormValid = this.isTipologiaEsenFormValid = this.isDocumentiEsenFormValid = false;
        return this.esenFormData;
    }

    isFormValid() {
se
        return  this.isGruppoEsenzioneFormValid &&
                this.isGruppoEsenzioneFormValid &&
                this.isTipologiaEsenFormValid &&
                this.isDocumentiEsenFormValid;
    }
}
