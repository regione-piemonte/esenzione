/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class FiltriRicercaEsenzioniOperatore extends FiltriRicercaEsenzioniEsenred {

	/**
	 * Rappresenta il radio button dell'interfaccia di ricerca delle esenzioni da parte dell'operatore.
	 * Vale "NO" se e' stato scelto "Solo a carico mio"
	 * Vale "SI" se e' stato scelto "In carico ASL"
	 */
	private String inCaricoASL;
	
	private int pagina;
	
	private String orderBy = "";
	
	private boolean asc = true;
	
	private long idOperatoreRichiesta;
	
	private String idAuraOperatore;
	
	public long getIdOperatoreRichiesta() {
		return idOperatoreRichiesta;
	}

	public void setIdAuraOperatore(String idAuraOperatore) {
		this.idAuraOperatore = idAuraOperatore;
	}
	
	public String getIdAuraOperatore() {
		return idAuraOperatore;
	}

	public void setIdOperatoreRichiesta(long idOperatoreRichiesta) {
		this.idOperatoreRichiesta = idOperatoreRichiesta;
	}

	public String getInCaricoASL() {
		return inCaricoASL;
	}

	public void setInCaricoASL(String inCaricoASL) {
		this.inCaricoASL = inCaricoASL;
	}	

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

}