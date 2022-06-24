/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.dto;

public class NotificaResponse {
	
	private String codEsito;
	
	private String descEsito;

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDescEsito() {
		return descEsito;
	}

	public void setDescEsito(String descEsito) {
		this.descEsito = descEsito;
	}
}