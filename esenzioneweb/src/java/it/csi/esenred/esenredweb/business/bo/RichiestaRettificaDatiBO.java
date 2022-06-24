/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

import it.csi.esenred.esenpatweb.dto.Beneficiario;

public class RichiestaRettificaDatiBO {

	protected Beneficiario beneficiario;
	protected String skPraticaEsenzione;
	protected String skDocumento;

	protected String nota;
	protected String notainterna;
	protected String notabeneficiario;

	protected String codiceMotivazioneTipo;
	protected String richiestaRettificaDestinatario;

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getSkPraticaEsenzione() {
		return skPraticaEsenzione;
	}

	public void setSkPraticaEsenzione(String skPraticaEsenzione) {
		this.skPraticaEsenzione = skPraticaEsenzione;
	}

	public String getSkDocumento() {
		return skDocumento;
	}

	public void setSkDocumento(String skDocumento) {
		this.skDocumento = skDocumento;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNotainterna() {
		return notainterna;
	}

	public void setNotainterna(String notainterna) {
		this.notainterna = notainterna;
	}

	public String getNotabeneficiario() {
		return notabeneficiario;
	}

	public void setNotabeneficiario(String notabeneficiario) {
		this.notabeneficiario = notabeneficiario;
	}

	public String getCodiceMotivazioneTipo() {
		return codiceMotivazioneTipo;
	}

	public void setCodiceMotivazioneTipo(String codiceMotivazioneTipo) {
		this.codiceMotivazioneTipo = codiceMotivazioneTipo;
	}

	public String getRichiestaRettificaDestinatario() {
		return richiestaRettificaDestinatario;
	}

	public void setRichiestaRettificaDestinatario(String richiestaRettificaDestinatario) {
		this.richiestaRettificaDestinatario = richiestaRettificaDestinatario;
	}

}
