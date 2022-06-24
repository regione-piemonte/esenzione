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
 * The primary key class for the "ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" database table.
 * 
 */
@Embeddable
public class EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK implements Serializable {
  //default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", insertable = false, updatable = false, unique = true, nullable = false)
  private Long skTipologiaEsenzioneAura;

  @Column(name = "\"SK_GRUPPO\"", insertable = false, updatable = false, unique = true, nullable = false)
  private Long skGruppo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "\"DAT_INIZIO_VALIDITA\"", unique = true, nullable = false)
  private java.util.Date datInizioValidita;

  public EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK() {
  }

  public Long getSkTipologiaEsenzioneAura() {
    return this.skTipologiaEsenzioneAura;
  }

  public void setSkTipologiaEsenzioneAura(Long skTipologiaEsenzioneAura) {
    this.skTipologiaEsenzioneAura = skTipologiaEsenzioneAura;
  }

  public Long getSkGruppo() {
    return this.skGruppo;
  }

  public void setSkGruppo(Long skGruppo) {
    this.skGruppo = skGruppo;
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
    if (!(other instanceof EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK)) {
      return false;
    }
    EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK castOther = (EsenzioneRGruppoEsenzioniEsenzioneAuraTipoPK) other;
    return this.skTipologiaEsenzioneAura.equals(castOther.skTipologiaEsenzioneAura) && this.skGruppo.equals(castOther.skGruppo) && this.datInizioValidita.equals(castOther.datInizioValidita);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.skTipologiaEsenzioneAura.hashCode();
    hash = hash * prime + this.skGruppo.hashCode();
    hash = hash * prime + this.datInizioValidita.hashCode();

    return hash;
  }
}