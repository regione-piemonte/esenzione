/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;

public class LuogoNascita implements Comparable<LuogoNascita> {

	protected String codice;

	protected String descrizione;

	public LuogoNascita() {
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
	
	public LuogoNascita(Cittadino c) {
		String codComuneNascitaEsenred = null;
		
		if (!c.getComuneNascita().isEmpty()) {
			
			if (!c.getCodComuneNascita().isEmpty()) {
				codComuneNascitaEsenred = c.getCodComuneNascita();
			}
			else {
			ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
			EsenredCComuni elencoComuni = comuneIf.getElencoComuniPerCodIstat(c.getCodComuneNascita());
			codComuneNascitaEsenred = elencoComuni.getCodiceFiscale();
			}
		}
		this.codice = codComuneNascitaEsenred;
		this.descrizione = c.getComuneNascita();
	}

	public LuogoNascita(String codice, String descrizione) {
		
		if (descrizione.isEmpty() && !codice.isEmpty()) {
			ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
			EsenredCComuni elencoComuni = comuneIf.getElencoComuniPerCodIstat(codice);
			this.codice = codice;
			this.descrizione = elencoComuni.getDenominazione();
			}
		else {
			//vedo se non vuota descrizione prendo da aura e non da tabella comuni
			this.codice = codice;
			this.descrizione = descrizione;
			}
	}

	@Override
	public int compareTo(LuogoNascita arg0) {
		codice.compareTo(arg0.getCodice());
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuogoNascita other = (LuogoNascita) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
}