/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class EsenzioneCreate {
	private String codice_esenzione;

	private String rapporto_familiare;

	private CreatoPer creato_per;
	
	protected Titolare titolare;

	private boolean disclaimer;

	public boolean isDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(boolean disclaimer) {
		this.disclaimer = disclaimer;
	}

	public EsenzioneCreate() {
	}

	public EsenzioneCreate(String codice_esenzione, String rapporto_familiare) {
		this.codice_esenzione = codice_esenzione;
		this.rapporto_familiare = rapporto_familiare;
		this.creato_per = new CreatoPer();
		this.titolare = new Titolare();
	}

	public String getCodice_esenzione() {
		return codice_esenzione;
	}

	public CreatoPer getCreato_per() {
		return creato_per;
	}

	public void setCreato_per(CreatoPer creato_per) {
		this.creato_per = creato_per;
	}
	
	public Titolare getTitolare() {
		return titolare;
	}

	public void setTitolare(Titolare titolare) {
		this.titolare = titolare;
	}

	public void setCodice_esenzione(String codice_esenzione) {
		this.codice_esenzione = codice_esenzione;
	}

	public String getRapporto_familiare() {
		return rapporto_familiare;
	}

	public void setRapporto_familiare(String rapporto_familiare) {
		this.rapporto_familiare = rapporto_familiare;
	}

	@Override
	public String toString() {
		return "EsenzioneCreate [codice_esenzione=" + codice_esenzione + ", rapporto_familiare=" + rapporto_familiare
				+ ", creato_per=" + creato_per.toString() + ", titolare=" + titolare.toString() + ", disclaimer=" + disclaimer + "]";
	}

}
