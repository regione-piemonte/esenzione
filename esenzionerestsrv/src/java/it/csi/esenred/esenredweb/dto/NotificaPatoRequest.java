/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class NotificaPatoRequest {
	
	private String idAura;
	
	private String codiceEsenzione;
	
	private String dataInizio;
	
	private String dataFine;
	
	private String tipoNotifica;
	
	private String codiceAttestatoEsenzione;

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCodiceEsenzione() {
		return codiceEsenzione;
	}

	public void setCodiceEsenzione(String codiceEsenzione) {
		this.codiceEsenzione = codiceEsenzione;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public String getTipoNotifica() {
		return tipoNotifica;
	}

	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}

	public String getCodiceAttestatoEsenzione() {
		return codiceAttestatoEsenzione;
	}

	public void setCodiceAttestatoEsenzione(String codiceAttestatoEsenzione) {
		this.codiceAttestatoEsenzione = codiceAttestatoEsenzione;
	}
}