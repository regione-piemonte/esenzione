/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.*;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

@JsonPropertyOrder({ "nome", "cognome", "codFiscale", "codSesso", "dataNascita", "comuneNascita", "codStatoNascita",
		"idAura", "codAsl" })
public class Assistito {

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("cognome")
	private String cognome;

	@JsonProperty("codFiscale")
	private String codFiscale;

	@JsonProperty("codSesso")
	private String codSesso;

	@JsonProperty("dataNascita")
	private String dataNascita;

	@JsonProperty("comuneNascita")
	private String comuneNascita;

	@JsonProperty("codStatoNascita")
	private String codStatoNascita;

	@JsonProperty("idAura")
	private String idAura;
	
	@JsonProperty("stato")
	private String stato;
	
	@JsonProperty("datascadenzaSSN")
  private String datascadenzaSSN;
	
	 @JsonProperty("provinciaNascita")
	  private String provinciaNascita;

	@JsonProperty("codAsl")
	private String codAsl;
	  
	
	public Assistito() {}

	public Assistito(CittadinoEsenpat cit) {
		this.nome = cit.getNome();
		this.cognome = cit.getCognome();
		this.codFiscale = cit.getCodFiscale();
		this.codSesso = cit.getCodSesso();
		this.dataNascita = cit.getDataNascita();
		this.comuneNascita = cit.getComuneNascita();
		this.codStatoNascita = cit.getCodStatoNascita();
		this.idAura = cit.getIdAura();
		this.codAsl = cit.getCodASL();
	}

	public Assistito(EsenzioneTCittadino cit) {
		this.nome = cit.getNome();
		this.cognome = cit.getCognome();
		this.codFiscale = cit.getCodiceFiscale();
		this.codSesso = cit.getSesso();
		this.dataNascita = Converter.getStringDataFromTimeStamp(cit.getDataDiNascita(), Constants.DATE_FORMAT_ITALIAN);
		this.comuneNascita = cit.getComuneDiNascita();
		this.codStatoNascita = null;
		this.idAura = cit.getId_aura();
		this.codAsl = cit.getIdAzienda();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codiceFiscale) {
		this.codFiscale = codiceFiscale;
	}

	public String getCodSesso() {
		return codSesso;
	}

	public void setCodSesso(String codSesso) {
		this.codSesso = codSesso;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getCodStatoNascita() {
		return codStatoNascita;
	}

	public void setCodStatoNascita(String codStatoNascita) {
		this.codStatoNascita = codStatoNascita;
	}

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

  public String getStato() {
    return stato;
  }

  public void setStato(String stato) {
    this.stato = stato;
  }

  public String getDatascadenzaSSN() {
    return datascadenzaSSN;
  }

  public void setDatascadenzaSSN(String datascadenzaSSN) {
    this.datascadenzaSSN = datascadenzaSSN;
  }

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getCodAsl() {
		return codAsl;
	}

	public void setCodAsl(String codAsl) {
		this.codAsl = codAsl;
	}
	
}
