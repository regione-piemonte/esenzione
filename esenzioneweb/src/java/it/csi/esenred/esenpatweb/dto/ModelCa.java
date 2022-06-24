/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.ArrayList;
import java.util.List;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDCa;

public class ModelCa {

	private Integer sk;
	private String codice;
	private String descrizione;
	private List<OtpType> tipiOtp;
	
	public ModelCa() {}
	
	public ModelCa(EsenzioneDCa ca) {
		this.sk = ca.getSkCa();
		this.codice = ca.getCodCa();
		this.descrizione = ca.getDescCa();
		this.tipiOtp = new ArrayList<OtpType>();
	}
	
	public ModelCa(EsenzioneDCa ca, List<OtpType> otpTypes) {
		this.sk = ca.getSkCa();
		this.codice = ca.getCodCa();
		this.descrizione = ca.getDescCa();
		this.tipiOtp = otpTypes;
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

	public List<OtpType> getTipiOtp() {
		return tipiOtp;
	}

	public void setTipiOtp(List<OtpType> tipiOtp) {
		this.tipiOtp = tipiOtp;
	}
	
	
}
