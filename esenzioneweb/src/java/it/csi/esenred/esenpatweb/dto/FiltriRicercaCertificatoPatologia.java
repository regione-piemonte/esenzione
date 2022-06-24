/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltriRicercaCertificatoPatologia  {


	protected String idAuraBeneficiario;
	
	protected String nomeBeneficiario;
	
	protected String cognomeBeneficiario;
	
	protected String codFiscaleBeneficiario;
	
	protected String codStatoCertificato;
	
	protected String dataInizioValidita;

	protected String dataFineValidita;

	protected String codCertificato;
	
	private int pagina;
	
	private String orderBy = "";
	
	private boolean asc = true;
	
	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	
		public String getIdAuraBeneficiario() {
		return idAuraBeneficiario;
	}

	public void setIdAuraBeneficiario(String idAuraBeneficiario) {
		this.idAuraBeneficiario = idAuraBeneficiario;
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

	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public String getCognomeBeneficiario() {
		return cognomeBeneficiario;
	}

	public void setCognomeBeneficiario(String cognomeBeneficiario) {
		this.cognomeBeneficiario = cognomeBeneficiario;
	}

	public String getCodFiscaleBeneficiario() {
		return codFiscaleBeneficiario;
	}

	public void setCodFiscaleBeneficiario(String codFiscaleBeneficiario) {
		this.codFiscaleBeneficiario = codFiscaleBeneficiario;
	}

	public String getCodStatoCertificato() {
		return codStatoCertificato;
	}

	public void setCodStatoCertificato(String codStatoCertificato) {
		this.codStatoCertificato = codStatoCertificato;
	}

	public String getCodCertificato() {
		return codCertificato;
	}

	public void setCodCertificato(String codCertificato) {
		this.codCertificato = codCertificato;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	
}