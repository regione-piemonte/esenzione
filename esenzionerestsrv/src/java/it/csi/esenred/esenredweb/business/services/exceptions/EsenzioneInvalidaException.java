/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services.exceptions;

public class EsenzioneInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String codice;
    private String descrizione;

    public EsenzioneInvalidaException() {
    }

    public EsenzioneInvalidaException(String codice) {
        this.codice = codice;
    }

    public EsenzioneInvalidaException(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
