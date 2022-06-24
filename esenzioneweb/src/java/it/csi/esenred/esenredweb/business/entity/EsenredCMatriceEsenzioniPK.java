/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class EsenredCMatriceEsenzioniPK implements Serializable {
	
	@Id
	@Column(name = "\"COD_RICHIESTA\"")
    private String codRichiesta;
	
	@Id
	@Column(name = "\"COD_ESISTENTE\"")
    private String codEsistente;

    public String getCodRichiesta() {
        return codRichiesta;
    }

    public void setCodRichiesta(String codRichiesta) {
        this.codRichiesta = codRichiesta;
    }

    public String getCodEsistente() {
        return codEsistente;
    }

    public void setCodEsistente(String codEsistente) {
        this.codEsistente = codEsistente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EsenredCMatriceEsenzioniPK that = (EsenredCMatriceEsenzioniPK) o;

        if (codRichiesta != null ? !codRichiesta.equals(that.codRichiesta) : that.codRichiesta != null) return false;
        if (codEsistente != null ? !codEsistente.equals(that.codEsistente) : that.codEsistente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codRichiesta != null ? codRichiesta.hashCode() : 0;
        result = 31 * result + (codEsistente != null ? codEsistente.hashCode() : 0);
        return result;
    }
}
