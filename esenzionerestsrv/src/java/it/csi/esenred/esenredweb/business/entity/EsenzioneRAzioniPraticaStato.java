/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_R_AZIONI_PRATICA_STATO" database table.
 * 
 */
@Entity
@IdClass(EsenzioneRAzioniPraticaStatoPK.class)
@Table(name = "\"ESENZIONE_R_AZIONI_PRATICA_STATO\"")
@NamedQuery(name = "EsenzioneRAzioniPraticaStato.findAll", query = "SELECT e FROM EsenzioneRAzioniPraticaStato e")
public class EsenzioneRAzioniPraticaStato implements Serializable {
  private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "\"SK_AZIONE\"", nullable = false)
	private Integer skAzione;

	@Id
	@Column(name = "\"SK_PRATICA_STATO\"", nullable = false)
	private Integer skPraticaStato;

  //bi-directional many-to-one association to EsenzioneDPraticaStato
  @ManyToOne
  @JoinColumn(name = "\"SK_PRATICA_STATO\"", insertable = false, updatable = false)
  private EsenzioneDPraticaStato esenzioneDPraticaStato;

  //bi-directional many-to-one association to EsenzioneDAzione
  @ManyToOne
  @JoinColumn(name = "\"SK_AZIONE\"", insertable = false, updatable = false)
  private EsenzioneDAzione esenzioneDAzione;
  
  public EsenzioneRAzioniPraticaStato() {
	}

	public Integer getSkAzione() {
		return this.skAzione;
	}

	public void setSkAzione(Integer skAzione) {
		this.skAzione = skAzione;
	}

	public Integer getSkPraticaStato() {
		return this.skPraticaStato;
	}

	public void setSkPraticaStato(Integer skPraticaStato) {
		this.skPraticaStato = skPraticaStato;
	}


  public EsenzioneDPraticaStato getEsenzioneDPraticaStato() {
    return this.esenzioneDPraticaStato;
  }

	public void setEsenzioneDPraticaStato(EsenzioneDPraticaStato esenzioneDPraticaStato) {
    this.esenzioneDPraticaStato = esenzioneDPraticaStato;
  }

  public EsenzioneDAzione getEsenzioneDAzione() {
    return this.esenzioneDAzione;
  }

  public void setEsenzioneDAzione(EsenzioneDAzione esenzioneDAzione) {
    this.esenzioneDAzione = esenzioneDAzione;
  }

}