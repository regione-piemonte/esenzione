/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.aura.get.ArrayOfinfoesenzioneInfoEsenzioneNew;

public class CittadinoEsenpat extends Cittadino {
	private String codStatoNascita;
	private String telefonoResidenza;
	private String descStatoNascita;
	private ArrayOfinfoesenzioneInfoEsenzioneNew esenzioniAura;

	public CittadinoEsenpat() {
	}

	public ArrayOfinfoesenzioneInfoEsenzioneNew getEsenzioniAura() {
		return esenzioniAura;
	}

	public void setEsenzioniAura(ArrayOfinfoesenzioneInfoEsenzioneNew esenzioniAura) {
		this.esenzioniAura = esenzioniAura;
	}

	public String getCodStatoNascita() {
		return codStatoNascita;
	}

	public void setCodStatoNascita(String codStatoNascita) {
		this.codStatoNascita = codStatoNascita;
	}

	public String getTelefonoResidenza() {
		return telefonoResidenza;
	}

	public void setTelefonoResidenza(String telefonoResidenza) {
		this.telefonoResidenza = telefonoResidenza;
	}

	public String getDescStatoNascita() {
		return descStatoNascita;
	}

	public void setDescStatoNascita(String descStatoNascita) {
		this.descStatoNascita = descStatoNascita;
	}

}
