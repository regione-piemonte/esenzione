/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name="\"ESENRED_C_COMUNI\"")
@NamedQuery(name="EsenredCComuni.findAll", query="SELECT e FROM EsenredCComuni e")
public class EsenredCComuni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"COD_ISTAT\"")
	private String codIstat;

	@Column(name="\"COD_PROVINCIA\"")
	private String codProvincia;

	@Column(name="\"COD_REGIONE\"")
	private Integer codRegione;

	@Column(name="\"COD_SIGLA_PROVINCIA\"")
	private String codSiglaProvincia;

	@Column(name="\"CODICE_FISCALE\"")
	private String codiceFiscale;

	@Column(name="\"DENOMINAZIONE\"")
	private String denominazione;

	@Column(name="\"DENOMINAZIONE_PROVINCIA\"")
	private String denominazioneProvincia;

	@Column(name="\"DENOMINAZIONE_REGIONE\"")
	private String denominazioneRegione;

	public EsenredCComuni() {
	}

	public String getCodIstat() {
		return this.codIstat;
	}

	public void setCodIstat(String codIstat) {
		this.codIstat = codIstat;
	}

	public String getCodProvincia() {
		return this.codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public Integer getCodRegione() {
		return this.codRegione;
	}

	public void setCodRegione(Integer codRegione) {
		this.codRegione = codRegione;
	}

	public String getCodSiglaProvincia() {
		return this.codSiglaProvincia;
	}

	public void setCodSiglaProvincia(String codSiglaProvincia) {
		this.codSiglaProvincia = codSiglaProvincia;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDenominazioneProvincia() {
		return this.denominazioneProvincia;
	}

	public void setDenominazioneProvincia(String denominazioneProvincia) {
		this.denominazioneProvincia = denominazioneProvincia;
	}

	public String getDenominazioneRegione() {
		return this.denominazioneRegione;
	}

	public void setDenominazioneRegione(String denominazioneRegione) {
		this.denominazioneRegione = denominazioneRegione;
	}
}