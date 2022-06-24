/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenpatweb.dto.Filtri;

public class FiltriRicercaEsenzioniAcceleratore {

	private Filtri Stato;
	private Filtri codice_esenzione;
	private Filtri rapporto_familiare;
	private Filtri data_richiesta;
	private Filtri data_inizio_validita;
	private Filtri data_scadenza;
	private Filtri data_revoca;
	private Filtri creato_da;
	private Filtri creato_per;
	private Filtri storico;
	private Filtri titolare;
	
	public Filtri getStato() {
		return Stato;
	}
	public void setStato(Filtri stato) {
		Stato = stato;
	}
	public Filtri getCodice_esenzione() {
		return codice_esenzione;
	}
	public void setCodice_esenzione(Filtri codice_esenzione) {
		this.codice_esenzione = codice_esenzione;
	}
	public Filtri getRapporto_familiare() {
		return rapporto_familiare;
	}
	public void setRapporto_familiare(Filtri rapporto_familiare) {
		this.rapporto_familiare = rapporto_familiare;
	}
	public Filtri getData_richiesta() {
		return data_richiesta;
	}
	public void setData_richiesta(Filtri data_richiesta) {
		this.data_richiesta = data_richiesta;
	}
	public Filtri getData_inizio_validita() {
		return data_inizio_validita;
	}
	public void setData_inizio_validita(Filtri data_inizio_validita) {
		this.data_inizio_validita = data_inizio_validita;
	}
	public Filtri getData_scadenza() {
		return data_scadenza;
	}
	public void setData_scadenza(Filtri data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
	public Filtri getData_revoca() {
		return data_revoca;
	}
	public void setData_revoca(Filtri data_revoca) {
		this.data_revoca = data_revoca;
	}
	public Filtri getCreato_da() {
		return creato_da;
	}
	public void setCreato_da(Filtri creato_da) {
		this.creato_da = creato_da;
	}
	public Filtri getCreato_per() {
		return creato_per;
	}
	public void setCreato_per(Filtri creato_per) {
		this.creato_per = creato_per;
	}
	public Filtri getStorico() {
		return storico;
	}
	public void setStorico(Filtri storico) {
		this.storico = storico;
	}
	public Filtri getTitolare() {
		return titolare;
	}
	public void setTitolare(Filtri titolare) {
		this.titolare = titolare;
	}
	
	
	
}
