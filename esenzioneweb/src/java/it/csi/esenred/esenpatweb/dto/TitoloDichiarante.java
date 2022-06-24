/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;

public class TitoloDichiarante {

	private String codTitolo;

	private String descTitolo;

	public TitoloDichiarante(EsenredCTitoloDichiarante tDB) {
		this.codTitolo = tDB.getCodTitolo();
		this.descTitolo = tDB.getDescrizione();
	}

	public String getCodTitolo() {
		return codTitolo;
	}

	public void setCodTitolo(String codTitolo) {
		this.codTitolo = codTitolo;
	}

	public String getDescTitolo() {
		return descTitolo;
	}

	public void setDescTitolo(String descTitolo) {
		this.descTitolo = descTitolo;
	}

	@Override
	public String toString() {
		return "TitoloDichiarante [codTitolo=" + codTitolo + ", descTitolo=" + descTitolo + "]";
	}
}