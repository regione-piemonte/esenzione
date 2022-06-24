/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.enums;

public enum StatoEsenzione {

	IN_LAVORAZIONE("L", "In lavorazione"),	
	VALIDA("V", "In corso di validita'"),
	SCADUTA("S", "Scaduta"),
	REVOCATA("R", "Revocata"),
	NON_VALIDA("N", "Non valida"),
	REVOCATA_CON_BLOCCO("B", "Revocata con blocco");

	private String codice;
	private String descrizione;
	
	private StatoEsenzione(String codice, String descrizione) {		
		this.setCodice(codice);	
		this.setDescrizione(descrizione);
	}	
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public static String getTipo(String code) {	
		for(StatoEsenzione a: StatoEsenzione.values()) {		
			if(a.toString().equals(code)) {			
				return a.getCodice();		
			}		
		}		
		throw new IllegalArgumentException("Lo stato dell'esenzione non e' stato riconosciuto");	
	}

	public static String getDescrizione(String codStato) {
		for(StatoEsenzione a: StatoEsenzione.values()) {		
			if(a.getCodice().equals(codStato)) {			
				return a.getDescrizione();		
			}		
		}		
		throw new IllegalArgumentException("Lo stato dell'esenzione non e' stato riconosciuto");	
	}
}