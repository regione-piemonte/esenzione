import { statodocumento } from "app/statodocumento/statodocumento";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class FiltriRicercaPratiche {
    public stati_pratica: statodocumento[] = [];
    public cod_esenzione: string;
    public valida_dal: string;
    public valida_al: string;
    public cf_beneficiario: string;
    public cognome_beneficiario: string;
    public nome_beneficiario: string;
    public id_aura: string;
    public tab: string;
    public pagina: number;

    constructor() {

    }
}
