/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class DataInizioFine {
	
	private String dataRichiesta;

    private String inizioValidita;

    private String fineValidita;

	public String getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public String getInizioValidita() {
		return inizioValidita;
	}

	public void setInizioValidita(String inizioValidita) {
		this.inizioValidita = inizioValidita;
	}

	public String getFineValidita() {
		return fineValidita;
	}

	public void setFineValidita(String fineValidita) {
		this.fineValidita = fineValidita;
	}
}
