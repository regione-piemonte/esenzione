/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO\"")
@NamedQuery(name="EsenzioneRGruppoEsenzioniEsenzioneAuraTipo.findAll", query="SELECT e FROM EsenzioneRGruppoEsenzioniEsenzioneAuraTipo e")
public class EsenzioneRGruppoEsenzioniEsenzioneAuraTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK id;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	//bi-directional many-to-one association to EsenzioneDEsenzioneAuraTipo
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", insertable = false, updatable = false)
	private EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo;

	//bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
	@ManyToOne
	@JoinColumn(name = "\"SK_GRUPPO\"", insertable = false, updatable = false)
	private EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni;

	public EsenzioneRGruppoEsenzioniEsenzioneAuraTipo() {
	}

	public EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK getId() {
		return this.id;
	}

	public void setId(EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK id) {
		this.id = id;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public EsenzioneDEsenzioneAuraTipo getEsenzioneDEsenzioneAuraTipo() {
		return this.esenzioneDEsenzioneAuraTipo;
	}

	public void setEsenzioneDEsenzioneAuraTipo(EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo) {
		this.esenzioneDEsenzioneAuraTipo = esenzioneDEsenzioneAuraTipo;
	}

	public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioni() {
		return this.esenzioneDGruppoEsenzioni;
	}

	public void setEsenzioneDGruppoEsenzioni(EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni) {
		this.esenzioneDGruppoEsenzioni = esenzioneDGruppoEsenzioni;
	}

}