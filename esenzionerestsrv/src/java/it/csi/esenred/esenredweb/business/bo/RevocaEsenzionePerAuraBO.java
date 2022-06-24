/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class RevocaEsenzionePerAuraBO {
	private String codFiscale;
	
	private String codEsenzione;
	
	private String numProtocolloSogei;
	
	private String idUtente;
	
	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getNumProtocolloSogei() {
		return numProtocolloSogei;
	}

	public void setNumProtocolloSogei(String numProtocolloSogei) {
		this.numProtocolloSogei = numProtocolloSogei;
	}
}
