import { beneficiario } from "app/assistito/beneficiario";
import { DocumentiEsenzione } from "app/esenzione-wizard/data/esenFormData.model";

export class FiltroValidaEsenzionePatologia {
    public dataInizioValidita: string;
    public dataFineValidita: string;
    public skPraticaEsenzione: string;
    public codEsenzione: string;
    public notainterna: string;
    public nota: string;
    public notabeneficiario: string;
    public beneficiario: beneficiario;
    public azione : string;
    public documentiallegati: DocumentiEsenzione [];
}
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
