/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;

@JsonPropertyOrder({ "codice", "descrizione", })
public class StatiTipologieResponse {
	@JsonProperty("codice")
	private String codice;
	@JsonProperty("descrizione")
	private String descrizione;

	public StatiTipologieResponse() {
	}

	public StatiTipologieResponse(EsenzioneDPraticaStato stato) {
		this.codice = stato.getCodStato();
		this.descrizione = stato.getDescStato();
	}
	
	public StatiTipologieResponse(EsenzioneDGruppoEsenzioni tipo) {
		this.codice = tipo.getCodTipologiaGruppo();
		this.descrizione = tipo.getDescGruppo();
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
