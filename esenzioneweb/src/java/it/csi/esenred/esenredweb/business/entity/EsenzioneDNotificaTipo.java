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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;

/**
 * The persistent class for the "ESENZIONE_D_NOTIFICA_TIPO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_NOTIFICA_TIPO\"")
@NamedQuery(name = "EsenzioneDNotificaTipo.findAll", query = "SELECT e FROM EsenzioneDNotificaTipo e")
public class EsenzioneDNotificaTipo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"COD_NOTIFICA\"", nullable = false, length = 2147483647)
  private String codNotifica;

  @Column(name = "\"DESC_NOTIFICA\"", nullable = false, length = 2147483647)
  private String descNotifica;

  @Column(name = "\"SK_DESTINATARIO_TIPO\"", nullable = false)
  private Long skDestinatarioTipo;

  @Id
  @Column(name = "\"SK_NOTIFICA_TIPO\"", unique = true, nullable = false)
  private Integer skNotificaTipo;

  //bi-directional many-to-one association to EsenzioneRDestinatarioTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_DESTINATARIO_TIPO\"", insertable = false, updatable = false)
  private EsenzioneRDestinatarioTipo esenzioneRDestinatarioTipo;

  //bi-directional many-to-one association to EsenredWNotifiche
  @OneToMany(mappedBy = "esenzioneDNotificaTipo")
  private List<EsenredWNotifiche> esenredWNotifiches;

  public EsenzioneDNotificaTipo() {
  }

  public String getCodNotifica() {
    return this.codNotifica;
  }

  public void setCodNotifica(String codNotifica) {
    this.codNotifica = codNotifica;
  }

  public String getDescNotifica() {
    return this.descNotifica;
  }

  public void setDescNotifica(String descNotifica) {
    this.descNotifica = descNotifica;
  }

  public Long getSkDestinatarioTipo() {
    return this.skDestinatarioTipo;
  }

  public void setSkDestinatarioTipo(Long skDestinatarioTipo) {
    this.skDestinatarioTipo = skDestinatarioTipo;
  }

  public Integer getSkNotificaTipo() {
    return this.skNotificaTipo;
  }

  public void setSkNotificaTipo(Integer skNotificaTipo) {
    this.skNotificaTipo = skNotificaTipo;
  }

  public EsenzioneRDestinatarioTipo getEsenzioneRDestinatarioTipo() {
    return this.esenzioneRDestinatarioTipo;
  }

  public void setEsenzioneRDestinatarioTipo(EsenzioneRDestinatarioTipo esenzioneRDestinatarioTipo) {
    this.esenzioneRDestinatarioTipo = esenzioneRDestinatarioTipo;
  }

}