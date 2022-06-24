import { Stato } from "app/esenzione/Stato";
import { UtenteDa } from "app/assistito/UtenteDa";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class StoricoEsenzione {
    id: string;
    data_creazione: string;
    nuovo_stato: Stato;
    utente: UtenteDa;
    nota: string;
//    notaBe: string;
//    notaInt: string;
}
