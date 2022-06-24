/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

//import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.*;

public class NotificaPatoResponse {

	private String codiceRitorno;
	
  //	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String descrizione;

	public String getCodiceRitorno() {
		return codiceRitorno;
	}

	public void setCodiceRitorno(String codiceRitorno) {
		this.codiceRitorno = codiceRitorno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
