/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.dto;

public class NotificaRequest {
	
	private String idAura;
	
	private String codEsenzione;
	
	private String dataInizio;
	
	private String tipoNotifica;
	
	private String numProtSogei;
	
	private String esito;
	
	private String codEsitoMEF;
	
	private String descEsitoMEF;
	
	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getCodEsitoMEF() {
		return codEsitoMEF;
	}

	public void setCodEsitoMEF(String codEsitoMEF) {
		this.codEsitoMEF = codEsitoMEF;
	}

	public String getDescEsitoMEF() {
		return descEsitoMEF;
	}

	public void setDescEsitoMEF(String descEsitoMEF) {
		this.descEsitoMEF = descEsitoMEF;
	}

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getTipoNotifica() {
		return tipoNotifica;
	}

	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}

	public String getNumProtSogei() {
		return numProtSogei;
	}

	public void setNumProtSogei(String numProtSogei) {
		this.numProtSogei = numProtSogei;
	}
}
