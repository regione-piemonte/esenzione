/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the "ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" database table.
 * 
 */
@Embeddable
public class EsenzioneRMotivazionePraticaStatoPK implements Serializable {
  //default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name = "\"SK_PRATICA_STATO\"", insertable = false, updatable = false, unique = true, nullable = false)
  private Long skPraticaStato;

  @Column(name = "\"SK_MOTIVAZIONE_TIPO\"", insertable = false, updatable = false, unique = true, nullable = false)
  private Long skMotivazioneTipo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "\"DAT_INIZIO_VALIDITA\"", unique = true, nullable = false)
  private java.util.Date datInizioValidita;

  public EsenzioneRMotivazionePraticaStatoPK() {
  }

  public Long getSkPraticaStato() {
    return this.skPraticaStato;
  }

  public void setSkPraticaStato(Long skPraticaStato) {
    this.skPraticaStato = skPraticaStato;
  }

  public Long getSkMotivazioneTipo() {
    return this.skMotivazioneTipo;
  }

  public void setSkMotivazioneTipo(Long skMotivazioneTipo) {
    this.skMotivazioneTipo = skMotivazioneTipo;
  }

  public java.util.Date getDatInizioValidita() {
    return this.datInizioValidita;
  }

  public void setDatInizioValidita(java.util.Date datInizioValidita) {
    this.datInizioValidita = datInizioValidita;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof EsenzioneRMotivazionePraticaStatoPK)) {
      return false;
    }
    EsenzioneRMotivazionePraticaStatoPK castOther = (EsenzioneRMotivazionePraticaStatoPK) other;
    return this.skPraticaStato.equals(castOther.skPraticaStato) && this.skMotivazioneTipo.equals(castOther.skMotivazioneTipo) && this.datInizioValidita.equals(castOther.datInizioValidita);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.skPraticaStato.hashCode();
    hash = hash * prime + this.skMotivazioneTipo.hashCode();
    hash = hash * prime + this.datInizioValidita.hashCode();

    return hash;
  }
}