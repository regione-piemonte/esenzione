/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the "ESENZIONE_R_DIAGNOSI_PRESTAZIONE" database table.
 * 
 */
@Embeddable
public class EsenzioneRDiagnosiPrestazionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"DAT_INIZIO\"", unique=true, nullable=false)
	private java.util.Date datInizio;

	@Column(name="\"SK_DIAGNOSI\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skDiagnosi;

	@Column(name="\"SK_PRESTAZIONE\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skPrestazione;

	public EsenzioneRDiagnosiPrestazionePK() {
	}
	public java.util.Date getDatInizio() {
		return this.datInizio;
	}
	public void setDatInizio(java.util.Date datInizio) {
		this.datInizio = datInizio;
	}
	public Long getSkDiagnosi() {
		return this.skDiagnosi;
	}
	public void setSkDiagnosi(Long skDiagnosi) {
		this.skDiagnosi = skDiagnosi;
	}
	public Long getSkPrestazione() {
		return this.skPrestazione;
	}
	public void setSkPrestazione(Long skPrestazione) {
		this.skPrestazione = skPrestazione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EsenzioneRDiagnosiPrestazionePK)) {
			return false;
		}
		EsenzioneRDiagnosiPrestazionePK castOther = (EsenzioneRDiagnosiPrestazionePK)other;
		return 
			this.datInizio.equals(castOther.datInizio)
			&& this.skDiagnosi.equals(castOther.skDiagnosi)
			&& this.skPrestazione.equals(castOther.skPrestazione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.datInizio.hashCode();
		hash = hash * prime + this.skDiagnosi.hashCode();
		hash = hash * prime + this.skPrestazione.hashCode();
		
		return hash;
	}
}