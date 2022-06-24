import { beneficiario } from "app/assistito/beneficiario";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class CertificatoPatologia {
    beneficiario: beneficiario;
    id_certificato: string;
    tipo_certificato: string;
    esenzione: any | {
        codice: string,
        descrizione: string
    };
    diagnosi: any | {
        codice: string,
        descrizione: string
    };
    descrizione: string;
    data_emissione: string;
    utilizzabile: boolean;
    sk_pratica: number;

    constructor() {
        this.beneficiario = new beneficiario();
        this.esenzione = {}
        this.esenzione.codice = "" as string;
        this.esenzione.descrizione = "" as string;
        this,this.diagnosi = {}
        this.diagnosi.codice = "" as string;
        this.diagnosi.descrizione = "" as string;
        this.utilizzabile = null;
    }
}
