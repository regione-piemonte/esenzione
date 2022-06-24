/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "ESENZIONE_R_CA_TYPE_OTP" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_CA_TYPE_OTP\"")
@NamedQuery(name="EsenzioneRCaTypeOtp.findAll", query="SELECT e FROM EsenzioneRCaTypeOtp e")
public class EsenzioneRCaTypeOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRCaTypeOtpPK id;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	@Column(name="\"DAT_INIZIO_VALIDITA\"")
	private Timestamp datInizioValidita;

	//bi-directional many-to-one association to EsenzioneDCa
	@ManyToOne
    @JoinColumn(name = "\"SK_CA\"", insertable = false, updatable = false)
	private EsenzioneDCa esenzioneDCa;

	//bi-directional many-to-one association to EsenzioneDTypeOtp
	@ManyToOne
    @JoinColumn(name = "\"SK_TYPE_OTP\"", insertable = false, updatable = false)
	private EsenzioneDTypeOtp esenzioneDTypeOtp;

	public EsenzioneRCaTypeOtp() {
	}

	public EsenzioneRCaTypeOtpPK getId() {
		return this.id;
	}

	public void setId(EsenzioneRCaTypeOtpPK id) {
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

	public EsenzioneDCa getEsenzioneDCa() {
		return this.esenzioneDCa;
	}

	public void setEsenzioneDCa(EsenzioneDCa esenzioneDCa) {
		this.esenzioneDCa = esenzioneDCa;
	}

	public EsenzioneDTypeOtp getEsenzioneDTypeOtp() {
		return this.esenzioneDTypeOtp;
	}

	public void setEsenzioneDTypeOtp(EsenzioneDTypeOtp esenzioneDTypeOtp) {
		this.esenzioneDTypeOtp = esenzioneDTypeOtp;
	}

}