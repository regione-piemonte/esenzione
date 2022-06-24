/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;

public class Comune {
	
	private String descComune;
	
	private String siglaProvincia;

	public Comune(EsenredCComuni cDB) {
		this.descComune = cDB.getDenominazione();
		this.siglaProvincia = cDB.getCodProvincia();
		
	}

	public String getDescComune() {
		return descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}
}
