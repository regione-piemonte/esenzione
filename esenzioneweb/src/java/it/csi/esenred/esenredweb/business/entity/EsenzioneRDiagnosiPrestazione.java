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
 * The persistent class for the "ESENZIONE_R_DIAGNOSI_PRESTAZIONE" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_DIAGNOSI_PRESTAZIONE\"")
@NamedQuery(name="EsenzioneRDiagnosiPrestazione.findAll", query="SELECT e FROM EsenzioneRDiagnosiPrestazione e")
public class EsenzioneRDiagnosiPrestazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRDiagnosiPrestazionePK id;

	@Column(name="\"DAT_FINE\"")
	private Timestamp datFine;

	//bi-directional many-to-one association to EsenzioneDDiagnosi
	@ManyToOne
	@JoinColumn(name = "\"SK_DIAGNOSI\"", insertable = false, updatable = false)
	private EsenzioneDDiagnosi esenzioneDDiagnosi;

	//bi-directional many-to-one association to EsenzioneDPrestazioneSpecialistica
	@ManyToOne
	@JoinColumn(name = "\"SK_PRESTAZIONE\"", insertable = false, updatable = false)
	private EsenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica;

	public EsenzioneRDiagnosiPrestazione() {
	}

	public EsenzioneRDiagnosiPrestazionePK getId() {
		return this.id;
	}

	public void setId(EsenzioneRDiagnosiPrestazionePK id) {
		this.id = id;
	}

	public Timestamp getDatFine() {
		return this.datFine;
	}

	public void setDatFine(Timestamp datFine) {
		this.datFine = datFine;
	}

	public EsenzioneDDiagnosi getEsenzioneDDiagnosi() {
		return this.esenzioneDDiagnosi;
	}

	public void setEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		this.esenzioneDDiagnosi = esenzioneDDiagnosi;
	}

	public EsenzioneDPrestazioneSpecialistica getEsenzioneDPrestazioneSpecialistica() {
		return this.esenzioneDPrestazioneSpecialistica;
	}

	public void setEsenzioneDPrestazioneSpecialistica(EsenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica) {
		this.esenzioneDPrestazioneSpecialistica = esenzioneDPrestazioneSpecialistica;
	}

}