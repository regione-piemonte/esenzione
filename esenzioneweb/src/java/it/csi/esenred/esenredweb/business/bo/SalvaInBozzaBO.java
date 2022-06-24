/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class SalvaInBozzaBO {

	private String cit_id;

	private Long idEsenzione;

	private String notaInterna;
	private String notaBeneficiario;
	private String notaOperatore;
	private String note;

	public SalvaInBozzaBO() {
	}

	public String getCit_id() {
		return cit_id;
	}

	public void setCit_id(String cit_id) {
		this.cit_id = cit_id;
	}

	public Long getIdEsenzione() {
		return idEsenzione;
	}

	public void setIdEsenzione(Long idEsenzione) {
		this.idEsenzione = idEsenzione;
	}

	public String getNotaInterna() {
		return notaInterna;
	}

	public void setNotaInterna(String notaInterna) {
		this.notaInterna = notaInterna;
	}

	public String getNotaBeneficiario() {
		return notaBeneficiario;
	}

	public void setNotaBeneficiario(String notaBeneficiario) {
		this.notaBeneficiario = notaBeneficiario;
	}

	public String getNotaOperatore() {
		return notaOperatore;
	}

	public void setNotaOperatore(String notaOperatore) {
		this.notaOperatore = notaOperatore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
