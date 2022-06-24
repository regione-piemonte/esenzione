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
 * The primary key class for the "ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" database table.
 * 
 */
@Embeddable
public class EsenzioneDDocumentoGruppoEsenzioniPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"SK_GRUPPO\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skGruppo;

	@Column(name="\"SK_TIPO_DOCUMENTO\"", insertable=false, updatable=false, unique=true, nullable=false)
	private Long skTipoDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"DAT_INIZIO_VALIDITA\"", unique=true, nullable=false)
	private java.util.Date datInizioValidita;

	public EsenzioneDDocumentoGruppoEsenzioniPK() {
	}
	public Long getSkGruppo() {
		return this.skGruppo;
	}
	public void setSkGruppo(Long skGruppo) {
		this.skGruppo = skGruppo;
	}
	public Long getSkTipoDocumento() {
		return this.skTipoDocumento;
	}
	public void setSkTipoDocumento(Long skTipoDocumento) {
		this.skTipoDocumento = skTipoDocumento;
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
		if (!(other instanceof EsenzioneDDocumentoGruppoEsenzioniPK)) {
			return false;
		}
		EsenzioneDDocumentoGruppoEsenzioniPK castOther = (EsenzioneDDocumentoGruppoEsenzioniPK)other;
		return 
			this.skGruppo.equals(castOther.skGruppo)
			&& this.skTipoDocumento.equals(castOther.skTipoDocumento)
			&& this.datInizioValidita.equals(castOther.datInizioValidita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.skGruppo.hashCode();
		hash = hash * prime + this.skTipoDocumento.hashCode();
		hash = hash * prime + this.datInizioValidita.hashCode();
		
		return hash;
	}
}