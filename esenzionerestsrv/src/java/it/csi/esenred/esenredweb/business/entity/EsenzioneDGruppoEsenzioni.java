/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_D_GRUPPO_ESENZIONI" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_GRUPPO_ESENZIONI\"")
@NamedQueries({ 
  @NamedQuery(name = "EsenzioneDGruppoEsenzioni.findAll", query = "SELECT e FROM EsenzioneDGruppoEsenzioni e")
  })

public class EsenzioneDGruppoEsenzioni implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"SK_GRUPPO\"", unique = true, nullable = false)
  private Integer skGruppo;

  @Column(name = "\"COD_TIPOLOGIA_GRUPPO\"", nullable = false, length = 2147483647)
  private String codTipologiaGruppo;

  @Column(name = "\"DAT_FINE_VALIDITA\"")
  private Timestamp datFineValidita;

  @Column(name = "\"DAT_INIZIO_VALIDITA\"", nullable = false)
  private Timestamp datInizioValidita;

  @Column(name = "\"DESC_GRUPPO\"", nullable = false, length = 2147483647)
  private String descGruppo;

	@Column(name = "\"INCOMBO\"", nullable = true)
	private Integer incombo;

  //bi-directional many-to-one association to EsenzioneDDocumentoGruppoEsenzioni
  @OneToMany(mappedBy = "esenzioneDGruppoEsenzioni")
  private Set<EsenzioneDDocumentoGruppoEsenzioni> esenzioneDDocumentoGruppoEsenzionis;

  //bi-directional many-to-one association to EsenzioneDEsenzione
  @OneToMany(mappedBy = "esenzioneDGruppoEsenzioni")
  private Set<EsenzioneDEsenzione> esenzioneDEsenziones;

  //bi-directional many-to-one association to EsenzioneRGruppoEsenzioniEsenzioneAuraTipo
  @OneToMany(mappedBy = "esenzioneDGruppoEsenzioni")
  private Set<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> esenzioneRGruppoEsenzioniEsenzioneAuraTipos;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDGruppoEsenzioni")
  private Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDGruppoEsenzioni")
  private Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

  public EsenzioneDGruppoEsenzioni() {
  }

  public Integer getSkGruppo() {
    return this.skGruppo;
  }

  public void setSkGruppo(Integer skGruppo) {
    this.skGruppo = skGruppo;
  }

  public String getCodTipologiaGruppo() {
    return this.codTipologiaGruppo;
  }

  public void setCodTipologiaGruppo(String codTipologiaGruppo) {
    this.codTipologiaGruppo = codTipologiaGruppo;
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

  public String getDescGruppo() {
    return this.descGruppo;
  }

  public void setDescGruppo(String descGruppo) {
    this.descGruppo = descGruppo;
  }

	public Integer getIncombo() {
		return incombo;
	}

	public void setIncombo(Integer incombo) {
		this.incombo = incombo;
	}

  public Set<EsenzioneDDocumentoGruppoEsenzioni> getEsenzioneDDocumentoGruppoEsenzionis() {
    return this.esenzioneDDocumentoGruppoEsenzionis;
  }

  public void setEsenzioneDDocumentoGruppoEsenzionis(Set<EsenzioneDDocumentoGruppoEsenzioni> esenzioneDDocumentoGruppoEsenzionis) {
    this.esenzioneDDocumentoGruppoEsenzionis = esenzioneDDocumentoGruppoEsenzionis;
  }

  public EsenzioneDDocumentoGruppoEsenzioni addEsenzioneDDocumentoGruppoEsenzioni(EsenzioneDDocumentoGruppoEsenzioni esenzioneDDocumentoGruppoEsenzioni) {
    getEsenzioneDDocumentoGruppoEsenzionis().add(esenzioneDDocumentoGruppoEsenzioni);
    esenzioneDDocumentoGruppoEsenzioni.setEsenzioneDGruppoEsenzioni(this);

    return esenzioneDDocumentoGruppoEsenzioni;
  }

  public EsenzioneDDocumentoGruppoEsenzioni removeEsenzioneDDocumentoGruppoEsenzioni(EsenzioneDDocumentoGruppoEsenzioni esenzioneDDocumentoGruppoEsenzioni) {
    getEsenzioneDDocumentoGruppoEsenzionis().remove(esenzioneDDocumentoGruppoEsenzioni);
    esenzioneDDocumentoGruppoEsenzioni.setEsenzioneDGruppoEsenzioni(null);

    return esenzioneDDocumentoGruppoEsenzioni;
  }

  public Set<EsenzioneDEsenzione> getEsenzioneDEsenziones() {
    return this.esenzioneDEsenziones;
  }

  public void setEsenzioneDEsenziones(Set<EsenzioneDEsenzione> esenzioneDEsenziones) {
    this.esenzioneDEsenziones = esenzioneDEsenziones;
  }

  public EsenzioneDEsenzione addEsenzioneDEsenzione(EsenzioneDEsenzione esenzioneDEsenzione) {
    getEsenzioneDEsenziones().add(esenzioneDEsenzione);
    esenzioneDEsenzione.setEsenzioneDGruppoEsenzioni(this);

    return esenzioneDEsenzione;
  }

  public EsenzioneDEsenzione removeEsenzioneDEsenzione(EsenzioneDEsenzione esenzioneDEsenzione) {
    getEsenzioneDEsenziones().remove(esenzioneDEsenzione);
    esenzioneDEsenzione.setEsenzioneDGruppoEsenzioni(null);

    return esenzioneDEsenzione;
  }

  public Set<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos() {
    return this.esenzioneRGruppoEsenzioniEsenzioneAuraTipos;
  }

  public void setEsenzioneRGruppoEsenzioniEsenzioneAuraTipos(Set<EsenzioneRGruppoEsenzioniEsenzioneAuraTipo> esenzioneRGruppoEsenzioniEsenzioneAuraTipos) {
    this.esenzioneRGruppoEsenzioniEsenzioneAuraTipos = esenzioneRGruppoEsenzioniEsenzioneAuraTipos;
  }

  public EsenzioneRGruppoEsenzioniEsenzioneAuraTipo addEsenzioneRGruppoEsenzioniEsenzioneAuraTipo(EsenzioneRGruppoEsenzioniEsenzioneAuraTipo esenzioneRGruppoEsenzioniEsenzioneAuraTipo) {
    getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos().add(esenzioneRGruppoEsenzioniEsenzioneAuraTipo);
    esenzioneRGruppoEsenzioniEsenzioneAuraTipo.setEsenzioneDGruppoEsenzioni(this);

    return esenzioneRGruppoEsenzioniEsenzioneAuraTipo;
  }

  public EsenzioneRGruppoEsenzioniEsenzioneAuraTipo removeEsenzioneRGruppoEsenzioniEsenzioneAuraTipo(EsenzioneRGruppoEsenzioniEsenzioneAuraTipo esenzioneRGruppoEsenzioniEsenzioneAuraTipo) {
    getEsenzioneRGruppoEsenzioniEsenzioneAuraTipos().remove(esenzioneRGruppoEsenzioniEsenzioneAuraTipo);
    esenzioneRGruppoEsenzioniEsenzioneAuraTipo.setEsenzioneDGruppoEsenzioni(null);

    return esenzioneRGruppoEsenzioniEsenzioneAuraTipo;
  }

  public Set<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return this.esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDGruppoEsenzioni(this);

    return esenzioneSPraticaEsenzione;
  }

  public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDGruppoEsenzioni(null);

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
    esenzioneTPraticaEsenzione.setEsenzioneDGruppoEsenzioni(this);

    return esenzioneTPraticaEsenzione;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDGruppoEsenzioni(null);

    return esenzioneTPraticaEsenzione;
  }

}