import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import { Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class EsenFormData {
    gruppoEsenzione: string;
    diagnosi: Diagnosi;
    esenzione: Esenzione;
    registro_interregionale: string;
    trattamento_dati: string;
    lista_documenti: DocumentiEsenzione [] = new Array();

    clear() {
        this.gruppoEsenzione = '';
        this.diagnosi = new Diagnosi();
        this.esenzione = new Esenzione();
        this.registro_interregionale = '';
        this.trattamento_dati = '';
        this.lista_documenti = new Array();;
    }
}

export class GruppoEsenzione {
    gruppoEsenzione: string = '';
}

export class TipologiaEsen {
    diagnosi: Diagnosi;
    esenzione: Esenzione;
    registro_interregionale: string;
    trattamento_dati: string;
}

export class DocumentiEsenzione{
    documentoId: string = '';
    filename: string = '';
    fileBase64: string = '';
    tipologia: string = '';
    dataRilascio: string = '';
    dataFineValidita: string = '';
    descrizione: string = '';
}
