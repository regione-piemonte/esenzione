/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_D_DIAGNOSI" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_DIAGNOSI\"")
@NamedQuery(name = "EsenzioneDDiagnosi.findAll", query = "SELECT e FROM EsenzioneDDiagnosi e")
public class EsenzioneDDiagnosi implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_DIAGNOSI\"", unique = true, nullable = false)
  private Integer skDiagnosi;

  @Column(name = "\"COD_DIAGNOSI\"", nullable = false, length = 2147483647)
  private String codDiagnosi;

  @Column(name = "\"DAT_FINE_VALIDITA\"")
  private Timestamp datFineValidita;

  @Column(name = "\"DAT_INIZIO_VALIDITA\"", nullable = false)
  private Timestamp datInizioValidita;

  @Column(name = "\"DESC_DIAGNOSI\"", nullable = false, length = 2147483647)
  private String descDiagnosi;

  @Column(name = "\"NUM_DURATA\"")
  private Integer numDurata;

  @Column(name = "\"SK_DURATA_TIPO\"", nullable = false)
  private Long skDurataTipo;

  @Column(name = "\"SK_ESENZIONE\"", nullable = false)
  private Long skEsenzione;

  //bi-directional many-to-one association to EsenzioneDDurataTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_DURATA_TIPO\"", insertable = false, updatable = false)
  private EsenzioneDDurataTipo esenzioneDDurataTipo;

  //bi-directional many-to-one association to EsenzioneDEsenzione
  @ManyToOne
  @JoinColumn(name = "\"SK_ESENZIONE\"", insertable = false, updatable = false)
  private EsenzioneDEsenzione esenzioneDEsenzione;

  //bi-directional many-to-one association to EsenzioneRDiagnosiPrestazione
  @OneToMany(mappedBy = "esenzioneDDiagnosi")
  private Set<EsenzioneRDiagnosiPrestazione> esenzioneRDiagnosiPrestaziones;

  //bi-directional many-to-one association to EsenzioneSDocumento
  @OneToMany(mappedBy = "esenzioneDDiagnosi")
  private List<EsenzioneSDocumento> esenzioneSDocumentos;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDDiagnosi")
  private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

  //bi-directional many-to-one association to EsenzioneTDocumento
  @OneToMany(mappedBy = "esenzioneDDiagnosi")
  private List<EsenzioneTDocumento> esenzioneTDocumentos;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDDiagnosi")
  private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

  public EsenzioneDDiagnosi() {
  }

  public Integer getSkDiagnosi() {
    return this.skDiagnosi;
  }

  public void setSkDiagnosi(Integer skDiagnosi) {
    this.skDiagnosi = skDiagnosi;
  }

  public String getCodDiagnosi() {
    return this.codDiagnosi;
  }

  public void setCodDiagnosi(String codDiagnosi) {
    this.codDiagnosi = codDiagnosi;
  }

  public Timestamp getDatFineValidita() {
    return this.datFineValidita;
  }

  public void setDatFineValidita(Timestamp datFineValidita) {
    this.datFineValidita = datFineValidita;
  }

  public Timestamp getDatInizioValidita() {
    return this.datInizioValidita;
  }

  public void setDatInizioValidita(Timestamp datInizioValidita) {
    this.datInizioValidita = datInizioValidita;
  }

  public String getDescDiagnosi() {
    return this.descDiagnosi;
  }

  public void setDescDiagnosi(String descDiagnosi) {
    this.descDiagnosi = descDiagnosi;
  }

  public Integer getNumDurata() {
    return this.numDurata;
  }

  public void setNumDurata(Integer numDurata) {
    this.numDurata = numDurata;
  }

  public Long getSkDurataTipo() {
    return this.skDurataTipo;
  }

  public void setSkDurataTipo(Long skDurataTipo) {
    this.skDurataTipo = skDurataTipo;
  }

  public Long getSkEsenzione() {
    return this.skEsenzione;
  }

  public void setSkEsenzione(Long skEsenzione) {
    this.skEsenzione = skEsenzione;
  }

  public EsenzioneDDurataTipo getEsenzioneDDurataTipo() {
    return this.esenzioneDDurataTipo;
  }

  public void setEsenzioneDDurataTipo(EsenzioneDDurataTipo esenzioneDDurataTipo) {
    this.esenzioneDDurataTipo = esenzioneDDurataTipo;
  }

  public EsenzioneDEsenzione getEsenzioneDEsenzione() {
    return this.esenzioneDEsenzione;
  }

  public void setEsenzioneDEsenzione(EsenzioneDEsenzione esenzioneDEsenzione) {
    this.esenzioneDEsenzione = esenzioneDEsenzione;
  }

  public Set<EsenzioneRDiagnosiPrestazione> getEsenzioneRDiagnosiPrestaziones() {
    return this.esenzioneRDiagnosiPrestaziones;
  }

  public void setEsenzioneRDiagnosiPrestaziones(Set<EsenzioneRDiagnosiPrestazione> esenzioneRDiagnosiPrestaziones) {
    this.esenzioneRDiagnosiPrestaziones = esenzioneRDiagnosiPrestaziones;
  }

  public EsenzioneRDiagnosiPrestazione addEsenzioneRDiagnosiPrestazione(EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione) {
    getEsenzioneRDiagnosiPrestaziones().add(esenzioneRDiagnosiPrestazione);
    esenzioneRDiagnosiPrestazione.setEsenzioneDDiagnosi(this);

    return esenzioneRDiagnosiPrestazione;
  }

  public EsenzioneRDiagnosiPrestazione removeEsenzioneRDiagnosiPrestazione(EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione) {
    getEsenzioneRDiagnosiPrestaziones().remove(esenzioneRDiagnosiPrestazione);
    esenzioneRDiagnosiPrestazione.setEsenzioneDDiagnosi(null);

    return esenzioneRDiagnosiPrestazione;
  }

  public List<EsenzioneSDocumento> getEsenzioneSDocumentos() {
    return this.esenzioneSDocumentos;
  }

  public void setEsenzioneSDocumentos(List<EsenzioneSDocumento> esenzioneSDocumentos) {
    this.esenzioneSDocumentos = esenzioneSDocumentos;
  }

  public EsenzioneSDocumento addEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
    getEsenzioneSDocumentos().add(esenzioneSDocumento);
    esenzioneSDocumento.setEsenzioneDDiagnosi(this);

    return esenzioneSDocumento;
  }

  public EsenzioneSDocumento removeEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
    getEsenzioneSDocumentos().remove(esenzioneSDocumento);
    esenzioneSDocumento.setEsenzioneDDiagnosi(null);

    return esenzioneSDocumento;
  }

  public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return this.esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDDiagnosi(this);

    return esenzioneSPraticaEsenzione;
  }

  public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDDiagnosi(null);

    return esenzioneSPraticaEsenzione;
  }

  public List<EsenzioneTDocumento> getEsenzioneTDocumentos() {
    return this.esenzioneTDocumentos;
  }

  public void setEsenzioneTDocumentos(List<EsenzioneTDocumento> esenzioneTDocumentos) {
    this.esenzioneTDocumentos = esenzioneTDocumentos;
  }

  public EsenzioneTDocumento addEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().add(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneDDiagnosi(this);

    return esenzioneTDocumento;
  }

  public EsenzioneTDocumento removeEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().remove(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneDDiagnosi(null);

    return esenzioneTDocumento;
  }

  public List<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones() {
    return this.esenzioneTPraticaEsenziones;
  }

  public void setEsenzioneTPraticaEsenziones(List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones) {
    this.esenzioneTPraticaEsenziones = esenzioneTPraticaEsenziones;
  }

  public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().add(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDDiagnosi(this);

    return esenzioneTPraticaEsenzione;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDDiagnosi(null);

    return esenzioneTPraticaEsenzione;
  }

}