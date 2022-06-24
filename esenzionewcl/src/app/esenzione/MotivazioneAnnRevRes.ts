import { Motivazione } from "./Motivazione";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class MotivazioneAnnRevRes {
    public motivazione: Motivazione;
    public nota_beneficiario: string;
    public nota_servizio: string;
    public nota_interna: string;

    constructor() {
        this.motivazione = new Motivazione();
    }
}
