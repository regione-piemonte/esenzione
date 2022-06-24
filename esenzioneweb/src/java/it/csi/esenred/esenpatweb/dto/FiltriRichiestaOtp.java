/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "ca", "pin", "alias", "typeOtp" })
public class FiltriRichiestaOtp {

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("ca")
	private String ca;
	
	@JsonProperty("typeOtp")
	private String typeOtp;

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getTypeOtp() {
		return typeOtp;
	}

	public void setTypeOtp(String typeOtp) {
		this.typeOtp = typeOtp;
	}

}