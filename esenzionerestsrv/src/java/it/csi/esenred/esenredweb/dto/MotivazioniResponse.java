/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;

@JsonPropertyOrder({ "codice", "descrizione", })
public class MotivazioniResponse {
	@JsonProperty("codice")
	private String codice;
	@JsonProperty("descrizione")
	private String descrizione;

	public MotivazioniResponse() {
	}

	public MotivazioniResponse(EsenzioneDMotivazioneTipo motivazione) {
		this.codice = motivazione.getCodMotivazione();
		this.descrizione = motivazione.getDescMotivazione();
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
