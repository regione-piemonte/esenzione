/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "ESENZIONE_R_ERRORI_GATEWAY_FIRMA_ERRORI_ESENPAT" database table.
 * 
 */
@Embeddable
public class EsenzioneRErroriGatewayFirmaErroriEsenpatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"SK_ERRORI_GATEWAY_FIRMA\"", insertable=false, updatable=false)
	private Integer skErroriGatewayFirma;

	@Column(name="\"SK_ERRORI_ESENPAT\"", insertable=false, updatable=false)
	private Integer skErroriEsenpat;

	public EsenzioneRErroriGatewayFirmaErroriEsenpatPK() {
	}
	public Integer getSkErroriGatewayFirma() {
		return this.skErroriGatewayFirma;
	}
	public void setSkErroriGatewayFirma(Integer skErroriGatewayFirma) {
		this.skErroriGatewayFirma = skErroriGatewayFirma;
	}
	public Integer getSkErroriEsenpat() {
		return this.skErroriEsenpat;
	}
	public void setSkErroriEsenpat(Integer skErroriEsenpat) {
		this.skErroriEsenpat = skErroriEsenpat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EsenzioneRErroriGatewayFirmaErroriEsenpatPK)) {
			return false;
		}
		EsenzioneRErroriGatewayFirmaErroriEsenpatPK castOther = (EsenzioneRErroriGatewayFirmaErroriEsenpatPK)other;
		return 
			this.skErroriGatewayFirma.equals(castOther.skErroriGatewayFirma)
			&& this.skErroriEsenpat.equals(castOther.skErroriEsenpat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.skErroriGatewayFirma.hashCode();
		hash = hash * prime + this.skErroriEsenpat.hashCode();
		
		return hash;
	}
}