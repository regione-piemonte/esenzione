/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENRED_D_TIPI_ESENZIONI_REDDITO" database table.
 * 
 */
@Entity
@Table(name="\"ESENRED_D_TIPI_ESENZIONI_REDDITO\"")
@NamedQuery(name="EsenredDTipiEsenzioniReddito.findAll", query="SELECT e FROM EsenredDTipiEsenzioniReddito e")
public class EsenredDTipiEsenzioniReddito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"COD_ESENZIONE\"")
	private String codEsenzione;

	@Column(name="\"DESC_ESENZIONE\"")
	private String descEsenzione;

	@Column(name="\"DESC_MOTIVO\"")
	private String descMotivo;

	public EsenredDTipiEsenzioniReddito() {
	}

	public String getCodEsenzione() {
		return this.codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getDescEsenzione() {
		return this.descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}

	public String getDescMotivo() {
		return this.descMotivo;
	}

	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}


}