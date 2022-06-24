/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class EsenzioneStorico {
	
	private String Protocollo;
	private String CodEsenzione;
	private String data_inizio_validita;
	private String Path;
	private String TipoEsenzione;
	public String getProtocollo() {
		return Protocollo;
	}
	public void setProtocollo(String protocollo) {
		Protocollo = protocollo;
	}
	public String getCodEsenzione() {
		return CodEsenzione;
	}
	public void setCodEsenzione(String codEsenzione) {
		CodEsenzione = codEsenzione;
	}
	public String getData_inizio_validita() {
		return data_inizio_validita;
	}
	public void setData_inizio_validita(String data_inizio_validita) {
		this.data_inizio_validita = data_inizio_validita;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getTipoEsenzione() {
		return TipoEsenzione;
	}
	public void setTipoEsenzione(String tipoEsenzione) {
		TipoEsenzione = tipoEsenzione;
	}
	
}
