/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;


public class FiltriValidaEsenzionePatologia  {

	protected Beneficiario beneficiario;
	
	protected String dataInizioValidita;

	protected String dataFineValidita;

	protected String codEsenzione;
	
	protected String skPraticaEsenzione;
	
	protected String nota;
	
	protected String notainterna;
	
	protected String notabeneficiario;
	
	protected String azione; 
	
	protected String codiceMotivazioneTipo;
	
	protected Documenti[] documentiallegati;
	
	
	public FiltriValidaEsenzionePatologia() {}
	

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	
	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getSkPraticaEsenzione() {
		return skPraticaEsenzione;
	}

	public void setSkPraticaEsenzione(String skPraticaEsenzione) {
		this.skPraticaEsenzione = skPraticaEsenzione;
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

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public String getCodiceMotivazioneTipo() {
		return codiceMotivazioneTipo;
	}

	public void setCodiceMotivazioneTipo(String codiceMotivazioneTipo) {
		this.codiceMotivazioneTipo = codiceMotivazioneTipo;
	}

	public Documenti[] getDocumentiallegati() {
		return documentiallegati;
	}

	public void setDocumentiallegati(Documenti[] documentiallegati) {
		this.documentiallegati = documentiallegati;
	}
	
}