/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_PRESTAZIONE_SPECIALISTICA\"")
@NamedQuery(name = "EsenzioneDPrestazioneSpecialistica.findAll", query = "SELECT e FROM EsenzioneDPrestazioneSpecialistica e")
public class EsenzioneDPrestazioneSpecialistica implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_PRESTAZIONE\"", unique = true, nullable = false)
  private Integer skPrestazione;

  @Column(name = "\"COD_NOTA\"", length = 2147483647)
  private String codNota;

  @Column(name = "\"COD_PREST_PUNTO\"", length = 2147483647)
  private String codPrestPunto;

  @Column(name = "\"COD_PRESTAZIONE\"", nullable = false, length = 2147483647)
  private String codPrestazione;

  @Column(name = "\"DESC_PRESTAZIONE\"", nullable = false, length = 2147483647)
  private String descPrestazione;

  @Column(name = "\"FLAG_CICLI_TERAPIA\"", nullable = false)
  private Boolean flagCicliTerapia;

  //bi-directional many-to-one association to EsenzioneRDiagnosiPrestazione
  @OneToMany(mappedBy = "esenzioneDPrestazioneSpecialistica")
  private Set<EsenzioneRDiagnosiPrestazione> esenzioneRDiagnosiPrestaziones;

  public EsenzioneDPrestazioneSpecialistica() {
  }

  public Integer getSkPrestazione() {
    return this.skPrestazione;
  }

  public void setSkPrestazione(Integer skPrestazione) {
    this.skPrestazione = skPrestazione;
  }

  public String getCodNota() {
    return this.codNota;
  }

  public void setCodNota(String codNota) {
    this.codNota = codNota;
  }

  public String getCodPrestPunto() {
    return this.codPrestPunto;
  }

  public void setCodPrestPunto(String codPrestPunto) {
    this.codPrestPunto = codPrestPunto;
  }

  public String getCodPrestazione() {
    return this.codPrestazione;
  }

  public void setCodPrestazione(String codPrestazione) {
    this.codPrestazione = codPrestazione;
  }

  public String getDescPrestazione() {
    return this.descPrestazione;
  }

  public void setDescPrestazione(String descPrestazione) {
    this.descPrestazione = descPrestazione;
  }

  public Boolean getFlagCicliTerapia() {
    return this.flagCicliTerapia;
  }

  public void setFlagCicliTerapia(Boolean flagCicliTerapia) {
    this.flagCicliTerapia = flagCicliTerapia;
  }

  public Set<EsenzioneRDiagnosiPrestazione> getEsenzioneRDiagnosiPrestaziones() {
    return this.esenzioneRDiagnosiPrestaziones;
  }

  public void setEsenzioneRDiagnosiPrestaziones(Set<EsenzioneRDiagnosiPrestazione> esenzioneRDiagnosiPrestaziones) {
    this.esenzioneRDiagnosiPrestaziones = esenzioneRDiagnosiPrestaziones;
  }

  public EsenzioneRDiagnosiPrestazione addEsenzioneRDiagnosiPrestazione(EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione) {
    getEsenzioneRDiagnosiPrestaziones().add(esenzioneRDiagnosiPrestazione);
    esenzioneRDiagnosiPrestazione.setEsenzioneDPrestazioneSpecialistica(this);

    return esenzioneRDiagnosiPrestazione;
  }

  public EsenzioneRDiagnosiPrestazione removeEsenzioneRDiagnosiPrestazione(EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione) {
    getEsenzioneRDiagnosiPrestaziones().remove(esenzioneRDiagnosiPrestazione);
    esenzioneRDiagnosiPrestazione.setEsenzioneDPrestazioneSpecialistica(null);

    return esenzioneRDiagnosiPrestazione;
  }

}