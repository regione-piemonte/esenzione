/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.enums;

public enum TitoloDichiarante {

	INTERESSATO("0"),	
	GENITORE("1"),
	TUTORE("2"),
	INTERESSATO_ASSISTITO_DA_CURATORE("3"),
	CONIUGE_PARENTE_3_GRADO("4"),
	INTERESSATO_TRANITE_DELEGA("5");

	private String codice;
	
	private TitoloDichiarante(String codice) {		
		this.setCodice(codice);	
	}	
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public static String getTipo(String code) {	
		for(TitoloDichiarante a: TitoloDichiarante.values()) {		
			if(a.toString().equals(code)) {			
				return a.getCodice();		
			}		
		}		
		throw new IllegalArgumentException("Il titolo dichiarante non e' stato riconosciuto");	
	}
}