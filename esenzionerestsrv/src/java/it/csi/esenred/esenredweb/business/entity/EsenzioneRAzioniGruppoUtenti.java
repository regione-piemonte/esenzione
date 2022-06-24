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
 * The persistent class for the "ESENZIONE_R_AZIONI_GRUPPO_UTENTI" database table.
 * 
 */
@Entity
@IdClass(EsenzioneRAzioniGruppoUtentiPK.class)
@Table(name = "\"ESENZIONE_R_AZIONI_GRUPPO_UTENTI\"")
@NamedQuery(name = "EsenzioneRAzioniGruppoUtenti.findAll", query = "SELECT e FROM EsenzioneRAzioniGruppoUtenti e")
public class EsenzioneRAzioniGruppoUtenti implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_GRUPPO_UTENTI\"", nullable = false)
  private Integer skGruppoUtenti;

	@Id
  @Column(name = "\"SK_AZIONE\"", nullable = false)
  private Integer skAzione;

  //bi-directional many-to-one association to EsenzioneDGruppoUtenti
  @ManyToOne
  @JoinColumn(name = "\"SK_GRUPPO_UTENTI\"", insertable = false, updatable = false)
  private EsenzioneDGruppoUtenti esenzioneDGruppoUtenti;

  //bi-directional many-to-one association to EsenzioneDAzione
  @ManyToOne
  @JoinColumn(name = "\"SK_AZIONE\"", insertable = false, updatable = false)
  private EsenzioneDAzione esenzioneDAzione;

  public EsenzioneRAzioniGruppoUtenti() {
  }

  public Integer getSkGruppoUtenti() {
    return this.skGruppoUtenti;
  }

  public void setSkGruppoUtenti(Integer skGruppoUtenti) {
    this.skGruppoUtenti = skGruppoUtenti;
  }

  public Integer getSkAzione() {
    return this.skAzione;
  }

  public void setSkAzione(Integer skAzione) {
    this.skAzione = skAzione;
  }

  public EsenzioneDGruppoUtenti getEsenzioneDGruppoUtenti() {
    return this.esenzioneDGruppoUtenti;
  }

  public void setEsenzioneDGruppoUtenti(EsenzioneDGruppoUtenti esenzioneDGruppoUtenti) {
    this.esenzioneDGruppoUtenti = esenzioneDGruppoUtenti;
  }

  public EsenzioneDAzione getEsenzioneDAzione() {
    return this.esenzioneDAzione;
  }

  public void setEsenzioneDAzione(EsenzioneDAzione esenzioneDAzione) {
    this.esenzioneDAzione = esenzioneDAzione;
  }

}