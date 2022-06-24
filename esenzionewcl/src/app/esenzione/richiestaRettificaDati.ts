import { beneficiario } from "app/assistito/beneficiario";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class RichiestaRettificaDati {

    public beneficiario: beneficiario;
    public skPraticaEsenzione: string;
    public skDocumento: string;
    public nota: string;
    public notainterna: string;
    public notabeneficiario: string;
    public codiceMotivazioneTipo: string;
    public richiestaRettificaDestinatario: string;
}

