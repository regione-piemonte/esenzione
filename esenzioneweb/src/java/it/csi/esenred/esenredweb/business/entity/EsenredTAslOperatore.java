/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;

/**
 * The persistent class for the "ESENRED_T_ASL_OPERATORE" database table.
 * 
 */
@Entity
@Table(name = "\"ESENRED_T_ASL_OPERATORE\"")
@NamedQueries({ @NamedQuery(name = "EsenredTAslOperatore.findAll", query = "SELECT e FROM EsenredTAslOperatore e"),
    @NamedQuery(name = "EsenredTAslOperatore.findPerAsl", query = "SELECT e FROM EsenredTAslOperatore e where e.codAsl = :codAsl"),
    @NamedQuery(name = "EsenredTAslOperatore.findPerCF", query = "SELECT e FROM EsenredTAslOperatore e where e.codFiscaleOperatore = :codFiscaleOperatore") })

public class EsenredTAslOperatore implements Serializable {
  private static final long serialVersionUID = 1L;


  @Column(name = "\"COD_ASL\"")
  private String codAsl;

	@Id
  @Column(name = "\"COD_FISCALE_OPERATORE\"")
  private String codFiscaleOperatore;

  @Column(name = "\"NUM_DISTRETTO\"")
  private Integer numDistretto;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenredTAslOperatore")
  private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenredTAslOperatore")
  private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

  public EsenredTAslOperatore() {
  }

  public String getCodAsl() {
    return this.codAsl;
  }

  public void setCodAsl(String codAsl) {
    this.codAsl = codAsl;
  }

  public String getCodFiscaleOperatore() {
    return this.codFiscaleOperatore;
  }

  public void setCodFiscaleOperatore(String codFiscaleOperatore) {
    this.codFiscaleOperatore = codFiscaleOperatore;
  }

  public Integer getNumDistretto() {
    return numDistretto;
  }

  public void setNumDistretto(Integer numDistretto) {
    this.numDistretto = numDistretto;
  }

  public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public List<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones() {
    return esenzioneTPraticaEsenziones;
  }

  public void setEsenzioneTPraticaEsenziones(List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones) {
    this.esenzioneTPraticaEsenziones = esenzioneTPraticaEsenziones;
  }

}