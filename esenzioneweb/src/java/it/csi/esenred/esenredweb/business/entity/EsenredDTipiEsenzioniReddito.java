/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

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
	
	@Column(name="\"FLAG_INSERIBILE\"")
	private Integer flagInseribile;

	@Column(name="\"DATA_SCADENZA\"")
	private String dataScadenza;
	
	@Column(name="\"DATA_MODIFICABILE\"")
	private Integer dataModificabile;
//	//bi-directional many-to-one association to EsenredCMatriceEsenzioni
//	@OneToMany(mappedBy="esenzioneEsistente")
//	private List<EsenredCMatriceEsenzioni> esenredCMatriceEsenzionis1;

//	//bi-directional many-to-one association to EsenredCMatriceEsenzioni
//	@OneToMany(mappedBy="esenzioneRichiesta")
//	private List<EsenredCMatriceEsenzioni> esenredCMatriceEsenzionis2;

	//bi-directional many-to-one association to EsenredTEsenzioniReddito
//	@OneToMany(mappedBy="esenredDTipiEsenzioniReddito")
//	private List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos;

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
	
	public Integer getFlagInseribile() {
		return this.flagInseribile;
	}

	public void setFlagInseribile(Integer flagInseribile) {
		this.flagInseribile = flagInseribile;
	}
	
	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Integer getDataModificabile() {
		return dataModificabile;
	}

	public void setDataModificabile(Integer dataModificabile) {
		this.dataModificabile = dataModificabile;
	}
//	public List<EsenredCMatriceEsenzioni> getEsenredCMatriceEsenzionis1() {
//		return this.esenredCMatriceEsenzionis1;
//	}
//
//	public void setEsenredCMatriceEsenzionis1(List<EsenredCMatriceEsenzioni> esenredCMatriceEsenzionis1) {
//		this.esenredCMatriceEsenzionis1 = esenredCMatriceEsenzionis1;
//	}
//
//	public EsenredCMatriceEsenzioni addEsenredCMatriceEsenzionis1(EsenredCMatriceEsenzioni esenredCMatriceEsenzionis1) {
//		getEsenredCMatriceEsenzionis1().add(esenredCMatriceEsenzionis1);
//		esenredCMatriceEsenzionis1.setEsenredDTipiEsenzioniReddito1(this);
//
//		return esenredCMatriceEsenzionis1;
//	}
//
//	public EsenredCMatriceEsenzioni removeEsenredCMatriceEsenzionis1(EsenredCMatriceEsenzioni esenredCMatriceEsenzionis1) {
//		getEsenredCMatriceEsenzionis1().remove(esenredCMatriceEsenzionis1);
//		esenredCMatriceEsenzionis1.setEsenredDTipiEsenzioniReddito1(null);
//
//		return esenredCMatriceEsenzionis1;
//	}
//
//	public List<EsenredCMatriceEsenzioni> getEsenredCMatriceEsenzionis2() {
//		return this.esenredCMatriceEsenzionis2;
//	}
//
//	public void setEsenredCMatriceEsenzionis2(List<EsenredCMatriceEsenzioni> esenredCMatriceEsenzionis2) {
//		this.esenredCMatriceEsenzionis2 = esenredCMatriceEsenzionis2;
//	}
//
//	public EsenredCMatriceEsenzioni addEsenredCMatriceEsenzionis2(EsenredCMatriceEsenzioni esenredCMatriceEsenzionis2) {
//		getEsenredCMatriceEsenzionis2().add(esenredCMatriceEsenzionis2);
//		esenredCMatriceEsenzionis2.setEsenredDTipiEsenzioniReddito2(this);
//
//		return esenredCMatriceEsenzionis2;
//	}
//
//	public EsenredCMatriceEsenzioni removeEsenredCMatriceEsenzionis2(EsenredCMatriceEsenzioni esenredCMatriceEsenzionis2) {
//		getEsenredCMatriceEsenzionis2().remove(esenredCMatriceEsenzionis2);
//		esenredCMatriceEsenzionis2.setEsenredDTipiEsenzioniReddito2(null);
//
//		return esenredCMatriceEsenzionis2;
//	}
//
//	public List<EsenredTEsenzioniReddito> getEsenredTEsenzioniRedditos() {
//		return this.esenredTEsenzioniRedditos;
//	}
//
//	public void setEsenredTEsenzioniRedditos(List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos) {
//		this.esenredTEsenzioniRedditos = esenredTEsenzioniRedditos;
//	}
//
//	public EsenredTEsenzioniReddito addEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
//		getEsenredTEsenzioniRedditos().add(esenredTEsenzioniReddito);
//		esenredTEsenzioniReddito.setEsenredDTipiEsenzioniReddito(this);
//
//		return esenredTEsenzioniReddito;
//	}
//
//	public EsenredTEsenzioniReddito removeEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
//		getEsenredTEsenzioniRedditos().remove(esenredTEsenzioniReddito);
//		esenredTEsenzioniReddito.setEsenredDTipiEsenzioniReddito(null);
//
//		return esenredTEsenzioniReddito;
//	}

}