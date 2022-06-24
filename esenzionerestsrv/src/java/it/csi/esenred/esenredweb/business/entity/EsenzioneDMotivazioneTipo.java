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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_D_MOTIVAZIONE_TIPO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_MOTIVAZIONE_TIPO\"")
@NamedQueries({@NamedQuery(name = "EsenzioneDMotivazioneTipo.findAll", query = "SELECT e FROM EsenzioneDMotivazioneTipo e"),
  @NamedQuery(name = "EsenzioneDMotivazioneTipo.findPerCodMotivazione", query = "SELECT e FROM EsenzioneDMotivazioneTipo e where e.codMotivazione = :codMotivazione") })

public class EsenzioneDMotivazioneTipo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_MOTIVAZIONE_TIPO\"", unique = true, nullable = false)
  private Integer skMotivazioneTipo;

  @Column(name = "\"COD_MOTIVAZIONE\"", nullable = false, length = 2147483647)
  private String codMotivazione;

  @Column(name = "\"DESC_MOTIVAZIONE\"", nullable = false, length = 2147483647)
  private String descMotivazione;

  //bi-directional many-to-one association to EsenzioneRMotivazionePraticaStato
  @OneToMany(mappedBy = "esenzioneDMotivazioneTipo")
  private List<EsenzioneRMotivazionePraticaStato> esenzioneRMotivazionePraticaStatos;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDMotivazioneTipo")
  private Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDMotivazioneTipo")
  private Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

  public EsenzioneDMotivazioneTipo() {
  }

  public Integer getSkMotivazioneTipo() {
    return this.skMotivazioneTipo;
  }

  public void setSkMotivazioneTipo(Integer skMotivazioneTipo) {
    this.skMotivazioneTipo = skMotivazioneTipo;
  }

  public String getCodMotivazione() {
    return this.codMotivazione;
  }

  public void setCodMotivazione(String codMotivazione) {
    this.codMotivazione = codMotivazione;
  }

  public String getDescMotivazione() {
    return this.descMotivazione;
  }

  public void setDescMotivazione(String descMotivazione) {
    this.descMotivazione = descMotivazione;
  }

  public List<EsenzioneRMotivazionePraticaStato> getEsenzioneRMotivazionePraticaStatos() {
    return this.esenzioneRMotivazionePraticaStatos;
  }

  public void setEsenzioneRMotivazionePraticaStatos(List<EsenzioneRMotivazionePraticaStato> esenzioneRMotivazionePraticaStatos) {
    this.esenzioneRMotivazionePraticaStatos = esenzioneRMotivazionePraticaStatos;
  }

  public EsenzioneRMotivazionePraticaStato addEsenzioneRMotivazionePraticaStato(EsenzioneRMotivazionePraticaStato esenzioneRMotivazionePraticaStato) {
    getEsenzioneRMotivazionePraticaStatos().add(esenzioneRMotivazionePraticaStato);
    esenzioneRMotivazionePraticaStato.setEsenzioneDMotivazioneTipo(this);

    return esenzioneRMotivazionePraticaStato;
  }

  public EsenzioneRMotivazionePraticaStato removeEsenzioneRMotivazionePraticaStato(EsenzioneRMotivazionePraticaStato esenzioneRMotivazionePraticaStato) {
    getEsenzioneRMotivazionePraticaStatos().remove(esenzioneRMotivazionePraticaStato);
    esenzioneRMotivazionePraticaStato.setEsenzioneDMotivazioneTipo(null);

    return esenzioneRMotivazionePraticaStato;
  }

  public Set<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return this.esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDMotivazioneTipo(this);

    return esenzioneSPraticaEsenzione;
  }

  public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDMotivazioneTipo(null);

    return esenzioneSPraticaEsenzione;
  }

  public Set<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones() {
    return this.esenzioneTPraticaEsenziones;
  }

  public void setEsenzioneTPraticaEsenziones(Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones) {
    this.esenzioneTPraticaEsenziones = esenzioneTPraticaEsenziones;
  }

  public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().add(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDMotivazioneTipo(this);

    return esenzioneTPraticaEsenzione;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDMotivazioneTipo(null);

    return esenzioneTPraticaEsenzione;
  }

}