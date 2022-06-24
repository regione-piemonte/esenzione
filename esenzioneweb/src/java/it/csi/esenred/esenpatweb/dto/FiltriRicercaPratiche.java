/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.List;

import org.codehaus.jackson.annotate.*;

@JsonPropertyOrder({ "stati_pratica", "cod_esenzione", "valida_dal", "valida_al", "cf_beneficiario",
		"cognome_beneficiario", "nome_beneficiario", "id_aura", "tab", "pagina" })

public class FiltriRicercaPratiche {

	@JsonProperty("stati_pratica")
	private List<StatoDocumento> statiPratica;

	@JsonProperty("cod_esenzione")
	private String codEsenzione;

	@JsonProperty("valida_dal")
	private String validaDal;

	@JsonProperty("valida_al")
	private String validaAl;

	@JsonProperty("cf_beneficiario")
	private String cfBeneficiario;

	@JsonProperty("cognome_beneficiario")
	private String cognomeBeneficiario;

	@JsonProperty("nome_beneficiario")
	private String nomeBeneficiario;

	@JsonProperty("id_aura")
	private String idAura;

	@JsonProperty("tab")
	private String tab;

	@JsonProperty("pagina")
	private int pagina;

	public List<StatoDocumento> getStatiPratica() {
		return statiPratica;
	}

	public void setStatiPratica(List<StatoDocumento> statiPratica) {
		this.statiPratica = statiPratica;
	}

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getValidaDal() {
		return validaDal;
	}

	public void setValidaDal(String validaDal) {
		this.validaDal = validaDal;
	}

	public String getValidaAl() {
		return validaAl;
	}

	public void setValidaAl(String validaAl) {
		this.validaAl = validaAl;
	}

	public String getCfBeneficiario() {
		return cfBeneficiario;
	}

	public void setCfBeneficiario(String cfBeneficiario) {
		this.cfBeneficiario = cfBeneficiario;
	}

	public String getCognomeBeneficiario() {
		return cognomeBeneficiario;
	}

	public void setCognomeBeneficiario(String cognomeBeneficiario) {
		this.cognomeBeneficiario = cognomeBeneficiario;
	}

	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

}
