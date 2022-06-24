import { beneficiario } from "app/assistito/beneficiario";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class esenzioneAssistito {
    public numero_pratica: string;
    public sk_pratica_esenzione: string;
    public diagnosi: string;
    public cod_diagnosi: string;
    public asl: string;
    public beneficiario: beneficiario;
    public compilatore: string;
    public codice_esenzione: string;
    public stato_pratica: string;
    public data_richiesta: string;
    public valida_dal: string;
    public data_scadenza: string;
    public data_sospensione: string;
    public desc_gruppo: string;
    public desc_esenzione: string;
    public numeroTotaleElementi: string;
    public asl_denominazione: string;
    public distretto: string;
    public visualizza: boolean;
    }
