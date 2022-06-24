/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

public class Messaggio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codice;
	
	private String testo;

	public Messaggio(EsenredCMessaggi mDB) {
		this.codice = mDB.getCodice();
		this.testo = mDB.getTesto();
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
}