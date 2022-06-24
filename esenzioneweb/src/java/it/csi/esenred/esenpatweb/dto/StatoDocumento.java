/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import java.util.HashMap;
import java.util.Map;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;

public class StatoDocumento {
	
	private String codStato;
	
	private String descStato;

	public StatoDocumento() {
		
	}
	public StatoDocumento(EsenzioneDDocumentoStato eDB) {
		this.codStato = eDB.getCodStato();
		this.descStato = eDB.getDescStato();
	}

	public StatoDocumento(EsenzioneDPraticaStato eDB) {
		this.codStato = eDB.getCodStato().toUpperCase();
		this.descStato = eDB.getDescStato().toUpperCase();
	}

	public String getCodStato() {
		return codStato;
	}



	public void setCodStato(String codStato) {
		this.codStato = codStato;
	}



	public String getDescStato() {
		return descStato;
	}



	public void setDescStato(String descStato) {
		this.descStato = descStato;
	}



	@Override
	public String toString() {
		return "StatoDocumento [ codStato=" + codStato + ", descStato="
				+ descStato + "]";
	}

}


