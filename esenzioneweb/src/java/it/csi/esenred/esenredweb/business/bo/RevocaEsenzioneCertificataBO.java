/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class RevocaEsenzioneCertificataBO {
	
	private String codiceFiscaleChiamante; 
	
	private String codiceEsenzione;
	
	private String dataInizioValidita;
	
	private String codiceFiscaleAssistito;
	
	private String motivoRevocaEsenzione;
	
	private String notaInternaRevoca;
	
	public String getNotaInternaRevoca() {
		return notaInternaRevoca;
	}

	public void setNotaInternaRevoca(String notaInternaRevoca) {
		this.notaInternaRevoca = notaInternaRevoca;
	}
	
	public String getMotivoRevocaEsenzione() {
		return motivoRevocaEsenzione;
	}

	public void setMotivoRevocaEsenzione(String motivoRevocaEsenzione) {
		this.motivoRevocaEsenzione = motivoRevocaEsenzione;
	}

	public String getCodiceFiscaleChiamante() {
		return codiceFiscaleChiamante;
	}

	public void setCodiceFiscaleChiamante(String codiceFiscaleChiamante) {
		this.codiceFiscaleChiamante = codiceFiscaleChiamante;
	}

	public String getCodiceEsenzione() {
		return codiceEsenzione;
	}

	public void setCodiceEsenzione(String codiceEsenzione) {
		this.codiceEsenzione = codiceEsenzione;
	}

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getCodiceFiscaleAssistito() {
		return codiceFiscaleAssistito;
	}

	public void setCodiceFiscaleAssistito(String codiceFiscaleAssistito) {
		this.codiceFiscaleAssistito = codiceFiscaleAssistito;
	}
}
