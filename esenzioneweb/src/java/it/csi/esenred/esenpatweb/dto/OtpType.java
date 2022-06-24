/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDTypeOtp;

public class OtpType {
	
	private Integer sk;
	private String codice;
	private String descrizione;
	
	public OtpType() {}
	
	public OtpType(EsenzioneDTypeOtp otp) {
		this.sk = otp.getSkTypeOtp();
		this.codice = otp.getCodTypeOtp();
		this.descrizione = otp.getDescTypeOtp();
	}

	public Integer getSk() {
		return sk;
	}

	public void setSk(Integer sk) {
		this.sk = sk;
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
