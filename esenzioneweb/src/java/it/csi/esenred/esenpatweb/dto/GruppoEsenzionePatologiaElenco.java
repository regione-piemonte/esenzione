/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;

public class GruppoEsenzionePatologiaElenco {
	private String codTipologiaGruppo;
	private String descGruppo;
	


	public GruppoEsenzionePatologiaElenco(EsenzioneDGruppoEsenzioni eDB) {
		this.codTipologiaGruppo = eDB.getCodTipologiaGruppo();
		this.descGruppo = eDB.getDescGruppo();
	}


	public String getCodTipologiaGruppo() {
		return codTipologiaGruppo;
	}



	public void setCodTipologiaGruppo(String codTipologiaGruppo) {
		this.codTipologiaGruppo = codTipologiaGruppo;
	}



	public String getDescGruppo() {
		return descGruppo;
	}



	public void setDescGruppo(String descGruppo) {
		this.descGruppo = descGruppo;
	}



	@Override
	public String toString() {
		return "GruppoEsenzionePatologiaElenco [codTipologiaGruppo=" + codTipologiaGruppo + ", descGruppo=" + descGruppo
				+ "]";
	}

}


