export class CaModel {
    sk :number;
    codice :string;
    descrizione :string;
    tipiOtp :OtpTypeModel[];
}
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class OtpTypeModel {
    sk :number;
    codice :string;
    descrizione :string;
}
