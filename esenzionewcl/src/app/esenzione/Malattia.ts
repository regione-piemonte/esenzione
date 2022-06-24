import { Prestazioni } from "./Prestazioni";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class Malattia {
    codice: string;
    descrizione: string;
    giorni_validita: number;
    prestazioni: Prestazioni[];

    constructor() {
        this.prestazioni = [];
    }
}
