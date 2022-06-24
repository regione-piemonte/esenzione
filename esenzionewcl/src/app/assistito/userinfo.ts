import { Azione } from "app/assistito/azione";
import { Ruolo } from "app/assistito/ruolo";

export class UserInfo {
    codFisc: string;
    cognome: string;
    nome: string;
    codASL: string;
    idAura: string;
    azioni: Azione[];
    ruoli: Ruolo[];
}
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
