/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDInvaliditaTipo;

@JsonPropertyOrder({ "codice", "descrizione" })
public class TipologiaInvalidita {

	@JsonProperty("codice")
	private String codice;

	@JsonProperty("descrizione")
	private String descrizione;

	public TipologiaInvalidita() {

	}

	public TipologiaInvalidita(EsenzioneDInvaliditaTipo inv) {
		this.codice = inv.getCodInvaliditaTipo();
		this.descrizione = inv.getDescInvaliditaTipo();
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
