/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredTAslOperatore;

public class AslOperarore {
	
	private String codAsl;
	
	private String codFiscaleOperatore;

	public AslOperarore(EsenredTAslOperatore pDB) {
		this.codAsl = pDB.getCodAsl();
		this.codFiscaleOperatore = pDB.getCodFiscaleOperatore();
	}

	public String getCodAsl() {
		return codAsl;
	}

	public void setCodAsl(String codAsl) {
		this.codAsl = codAsl;
	}

	public String getCodFiscaleOperatore() {
		return codFiscaleOperatore;
	}

	public void setCodFiscaleOperatore(String codFiscaleOperatore) {
		this.codFiscaleOperatore = codFiscaleOperatore;
	}

	
}
