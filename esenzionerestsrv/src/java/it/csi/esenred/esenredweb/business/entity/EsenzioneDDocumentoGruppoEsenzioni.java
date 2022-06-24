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
 * The persistent class for the "ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI\"")
@NamedQuery(name="EsenzioneDDocumentoGruppoEsenzioni.findAll", query="SELECT e FROM EsenzioneDDocumentoGruppoEsenzioni e")
public class EsenzioneDDocumentoGruppoEsenzioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneDDocumentoGruppoEsenzioniPK id;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	//bi-directional many-to-one association to EsenzioneDDocumentoTipo
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPO_DOCUMENTO\"", insertable = false, updatable = false)
	private EsenzioneDDocumentoTipo esenzioneDDocumentoTipo;

	//bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
	@ManyToOne
	@JoinColumn(name = "\"SK_GRUPPO\"", insertable = false, updatable = false)
	private EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni;

	public EsenzioneDDocumentoGruppoEsenzioni() {
	}

	public EsenzioneDDocumentoGruppoEsenzioniPK getId() {
		return this.id;
	}

	public void setId(EsenzioneDDocumentoGruppoEsenzioniPK id) {
		this.id = id;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public EsenzioneDDocumentoTipo getEsenzioneDDocumentoTipo() {
		return this.esenzioneDDocumentoTipo;
	}

	public void setEsenzioneDDocumentoTipo(EsenzioneDDocumentoTipo esenzioneDDocumentoTipo) {
		this.esenzioneDDocumentoTipo = esenzioneDDocumentoTipo;
	}

	public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioni() {
		return this.esenzioneDGruppoEsenzioni;
	}

	public void setEsenzioneDGruppoEsenzioni(EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni) {
		this.esenzioneDGruppoEsenzioni = esenzioneDGruppoEsenzioni;
	}

}