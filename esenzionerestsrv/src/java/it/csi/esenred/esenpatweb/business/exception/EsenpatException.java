/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.exception;

public class EsenpatException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String codice;
	private String codMessaggio;
	private int status;
	private String descrizione;
	
	public EsenpatException() {}
	
	public EsenpatException(String codice) {
		this.codice = codice;
	}
	
	public EsenpatException(String codMessaggio, String codice, int status) {
    this.codMessaggio = codMessaggio;
    this.codice = codice;
    this.status = status;
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

  public String getCodMessaggio() {
    return codMessaggio;
  }

  public void setCodMessaggio(String codMessaggio) {
    this.codMessaggio = codMessaggio;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}