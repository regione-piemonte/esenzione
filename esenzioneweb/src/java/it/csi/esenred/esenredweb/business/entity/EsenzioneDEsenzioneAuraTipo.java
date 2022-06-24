/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRGruppoEsenzioniEsenzioneAuraTipo;

/**
 * The persistent class for the "ESENZIONE_D_ESENZIONE_AURA_TIPO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_ESENZIONE_AURA_TIPO\"")
@NamedQuery(name = "EsenzioneDEsenzioneAuraTipo.findAll", query = "SELECT e FROM EsenzioneDEsenzioneAuraTipo e")
public class EsenzioneDEsenzioneAuraTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"")
	private Integer skTipologiaEsenzioneAura;

	@Column(name = "\"COD_TIPOLOGIA_ESENZIONE_AURA\"")
	private String codTipologiaEsenzioneAura;

	@Column(name = "\"DESC_TIPOLOGIA_ESENZIONE_AURA\"")
	private String descTipologiaEsenzioneAura;

	// bi-directional many-to-one association to EsenzioneDEsenzione
	@OneToMany(mappedBy = "esenzioneDEsenzioneAuraTipo")
	private List<EsenzioneDEsenzione> esenzioneDEsenziones;

	// bi-directional many-to-one association to
	// EsenzioneRGruppoEsenzioniEsenzioneAuraTipo
	@OneToMany(mappedBy = "esenzioneDEsenzioneAuraTipo")
	private List<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> esenzioneRGruppoEsenzioniEsenzioneAuraTipos;

	public EsenzioneDEsenzioneAuraTipo() {
	}

	public Integer getSkTipologiaEsenzioneAura() {
		return this.skTipologiaEsenzioneAura;
	}

	public void setSkTipologiaEsenzioneAura(Integer skTipologiaEsenzioneAura) {
		this.skTipologiaEsenzioneAura = skTipologiaEsenzioneAura;
	}

	public String getCodTipologiaEsenzioneAura() {
		return this.codTipologiaEsenzioneAura;
	}

	public void setCodTipologiaEsenzioneAura(String codTipologiaEsenzioneAura) {
		this.codTipologiaEsenzioneAura = codTipologiaEsenzioneAura;
	}

	public String getDescTipologiaEsenzioneAura() {
		return this.descTipologiaEsenzioneAura;
	}

	public void setDescTipologiaEsenzioneAura(String descTipologiaEsenzioneAura) {
		this.descTipologiaEsenzioneAura = descTipologiaEsenzioneAura;
	}

	public List<EsenzioneDEsenzione> getEsenzioneDEsenziones() {
		return this.esenzioneDEsenziones;
	}

	public void setEsenzioneDEsenziones(List<EsenzioneDEsenzione> esenzioneDEsenziones) {
		this.esenzioneDEsenziones = esenzioneDEsenziones;
	}

	public EsenzioneDEsenzione addEsenzioneDEsenzione(EsenzioneDEsenzione esenzioneDEsenzione) {
		getEsenzioneDEsenziones().add(esenzioneDEsenzione);
		esenzioneDEsenzione.setEsenzioneDEsenzioneAuraTipo(this);

		return esenzioneDEsenzione;
	}

	public EsenzioneDEsenzione removeEsenzioneDEsenzione(EsenzioneDEsenzione esenzioneDEsenzione) {
		getEsenzioneDEsenziones().remove(esenzioneDEsenzione);
		esenzioneDEsenzione.setEsenzioneDEsenzioneAuraTipo(null);

		return esenzioneDEsenzione;
	}

	public List<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos() {
		return this.esenzioneRGruppoEsenzioniEsenzioneAuraTipos;
	}

	public void setEsenzioneRGruppoEsenzioniEsenzioneAuraTipos(
			List<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> esenzioneRGruppoEsenzioniEsenzioneAuraTipos) {
		this.esenzioneRGruppoEsenzioniEsenzioneAuraTipos = esenzioneRGruppoEsenzioniEsenzioneAuraTipos;
	}

	public EsenzioneRGruppoEsenzioniEsenzioneAuraTipo addEsenzioneRGruppoEsenzioniEsenzioneAuraTipo(
			EsenzioneRGruppoEsenzioniEsenzioneAuraTipo esenzioneRGruppoEsenzioniEsenzioneAuraTipo) {
		getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos().add(esenzioneRGruppoEsenzioniEsenzioneAuraTipo);
		esenzioneRGruppoEsenzioniEsenzioneAuraTipo.setEsenzioneDEsenzioneAuraTipo(this);

		return esenzioneRGruppoEsenzioniEsenzioneAuraTipo;
	}

	public EsenzioneRGruppoEsenzioniEsenzioneAuraTipo removeEsenzioneRGruppoEsenzioniEsenzioneAuraTipo(
			EsenzioneRGruppoEsenzioniEsenzioneAuraTipo esenzioneRGruppoEsenzioniEsenzioneAuraTipo) {
		getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos().remove(esenzioneRGruppoEsenzioniEsenzioneAuraTipo);
		esenzioneRGruppoEsenzioniEsenzioneAuraTipo.setEsenzioneDEsenzioneAuraTipo(null);

		return esenzioneRGruppoEsenzioniEsenzioneAuraTipo;
	}

}