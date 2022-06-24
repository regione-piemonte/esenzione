import { beneficiario } from "app/assistito/beneficiario";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class certificato {
    public sk_documento: string;
    public diagnosi: string;
    public cod_diagnosi: string;
    public codice_esenzione: string;
    public data_inserimento: string;
    public medico_firmatario: string;
    public medico_redattore: string;
    public beneficiario: beneficiario;
    public desc_gruppo: string;
    public desc_esenzione: string;
    public numeroTotaleElementi: number;
}
