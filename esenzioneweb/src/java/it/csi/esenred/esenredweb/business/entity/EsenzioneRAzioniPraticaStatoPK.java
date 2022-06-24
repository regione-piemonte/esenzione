/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;


public class EsenzioneRAzioniPraticaStatoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


	private Integer skAzione;
	private Integer skPraticaStato;

	public EsenzioneRAzioniPraticaStatoPK() {

	}

	public Integer getSkAzione() {
		return skAzione;
	}

	public void setSkAzione(Integer skAzione) {
		this.skAzione = skAzione;
	}

	public Integer getSkPraticaStato() {
		return skPraticaStato;
	}

	public void setSkPraticaStato(Integer skPraticaStato) {
		this.skPraticaStato = skPraticaStato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((skAzione == null) ? 0 : skAzione.hashCode());
		result = prime * result + ((skPraticaStato == null) ? 0 : skPraticaStato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EsenzioneRAzioniPraticaStatoPK other = (EsenzioneRAzioniPraticaStatoPK) obj;
		if (skAzione == null) {
			if (other.skAzione != null)
				return false;
		} else if (!skAzione.equals(other.skAzione))
			return false;
		if (skPraticaStato == null) {
			if (other.skPraticaStato != null)
				return false;
		} else if (!skPraticaStato.equals(other.skPraticaStato))
			return false;
		return true;
	}

}
