/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

/**
 * The persistent class for the "ESENZIONE_D_AZIONE" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_AZIONE\"")
@NamedQuery(name = "EsenzioneDAzione.findAll", query = "SELECT e FROM EsenzioneDAzione e")
public class EsenzioneDAzione implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_AZIONE\"")
  private Integer skAzione;

  @Column(name = "\"COD_AZIONE\"")
  private String codAzione;

  @Column(name = "\"DESC_AZIONE\"")
  private String descAzione;

  //bi-directional many-to-one association to EsenzioneRAzioniGruppoUtenti
  @OneToMany(mappedBy = "esenzioneDAzione")
  private Set<EsenzioneRAzioniGruppoUtenti> esenzioneRAzioniGruppoUtentis;
  
  //bi-directional many-to-one association to EsenzioneRAzioniPraticaStato
  @OneToMany(mappedBy = "esenzioneDAzione")
  private Set<EsenzioneRAzioniPraticaStato> esenzioneRAzioniPraticaStatos;

  public EsenzioneDAzione() {
  }

  public Integer getSkAzione() {
    return this.skAzione;
  }

  public void setSkAzione(Integer skAzione) {
    this.skAzione = skAzione;
  }

  public String getCodAzione() {
    return this.codAzione;
  }

  public void setCodAzione(String codAzione) {
    this.codAzione = codAzione;
  }

  public String getDescAzione() {
    return this.descAzione;
  }

  public void setDescAzione(String descAzione) {
    this.descAzione = descAzione;
  }

  public Set<EsenzioneRAzioniGruppoUtenti> getEsenzioneRAzioniGruppoUtentis() {
    return this.esenzioneRAzioniGruppoUtentis;
  }

  public void setEsenzioneRAzioniGruppoUtentis(Set<EsenzioneRAzioniGruppoUtenti> esenzioneRAzioniGruppoUtentis) {
    this.esenzioneRAzioniGruppoUtentis = esenzioneRAzioniGruppoUtentis;
  }
  
  public Set<EsenzioneRAzioniPraticaStato> getEsenzioneRAzioniPraticaStatos() {
	    return this.esenzioneRAzioniPraticaStatos;
	  }

  public void setEsenzioneRAzioniPraticaStatos(Set<EsenzioneRAzioniPraticaStato> esenzioneRAzioniPraticaStatos) {
	    this.esenzioneRAzioniPraticaStatos = esenzioneRAzioniPraticaStatos;
	  }
}