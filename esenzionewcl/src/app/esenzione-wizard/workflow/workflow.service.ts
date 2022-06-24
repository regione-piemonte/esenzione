import { Injectable }         from '@angular/core';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
//import { STEPS }              from './workflow.model';

@Injectable()
export class EsenWorkflowService  {

    private currentStep: number = 1;

    private numEsen: string = '';

    private fromCert: boolean = false;

    setCurrentStep(step: number){
        this.currentStep = step;
    }

    getCurrentStep(): number {
        return this.currentStep;
    }


    resetWorkFlow(){
        this.currentStep = 1;
        this.numEsen = '';
        this.fromCert = false;
    }

    setEsenNumber(numeroCertificato : string){
        this.numEsen = numeroCertificato;
    }

    getEsenNumber(): string {
        return this.numEsen;
    }

    setFromCert(bool : boolean) {
        this.fromCert = bool;
    }

    getFromCert() {
        return this.fromCert;
    }
}
