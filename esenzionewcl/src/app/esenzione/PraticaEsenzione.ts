import { beneficiario } from "app/assistito/beneficiario";
import { Codice } from "./Codice";
import { Stato } from "./Stato";
import { Tipologia } from "./Tipologia";
import { Malattia } from "./Malattia";
import { UtenteDa } from "app/assistito/UtenteDa";
import { Motivazione } from "./Motivazione";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class PraticaEsenzione {
    id: string;
    numero_pratica: string;
    beneficiario: beneficiario;
    data_richiesta: string;
    data_revoca: string;
    data_annullamento: string;
    dataAnnullamento: string;
    data_scadenza: string;
    data_aggiornamento: string;
    data_inizio_validita: string;
    data_fine_validita: string;
    certificato_id: string;
    zip: boolean;
    archiviata: boolean;
    annullabile: boolean;
    rinnovabile: boolean;
    revocabile: boolean;
    in_scadenza: boolean;
    codice: Codice;
    stato: Stato;
    tipologia: Tipologia;
    malattia: Malattia;
    richiesto_da: UtenteDa;
    revocato_da: UtenteDa;
    annullato_da: UtenteDa;
    motivazione_revoca: Motivazione;
    motivazione_annullamento: Motivazione;
    motivazione: Motivazione;

    constructor() {
        this.beneficiario = new beneficiario;
        this.codice = new Codice;
        this.stato = new Stato;
        this.tipologia = new Tipologia;
        this.malattia = new Malattia;
        this.richiesto_da = new UtenteDa;
        this.revocato_da = new UtenteDa;
        this.annullato_da = new UtenteDa;
        this.motivazione_revoca = new Motivazione;
        this.motivazione_annullamento = new Motivazione;
        this.motivazione = new Motivazione;
    }
}
