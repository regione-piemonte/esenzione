/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;

public class EsenzioneCodiceAcceleratore {
	protected String codice;
	
	protected String descrizione;
	
	protected String motivo;
	
	private boolean valido;

	public EsenzioneCodiceAcceleratore() {}
	public EsenzioneCodiceAcceleratore(Esenzione eDB) {
		this.codice = eDB.getCodEsenzione();
		this.descrizione = eDB.getDescEsenzione();
		this.motivo = eDB.getDescMotivo();
		this.valido = eDB.getFlagInseribile();
	}
	
	public EsenzioneCodiceAcceleratore(EsenredDTipiEsenzioniReddito eDB) {
		this.codice = eDB.getCodEsenzione();
		this.descrizione = eDB.getDescEsenzione();
		this.motivo = eDB.getDescMotivo();
		this.valido = eDB.getFlagInseribile().intValue() == 0 ? false : true;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public boolean getValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
}
