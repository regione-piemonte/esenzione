/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;

@JsonPropertyOrder({ "codice_esenzione", "descrizione_esenzione", "tipologia_aura" })
public class EsenzioniResponse {
	@JsonProperty("codice")
	private String codiceEsenzione;
	@JsonProperty("descrizione")
	private String descrizioneEsenzione;
	@JsonProperty("tipologia_aura")
	private String tipologiaAura;

	public EsenzioniResponse() {
	}

	public EsenzioniResponse(EsenzioneDEsenzione ede) {
		this.codiceEsenzione = ede.getCodEsenzione();
		this.descrizioneEsenzione = ede.getDescEsenzione();
		if (ede.getEsenzioneDEsenzioneAuraTipo() != null) {
			this.tipologiaAura = ede.getEsenzioneDEsenzioneAuraTipo().getCodTipologiaEsenzioneAura();
		}
	}

	public String getTipologiaAura() {
		return tipologiaAura;
	}

	public void setTipologiaAura(String tipologiaAura) {
		this.tipologiaAura = tipologiaAura;
	}

	public String getCodiceEsenzione() {
		return codiceEsenzione;
	}

	public void setCodiceEsenzione(String codiceEsenzione) {
		this.codiceEsenzione = codiceEsenzione;
	}

	public String getDescrizioneEsenzione() {
		return descrizioneEsenzione;
	}

	public void setDescrizioneEsenzione(String descrizioneEsenzione) {
		this.descrizioneEsenzione = descrizioneEsenzione;
	}

}
