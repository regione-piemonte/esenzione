/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class FiltriRicercaEsenzioniEsenred {
	
	protected String idAuraDichiarante;
	
	protected String idAuraOperatore;

	protected String codFiscaleDichiarante;
	
	protected String codFiscaleTitolare;

	protected String idAuraBeneficiario;
	
	protected String codStatoEsenzione;
	
	protected String dataInizioValidita;

	protected String dataFineValidita;

	protected String codEsenzione;
	
	protected String idAuraTitolare;
	
	protected String storico;
	
	protected String protocollo;
	
	protected String aslOperatore;
	
	public String getAslOperatore() {
		return aslOperatore;
	}

	public void setAslOperatore(String aslOperatore) {
		this.aslOperatore = aslOperatore;
	}
	
	public String getStorico() {
		return storico;
	}

	public void setStorico(String storico) {
		this.storico = storico;
	}

	public String getIdAuraBeneficiario() {
		return idAuraBeneficiario;
	}

	public void setIdAuraBeneficiario(String idAuraBeneficiario) {
		this.idAuraBeneficiario = idAuraBeneficiario;
	}
	
	public String getIdAuraTitolare() {
		return idAuraTitolare;
	}

	public void setIdAuraTitolare(String idAuraTitolare) {
		this.idAuraTitolare = idAuraTitolare;
	}

	public String getIdAuraOperatore() {
		return idAuraOperatore;
	}

	public void setIdAuraOperatore(String idAuraOperatore) {
		this.idAuraOperatore = idAuraOperatore;
	}

	public String getCodFiscaleDichiarante() {
		return codFiscaleDichiarante;
	}

	public void setCodFiscaleDichiarante(String codFiscaleDichiarante) {
		this.codFiscaleDichiarante = codFiscaleDichiarante;
	}

	public String getIdAuraDichiarante() {
		return idAuraDichiarante;
	}

	public void setIdAuraDichiarante(String idAuraDichiarante) {
		this.idAuraDichiarante = idAuraDichiarante;
	}

	public String getCodStatoEsenzione() {
		return codStatoEsenzione;
	}

	public void setCodStatoEsenzione(String codStatoEsenzione) {
		this.codStatoEsenzione = codStatoEsenzione;
	}

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

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public String getCodFiscaleTitolare() {
		return codFiscaleTitolare;
	}

	public void setCodFiscaleTitolare(String codFiscaleTitolare) {
		this.codFiscaleTitolare = codFiscaleTitolare;
	}
}
