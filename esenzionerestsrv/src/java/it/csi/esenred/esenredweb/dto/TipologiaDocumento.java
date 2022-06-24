/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;

@JsonPropertyOrder({ "codice", "descrizione", "descrizione_estesa" })
public class TipologiaDocumento {

	@JsonProperty("codice")
	private String codice;

	@JsonProperty("descrizione")
	private String descrizione;

	@JsonProperty("descrizione_estesa")
	private String descrizioneEstesa;

	public TipologiaDocumento() {

	}

	public TipologiaDocumento(EsenzioneDDocumentoTipo tipo) {
		this.codice = tipo.getCodDocumentoTipo();
		this.descrizione = tipo.getDescDocumentoTipo();
		this.descrizioneEstesa = null;
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

	public String getDescrizioneEstesa() {
		return descrizioneEstesa;
	}

	public void setDescrizioneEstesa(String descrizioneEstesa) {
		this.descrizioneEstesa = descrizioneEstesa;
	}

}
