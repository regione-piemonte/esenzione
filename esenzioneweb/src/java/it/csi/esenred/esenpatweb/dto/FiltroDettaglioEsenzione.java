/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.*;

@JsonPropertyOrder({ "sk_esenzione", "cod_esenzione", "cf_beneficiario", "data_emissione" })

public class FiltroDettaglioEsenzione {

	@JsonProperty("sk_esenzione")
	private String skEsenzione;

	@JsonProperty("cod_esenzione")
	private String codEsenzione;

	@JsonProperty("cf_beneficiario")
	private String cfBeneficiario;

	@JsonProperty("data_emissione")
	private String dataEmissione;

	public String getSkEsenzione() {
		return skEsenzione;
	}

	public void setSkEsenzione(String skEsenzione) {
		this.skEsenzione = skEsenzione;
	}

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getCfBeneficiario() {
		return cfBeneficiario;
	}

	public void setCfBeneficiario(String cfBeneficiario) {
		this.cfBeneficiario = cfBeneficiario;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(String dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

}
