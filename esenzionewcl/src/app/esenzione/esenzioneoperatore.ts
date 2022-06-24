import { Assistito } from "app/assistito/assistito";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class EsenzioneOperatore {
   constructor(
           public idEsenzione: number,
           public codTitolo: string,
           public descTitolo: string,
           public idAuraBeneficiario: string,
           public codFiscaleBeneficiario: string,
           public nomeBeneficiario: string,
           public cognomeBeneficiario: string,
           public codSessoBeneficiario: string,
           public dataNascitaBeneficiario: string,
           public comuneNascitaBeneficiario: string,
           public provinciaNascitaBeneficiario: string,
           public codFiscaleRichiedente: string,
           public nomeRichiedente: string,
           public cognomeRichiedente: string,
           public codSessoRichiedente: string,
           public dataNascitaRichiedente: string,
           public comuneNascitaRichiedente: string,
           public provinciaNascitaRichiedente: string,
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
	       public nota: string,
	       public numeroTotaleElementi: number,
	       public titolare: Assistito,
           public sititolare: boolean,
           public idUserInsert:string,
           public motivo_nonvalida: string,
           public attestato: string
               ) { }
}
















