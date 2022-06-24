/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class ParametroDisclaimer {
	
//	protected String codice;
	protected String testo_informativa;
	protected String versione_informativa;
	protected String testo_privacy;
	protected String versione_privacy;
	
	public ParametroDisclaimer() {}
	
	
	
	public String getTesto_informativa() {
		return testo_informativa;
	}



	public void setTesto_informativa(String testo_informativa) {
		this.testo_informativa = testo_informativa;
	}



	public String getVersione_informativa() {
		return versione_informativa;
	}



	public void setVersione_informativa(String versione_informativa) {
		this.versione_informativa = versione_informativa;
	}



	public String getTesto_privacy() {
		return testo_privacy;
	}



	public void setTesto_privacy(String testo_privacy) {
		this.testo_privacy = testo_privacy;
	}



	public String getVersione_privacy() {
		return versione_privacy;
	}



	public void setVersione_privacy(String versione_privacy) {
		this.versione_privacy = versione_privacy;
	}




	@Override
	public String toString() {
		return "ParametroDisclaimer [testo_informativa=" + testo_informativa + ", versione_informativa=" + versione_informativa + ", testo_privacy=" + testo_privacy + ", versione_privacy=" + versione_privacy + "]";
	}
}