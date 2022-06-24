/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "ESENZIONE_R_ERRORI_GATEWAY_FIRMA_ERRORI_ESENPAT" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_ERRORI_GATEWAY_FIRMA_ERRORI_ESENPAT\"")
@NamedQuery(name="EsenzioneRErroriGatewayFirmaErroriEsenpat.findAll", query="SELECT e FROM EsenzioneRErroriGatewayFirmaErroriEsenpat e")
public class EsenzioneRErroriGatewayFirmaErroriEsenpat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRErroriGatewayFirmaErroriEsenpatPK id;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	@Column(name="\"DAT_INIZIO_VALIDITA\"")
	private Timestamp datInizioValidita;

	//bi-directional many-to-one association to EsenzioneDErroriEsenpat
	@ManyToOne
    @JoinColumn(name = "\"SK_ERRORI_ESENPAT\"", insertable = false, updatable = false)
	private EsenzioneDErroriEsenpat esenzioneDErroriEsenpat;

	//bi-directional many-to-one association to EsenzioneDErroriGatewayFirma
	@ManyToOne
    @JoinColumn(name = "\"SK_ERRORI_GATEWAY_FIRMA\"", insertable = false, updatable = false)
	private EsenzioneDErroriGatewayFirma esenzioneDErroriGatewayFirma;

	public EsenzioneRErroriGatewayFirmaErroriEsenpat() {
	}

	public EsenzioneRErroriGatewayFirmaErroriEsenpatPK getId() {
		return this.id;
	}

	public void setId(EsenzioneRErroriGatewayFirmaErroriEsenpatPK id) {
		this.id = id;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public Timestamp getDatInizioValidita() {
		return this.datInizioValidita;
	}

	public void setDatInizioValidita(Timestamp datInizioValidita) {
		this.datInizioValidita = datInizioValidita;
	}

	public EsenzioneDErroriEsenpat getEsenzioneDErroriEsenpat() {
		return this.esenzioneDErroriEsenpat;
	}

	public void setEsenzioneDErroriEsenpat(EsenzioneDErroriEsenpat esenzioneDErroriEsenpat) {
		this.esenzioneDErroriEsenpat = esenzioneDErroriEsenpat;
	}

	public EsenzioneDErroriGatewayFirma getEsenzioneDErroriGatewayFirma() {
		return this.esenzioneDErroriGatewayFirma;
	}

	public void setEsenzioneDErroriGatewayFirma(EsenzioneDErroriGatewayFirma esenzioneDErroriGatewayFirma) {
		this.esenzioneDErroriGatewayFirma = esenzioneDErroriGatewayFirma;
	}

}