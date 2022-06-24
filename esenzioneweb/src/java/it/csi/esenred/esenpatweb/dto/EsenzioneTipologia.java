/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;

public class EsenzioneTipologia {
	
	private String codEsenzione;
	
	private String descEsenzione;

	public EsenzioneTipologia(EsenzioneDEsenzione eDB) {
		this.codEsenzione = eDB.getCodEsenzione();
		this.descEsenzione = eDB.getDescEsenzione();
	}
	

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getDescEsenzione() {
		return descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}


	@Override
	public String toString() {
		return "Esenzione [codEsenzione=" + codEsenzione + ", descEsenzione=" + descEsenzione +"]";
	}
}
