/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class ResponseAura {
	
	private String codAura;
	
	private String EsitoAura;
	
	private String messaggio;
	
	private Long idEsenzione;

	public Long getIdEsenzione() {
		return idEsenzione;
	}

	public void setIdEsenzione(Long idEsenzione) {
		this.idEsenzione = idEsenzione;
	}

	public String getCodAura() {
		return codAura;
	}

	public void setCodAura(String codAura) {
		this.codAura = codAura;
	}
	
	public String getEsitoAura() {
		return EsitoAura;
	}

	public void setEsitoAura(String EsitoAura) {
		this.EsitoAura = EsitoAura;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
}
