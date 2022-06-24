import { Injectable }         from '@angular/core';
import { FiltroValidaEsenzionePatologia } from "app/certificato-wizard/certificato-validato/filtrovalidaesenzionepatologia";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
//import { STEPS }              from './workflow.model';

@Injectable()
export class WorkflowService {

    private currentStep: number = 1;

    private numCert: string = '';

    private codDiagnosi: string = '';

    private codEsenzione: string = '';

    private provieneDaEsenzione: boolean = false;

    private filtro:FiltroValidaEsenzionePatologia;

    setCurrentStep(step: number){
        this.currentStep = step;
    }

    getCurrentStep(): number {
        return this.currentStep;
    }

    setCertNumber(numeroCertificato : string){
        this.numCert = numeroCertificato;
    }

    getCertNumber(): string {
        return this.numCert;
    }

    setCodDiagnosi(codiceDiagnosi : string){
        this.codDiagnosi = codiceDiagnosi;
    }

    getCodDiagnosi(): string {
        return this.codDiagnosi;
    }

    setCodEsenzione(codiceEsenzione : string){
        this.codEsenzione = codiceEsenzione;
    }

    getCodEsenzione(): string {
        return this.codEsenzione;
    }

    isFromEsenzioni(){
        return this.provieneDaEsenzione;
    }

    setFromEsenzioni(bool: boolean){
        this.provieneDaEsenzione = bool;
    }

    getFiltroRettifica(): FiltroValidaEsenzionePatologia{
        return this.filtro;
    }

    setFiltroRettifica(filter: FiltroValidaEsenzionePatologia){
        this.filtro = filter;
    }

    resetWorkFlow(){
        this.currentStep = 1;
        this.numCert = '';
        this.provieneDaEsenzione = false;
        this.filtro = null;
    }

}
