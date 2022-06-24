import { Diagnosi } from "app/certificato-wizard/tipologia-cert/diagnosi.model";
import { Esenzione } from "app/certificato-wizard/tipologia-cert/esenzione.model";

export class FormData {
    gruppoEsenzione: string;
    diagnosi: Diagnosi;
    esenzione: Esenzione;
    patologia: string;

    clear() {
        this.gruppoEsenzione = '';
        this.diagnosi = new Diagnosi();
        this.esenzione = new Esenzione();
        this.patologia = '';
    }
}

export class GruppoEsenzione {
    gruppoEsenzione: string = '';
}

export class TipologiaCert {
    diagnosi: Diagnosi;
    esenzione: Esenzione;
    patologia: string;
}
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
