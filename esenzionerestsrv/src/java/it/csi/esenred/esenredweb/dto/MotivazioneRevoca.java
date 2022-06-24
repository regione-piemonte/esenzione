/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class MotivazioneRevoca {
	private String motivazione_revoca;

	public MotivazioneRevoca() {
	}

	public String getMotivazione_revoca() {
		return motivazione_revoca;
	}

	public void setMotivazione_revoca(String motivazione_revoca) {
		this.motivazione_revoca = motivazione_revoca;
	}

	@Override
	public String toString() {
		return "MotivazioneRevoca [motivazione_revoca=" + motivazione_revoca + "]";
	}

}
