/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;


public class EsenzioneRAzioniGruppoUtentiPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer skGruppoUtenti;
	private Integer skAzione;

	public EsenzioneRAzioniGruppoUtentiPK() {

	}

	public Integer getSkGruppoUtenti() {
		return skGruppoUtenti;
	}

	public void setSkGruppoUtenti(Integer skGruppoUtenti) {
		this.skGruppoUtenti = skGruppoUtenti;
	}

	public Integer getSkAzione() {
		return skAzione;
	}

	public void setSkAzione(Integer skAzione) {
		this.skAzione = skAzione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((skAzione == null) ? 0 : skAzione.hashCode());
		result = prime * result + ((skGruppoUtenti == null) ? 0 : skGruppoUtenti.hashCode());
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
		EsenzioneRAzioniGruppoUtentiPK other = (EsenzioneRAzioniGruppoUtentiPK) obj;
		if (skAzione == null) {
			if (other.skAzione != null)
				return false;
		} else if (!skAzione.equals(other.skAzione))
			return false;
		if (skGruppoUtenti == null) {
			if (other.skGruppoUtenti != null)
				return false;
		} else if (!skGruppoUtenti.equals(other.skGruppoUtenti))
			return false;
		return true;
	}


}
