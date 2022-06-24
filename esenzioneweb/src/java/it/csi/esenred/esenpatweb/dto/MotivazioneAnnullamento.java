/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import java.util.HashMap;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"motivazione", "nota_beneficiario", "nota_servizio", "nota_interna"
})
public class MotivazioneAnnullamento {

  @JsonProperty("motivazione")
  private Motivazione motivazione;

	@JsonProperty("nota_beneficiario")
	private String notaBeneficiario;

	@JsonProperty("nota_servizio")
	private String notaServizio;

	@JsonProperty("nota_interna")
	private String notaInterna;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("motivazione")
  public Motivazione getMotivazione() {
    return motivazione;
  }

  @JsonProperty("motivazione")
  public void setMotivazione(Motivazione motivazione) {
    this.motivazione = motivazione;
  }

	public String getNotaBeneficiario() {
		return notaBeneficiario;
	}

	public void setNotaBeneficiario(String notaBeneficiario) {
		this.notaBeneficiario = notaBeneficiario;
	}

	public String getNotaServizio() {
		return notaServizio;
	}

	public void setNotaServizio(String notaServizio) {
		this.notaServizio = notaServizio;
	}

	public String getNotaInterna() {
		return notaInterna;
	}

	public void setNotaInterna(String notaInterna) {
		this.notaInterna = notaInterna;
	}

	@JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
  }
}
