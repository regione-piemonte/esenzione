/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class ParametroConfigurazione {
	
//	protected String codice;
	
	protected String descrizione_titolare;
	
	public ParametroConfigurazione() {}
	
	
	
	public ParametroConfigurazione(String descrizione_titolare) {
//		this.codice = pDB.getCodice();
		this.descrizione_titolare = descrizione_titolare;
	}
	


//	public String getCodice() {
//		return codice;
//	}
//
//	public void setCodice(String codice) {
//		this.codice = codice;
//	}

	public String getDescrizione_titolare() {
		return descrizione_titolare;
	}

	public void setDescrizione_titolare(String descrizione_titolare) {
		this.descrizione_titolare = descrizione_titolare;
	}

	@Override
	public String toString() {
		return "ParametroConfigurazione [descrizione_titolare=" + descrizione_titolare + "]";
	}
}