import { Assistito } from "app/assistito/assistito";
import { certificato } from "../../esenzione/certificato";
import { esenzioneAssistito } from "app/esenzione/esenzioneAssistito";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ricercaAssistitoResponse {
    assistito: Assistito;
    lista_certificati: certificato[];
    lista_esenzioni: esenzioneAssistito[];
    elenco_assistiti: Assistito[];

    constructor() {
        this.assistito = new Assistito();
        this.lista_certificati = [];
        this.lista_esenzioni = [];
        this.elenco_assistiti = [];
    }
}
