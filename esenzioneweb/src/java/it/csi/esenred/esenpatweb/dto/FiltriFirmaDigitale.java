/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "pkDocumento", "pin", "alias", "otp", "ca" })
public class FiltriFirmaDigitale {

	@JsonProperty("pkDocumento")
	private String pkDocumento;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("otp")
	private String otp;

	@JsonProperty("ca")
	private String ca;
	
	public String getPkDocumento() {
		return this.pkDocumento;
	}

	public void setPkDocumento(String pkDocumento) {
		this.pkDocumento = pkDocumento;
	}

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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

}