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
 * The primary key class for the "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" database table.
 * 
 */
@Embeddable
public class EsenzioneRPraticaEsenzioneDocumentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"SK_PRATICA_ESENZIONE\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skPraticaEsenzione;

	@Column(name="\"SK_DOCUMENTO\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"DAT_INIZIO_VALIDITA\"", unique=true, nullable=false)
	private java.util.Date datInizioValidita;

	public EsenzioneRPraticaEsenzioneDocumentoPK() {
	}
	public Long getSkPraticaEsenzione() {
		return this.skPraticaEsenzione;
	}
	public void setSkPraticaEsenzione(Long skPraticaEsenzione) {
		this.skPraticaEsenzione = skPraticaEsenzione;
	}
	public Long getSkDocumento() {
		return this.skDocumento;
	}
	public void setSkDocumento(Long skDocumento) {
		this.skDocumento = skDocumento;
	}
	public java.util.Date getDatInizioValidita() {
		return this.datInizioValidita;
	}
	public void setDatInizioValidita(java.util.Date datInizioValidita) {
		this.datInizioValidita = datInizioValidita;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EsenzioneRPraticaEsenzioneDocumentoPK)) {
			return false;
		}
		EsenzioneRPraticaEsenzioneDocumentoPK castOther = (EsenzioneRPraticaEsenzioneDocumentoPK)other;
		return 
			this.skPraticaEsenzione.equals(castOther.skPraticaEsenzione)
			&& this.skDocumento.equals(castOther.skDocumento)
			&& this.datInizioValidita.equals(castOther.datInizioValidita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.skPraticaEsenzione.hashCode();
		hash = hash * prime + this.skDocumento.hashCode();
		hash = hash * prime + this.datInizioValidita.hashCode();
		
		return hash;
	}
}