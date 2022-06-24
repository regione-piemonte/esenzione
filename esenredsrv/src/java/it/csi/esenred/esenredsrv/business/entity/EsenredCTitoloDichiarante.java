/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ESENRED_C_TITOLO_DICHIARANTE" database table.
 * 
 */
@Entity
@Table(name="\"ESENRED_C_TITOLO_DICHIARANTE\"")
@NamedQuery(name="EsenredCTitoloDichiarante.findAll", query="SELECT e FROM EsenredCTitoloDichiarante e")
public class EsenredCTitoloDichiarante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"COD_TITOLO\"")
	private String codTitolo;

	@Column(name="\"DESCRIZIONE\"")
	private String descrizione;

	//bi-directional many-to-one association to EsenredTEsenzioniReddito
	@OneToMany(mappedBy="esenredCTitoloDichiarante")
	private List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos;

	public EsenredCTitoloDichiarante() {
	}

	public String getCodTitolo() {
		return this.codTitolo;
	}

	public void setCodTitolo(String codTitolo) {
		this.codTitolo = codTitolo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EsenredTEsenzioniReddito> getEsenredTEsenzioniRedditos() {
		return this.esenredTEsenzioniRedditos;
	}

	public void setEsenredTEsenzioniRedditos(List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos) {
		this.esenredTEsenzioniRedditos = esenredTEsenzioniRedditos;
	}

	public EsenredTEsenzioniReddito addEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
		getEsenredTEsenzioniRedditos().add(esenredTEsenzioniReddito);
		esenredTEsenzioniReddito.setEsenredCTitoloDichiarante(this);

		return esenredTEsenzioniReddito;
	}

	public EsenredTEsenzioniReddito removeEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
		getEsenredTEsenzioniRedditos().remove(esenredTEsenzioniReddito);
		esenredTEsenzioniReddito.setEsenredCTitoloDichiarante(null);

		return esenredTEsenzioniReddito;
	}

}