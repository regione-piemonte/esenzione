/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;

public class TitoloDichiaranteAcceleratore {
	
	protected String codice;
	
	protected String descrizione;
    
	public TitoloDichiaranteAcceleratore(String codice,String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}
	
	public TitoloDichiaranteAcceleratore(EsenredCTitoloDichiarante tDB) {
		this.codice = tDB.getCodTitolo();
		this.descrizione = tDB.getDescrizione();
	}
	
	public TitoloDichiaranteAcceleratore(TitoloDichiarante tDB) {
		this.codice = tDB.getCodTitolo();
		this.descrizione = tDB.getDescTitolo();
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