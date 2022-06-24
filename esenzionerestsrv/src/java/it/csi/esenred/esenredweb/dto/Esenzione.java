/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;

public class Esenzione {
	private String codEsenzione;
	
	private String descEsenzione;
	
	private String descMotivo;
	
	private boolean flagInseribile;

	public Esenzione(EsenredDTipiEsenzioniReddito eDB) {
		this.codEsenzione = eDB.getCodEsenzione();
		this.descEsenzione = eDB.getDescEsenzione();
		this.descMotivo = eDB.getDescMotivo();
		this.flagInseribile = eDB.getFlagInseribile().intValue() == 0 ? false : true;
	}

	public String getCodEsenzione() {
		return codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public boolean getFlagInseribile() {
		return flagInseribile;
	}

	public void setFlagInseribile(boolean flagInseribile) {
		this.flagInseribile = flagInseribile;
	}

	public String getDescEsenzione() {
		return descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}

	public String getDescMotivo() {
		return descMotivo;
	}

	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}

	@Override
	public String toString() {
		return "Esenzione [codEsenzione=" + codEsenzione + ", descEsenzione=" + descEsenzione + ", descMotivo="
				+ descMotivo + ",flagInseribile=" + flagInseribile +"]";
	}
}
