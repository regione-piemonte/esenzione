/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class FiltriEsenzioneAuraDettaglio {

	private Filtri data_emissione;
	private Filtri data_scadenza;

	public Filtri getData_emissione() {
		return data_emissione;
	}

	public void setData_emissione(Filtri data_emissione) {
		this.data_emissione = data_emissione;
	}

	public Filtri getData_scadenza() {
		return data_scadenza;
	}

	public void setData_scadenza(Filtri data_scadenza) {
		this.data_scadenza = data_scadenza;
	}

}
