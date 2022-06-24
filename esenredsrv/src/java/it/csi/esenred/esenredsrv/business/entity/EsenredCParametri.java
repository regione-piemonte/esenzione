/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "ESENRED_C_PARAMETRI" database table.
 * 
 */
@Entity
@Table(name="\"ESENRED_C_PARAMETRI\"")
@NamedQueries({ 
	@NamedQuery(name="EsenredCParametri.findAll", query="SELECT parametro FROM EsenredCParametri parametro"),
	@NamedQuery(name="EsenredCParametri.findParametro", query="SELECT p FROM EsenredCParametri p where p.codice = :codice")
})
public class EsenredCParametri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"NUM_PROGRESSIVO\"")
	private Integer numProgressivo;

	@Column(name="\"CODICE\"")
	private String codice;

	@Column(name="\"VALORE\"")
	private String valore;

	public EsenredCParametri() {
	}

	public Integer getNumProgressivo() {
		return this.numProgressivo;
	}

	public void setNumProgressivo(Integer numProgressivo) {
		this.numProgressivo = numProgressivo;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

}