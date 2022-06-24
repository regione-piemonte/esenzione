/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;


public class EsenzionePatologiaElenco {
	private String gruppoEsenzione;
	private EsenzioneTipologia esenzionetipologia;
	

	public String getGruppoEsenzione() {
		return gruppoEsenzione;
	}


	public void setGruppoEsenzione(String gruppoEsenzione) {
		this.gruppoEsenzione = gruppoEsenzione;
	}


	public EsenzioneTipologia getEsenzionetipologia() {
		return esenzionetipologia;
	}


	public void setEsenzionetipologia(EsenzioneTipologia esenzionetipologia) {
		this.esenzionetipologia = esenzionetipologia;
	}




}


