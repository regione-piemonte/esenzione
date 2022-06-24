/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;


public class Info {

    protected String scadenza;

    protected boolean bloccato;
    
    protected String data_sblocco;
	
    public Info() {}
    
   
   
    public String getScadenza() {
		return scadenza;
	}



	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}



	public boolean isBloccato() {
		return bloccato;
	}



	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}



	public String getData_sblocco() {
		return data_sblocco;
	}



	public void setData_sblocco(String data_sblocco) {
		this.data_sblocco = data_sblocco;
	}


    public Info(String scadenza,boolean bloccato,String data_sblocco) {
      	 this.scadenza = scadenza;
         this.bloccato = bloccato;
         this.data_sblocco = data_sblocco;  
      }
}