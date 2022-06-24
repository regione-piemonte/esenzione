/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;

public class Parametro {
	
	private String codParametro;
	
	private String valoreParametro;

	public Parametro(EsenredCParametri pDB) {
		this.codParametro = pDB.getCodice();
		this.valoreParametro = pDB.getValore();
	}

	public String getCodParametro() {
		return codParametro;
	}

	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}

	public String getValoreParametro() {
		return valoreParametro;
	}

	public void setValoreParametro(String valoreParametro) {
		this.valoreParametro = valoreParametro;
	}
}