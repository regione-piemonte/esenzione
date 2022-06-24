/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;
import javax.persistence.*;


@Embeddable
@Table(name="\"ESENRED_C_MATRICE_ESENZIONI\"")
@NamedQuery(name="EsenredCMatriceEsenzioni.findAll", query="SELECT e FROM EsenredCMatriceEsenzioni e")
public class EsenredCMatriceEsenzioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"COD_ESISTENTE\"")
	private String codEsistente;

	@Column(name="\"COD_RICHIESTA\"")
	private String codRichiesta;

	@Column(name="\"FLAG_INSERIMENTO\"")
	private String flagInserimento;

	//bi-directional many-to-one association to EsenredDTipiEsenzioniReddito
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="\"COD_ESISTENTE\"", referencedColumnName="\"COD_ESENZIONE\"")
		})
	private EsenredDTipiEsenzioniReddito esenzioneEsistente;

	//bi-directional many-to-one association to EsenredDTipiEsenzioniReddito
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="\"COD_RICHIESTA\"", referencedColumnName="\"COD_ESENZIONE\"")
		})
	private EsenredDTipiEsenzioniReddito esenzioneRichiesta;

	public EsenredCMatriceEsenzioni() {
	}

	public String getCodEsistente() {
		return this.codEsistente;
	}

	public void setCodEsistente(String codEsistente) {
		this.codEsistente = codEsistente;
	}

	public String getCodRichiesta() {
		return this.codRichiesta;
	}

	public void setCodRichiesta(String codRichiesta) {
		this.codRichiesta = codRichiesta;
	}

	public String getFlagInserimento() {
		return this.flagInserimento;
	}

	public void setFlagInserimento(String flagInserimento) {
		this.flagInserimento = flagInserimento;
	}

	public EsenredDTipiEsenzioniReddito getEsenzioneEsistente() {
		return esenzioneEsistente;
	}

	public void setEsenzioneEsistente(EsenredDTipiEsenzioniReddito esenzioneEsistente) {
		this.esenzioneEsistente = esenzioneEsistente;
	}

	public EsenredDTipiEsenzioniReddito getEsenzioneRichiesta() {
		return esenzioneRichiesta;
	}

	public void setEsenzioneRichiesta(EsenredDTipiEsenzioniReddito esenzioneRichiesta) {
		this.esenzioneRichiesta = esenzioneRichiesta;
	}
}