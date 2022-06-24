/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class RinnovaEsenzioneBO {

	private String cit_id;
	private String skPraticaEsenzione;

	public RinnovaEsenzioneBO() {
	}

	public String getCit_id() {
		return cit_id;
	}

	public void setCit_id(String cit_id) {
		this.cit_id = cit_id;
	}

	public String getSkPraticaEsenzione() {
		return skPraticaEsenzione;
	}

	public void setSkPraticaEsenzione(String skPraticaEsenzione) {
		this.skPraticaEsenzione = skPraticaEsenzione;
	}

}
