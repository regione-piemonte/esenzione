/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENRED_C_MESSAGGI" database table.
 * 
 */
@Entity
@Table(name = "\"ESENRED_C_MESSAGGI\"")
@NamedQueries({ @NamedQuery(name = "EsenredCMessaggi.findAll", query = "SELECT e FROM EsenredCMessaggi e"),
    @NamedQuery(name = "EsenredCMessaggi.findMessaggio", query = "SELECT r FROM EsenredCMessaggi r where r.codice = :codice"),
    @NamedQuery(name = "EsenredCMessaggi.findPerCodiceLike", query = "SELECT r FROM EsenredCMessaggi r where r.codice like :codice") })
public class EsenredCMessaggi implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"CODICE\"")
  private String codice;

  @Column(name = "\"COD_TIPO_ERRORE\"", length = 3)
  private String codTipoErrore;

  @Column(name = "\"IDMESSAGGIO\"")
  private Integer idmessaggio;

  @Column(name = "\"TESTO\"")
  private String testo;

  @Column(name = "\"TESTO_MSG_RESTITUITO\"", length = 50)
  private String testoMsgRestituito;

  public EsenredCMessaggi() {
  }

  public String getCodice() {
    return this.codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public String getTesto() {
    return this.testo;
  }

  public void setTesto(String testo) {
    this.testo = testo;
  }

  public String getCodTipoErrore() {
    return codTipoErrore;
  }

  public void setCodTipoErrore(String codTipoErrore) {
    this.codTipoErrore = codTipoErrore;
  }

  public Integer getIdmessaggio() {
    return idmessaggio;
  }

  public void setIdmessaggio(Integer idmessaggio) {
    this.idmessaggio = idmessaggio;
  }

  public String getTestoMsgRestituito() {
    return testoMsgRestituito;
  }

  public void setTestoMsgRestituito(String testoMsgRestituito) {
    this.testoMsgRestituito = testoMsgRestituito;
  }

}