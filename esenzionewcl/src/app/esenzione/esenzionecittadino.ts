import { Assistito } from "app/assistito/assistito";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class EsenzioneCittadino {
   constructor(
           public idEsenzione: number,
           public codTitolo: string,
           public descTitolo: string,
           public idAuraBeneficiario: string,
           public nomeBeneficiario: string,
           public cognomeBeneficiario: string,
           public codFiscaleBeneficiario: string,
           public codSessoBeneficiario: string,
           public dataNascitaBeneficiario: string,
           public comuneNascitaBeneficiario: string,
           public codcomuneNascitaBeneficiario: string,
           public provinciaNascitaBeneficiario: string,
           public stato: string,
           public codStato: string,
           public codEsenzione: string,
           public descEsenzione: string,
           public dataRichiesta: string,
           public inizioValidita: string,
           public fineValidita: string,
           public revocabile: boolean,
           public stampabile: boolean,
           public numProtocolloSogei: string,
           public motivoRevoca: string,
           public dataRevoca: string,
           public indirizzoResidenza: string,
           public capResidenza: string,
           public cittaResidenza: string,
           public titolare: Assistito,
           public sititolare: boolean,
           public idUserInsert:string
               ) { }
}
