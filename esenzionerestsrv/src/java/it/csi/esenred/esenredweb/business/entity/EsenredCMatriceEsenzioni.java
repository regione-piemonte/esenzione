/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"ESENRED_C_MATRICE_ESENZIONI\"")

@NamedQueries({
		@NamedQuery(name = "EsenredCMatriceEsenzioni.findAll", query = "SELECT e FROM EsenredCMatriceEsenzioni e"),
		@NamedQuery(name = "EsenredCMatriceEsenzioni.getRiga", query = "SELECT t FROM EsenredCMatriceEsenzioni t where t.codEsistente = :codEsistente and t.codRichiesta = :codRichiesta") 
		})
@IdClass(EsenredCMatriceEsenzioniPK.class)
public class EsenredCMatriceEsenzioni {
	
	@Id
	@Column(name = "\"COD_RICHIESTA\"")
	private String codRichiesta;
	
	@Id
	@Column(name = "\"COD_ESISTENTE\"")
	private String codEsistente;
	
	@Basic
	@Column(name = "\"FLAG_INSERIMENTO\"")
	private String flagInserimento;

//	@Id
//	@Column(name = "\"COD_RICHIESTA\"", nullable = false, length = 3)
	public String getCodRichiesta() {
		return codRichiesta;
	}

	public void setCodRichiesta(String codRichiesta) {
		this.codRichiesta = codRichiesta;
	}

//	@Id
//	@Column(name = "\"COD_ESISTENTE\"", nullable = false, length = 3)
	public String getCodEsistente() {
		return codEsistente;
	}

	public void setCodEsistente(String codEsistente) {
		this.codEsistente = codEsistente;
	}

//	@Basic
//	@Column(name = "\"FLAG_INSERIMENTO\"", nullable = false, length = 2)
	public String getFlagInserimento() {
		return flagInserimento;
	}

	public void setFlagInserimento(String flagInserimento) {
		this.flagInserimento = flagInserimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EsenredCMatriceEsenzioni that = (EsenredCMatriceEsenzioni) o;

		if (codRichiesta != null ? !codRichiesta.equals(that.codRichiesta) : that.codRichiesta != null)
			return false;
		if (codEsistente != null ? !codEsistente.equals(that.codEsistente) : that.codEsistente != null)
			return false;
		if (flagInserimento != null ? !flagInserimento.equals(that.flagInserimento) : that.flagInserimento != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = codRichiesta != null ? codRichiesta.hashCode() : 0;
		result = 31 * result + (codEsistente != null ? codEsistente.hashCode() : 0);
		result = 31 * result + (flagInserimento != null ? flagInserimento.hashCode() : 0);
		return result;
	}
}
