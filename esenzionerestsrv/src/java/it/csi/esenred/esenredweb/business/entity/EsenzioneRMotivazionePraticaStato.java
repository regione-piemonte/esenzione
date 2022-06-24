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
 * The persistent class for the "ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO\"")
@NamedQuery(name="EsenzioneRMotivazionePraticaStato.findAll", query="SELECT e FROM EsenzioneRMotivazionePraticaStato e")
public class EsenzioneRMotivazionePraticaStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRMotivazionePraticaStatoPK id;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	//bi-directional many-to-one association to EsenzioneDMotivazioneTipo
	@ManyToOne
	@JoinColumn(name = "\"SK_MOTIVAZIONE_TIPO\"", insertable = false, updatable = false)
	private EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo;

	//bi-directional many-to-one association to EsenzioneDPraticaStato
	@ManyToOne
	@JoinColumn(name = "\"SK_PRATICA_STATO\"", insertable = false, updatable = false)
	private EsenzioneDPraticaStato esenzioneDPraticaStato;

	public EsenzioneRMotivazionePraticaStato() {
	}

	public EsenzioneRMotivazionePraticaStatoPK getId() {
		return this.id;
	}

	public void setId(EsenzioneRMotivazionePraticaStatoPK id) {
		this.id = id;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public EsenzioneDMotivazioneTipo getEsenzioneDMotivazioneTipo() {
		return this.esenzioneDMotivazioneTipo;
	}

	public void setEsenzioneDMotivazioneTipo(EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo) {
		this.esenzioneDMotivazioneTipo = esenzioneDMotivazioneTipo;
	}

	public EsenzioneDPraticaStato getEsenzioneDPraticaStato() {
		return this.esenzioneDPraticaStato;
	}

	public void setEsenzioneDPraticaStato(EsenzioneDPraticaStato esenzioneDPraticaStato) {
		this.esenzioneDPraticaStato = esenzioneDPraticaStato;
	}

}