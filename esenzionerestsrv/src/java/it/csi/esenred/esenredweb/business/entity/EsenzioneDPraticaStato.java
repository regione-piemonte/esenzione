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

/**
 * The persistent class for the "ESENZIONE_D_PRATICA_STATO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_D_PRATICA_STATO\"")
@NamedQueries({ @NamedQuery(name = "EsenzioneDPraticaStato.findAll", query = "SELECT e FROM EsenzioneDPraticaStato e"),
    @NamedQuery(name = "EsenzioneDPraticaStato.findPerCodStato", query = "SELECT e FROM EsenzioneDPraticaStato e where e.codStato = :codStato") })
public class EsenzioneDPraticaStato implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8760140629481862308L;
  @Id
  @Column(name = "\"SK_PRATICA_STATO\"", unique = true, nullable = false)
  private Integer skPraticaStato;

  @Column(name = "\"COD_STATO\"", nullable = false, length = 2147483647)
  private String codStato;

  @Column(name = "\"DESC_STATO\"", nullable = false, length = 2147483647)
  private String descStato;

//bi-directional many-to-one association to EsenzioneDAzioniPrarticaStato
	@OneToMany(mappedBy="esenzioneDPraticaStato")
  private List<EsenzioneRAzioniPraticaStato> esenzioneRAzioniPraticaStatos;
  
  //bi-directional many-to-one association to EsenzioneRMotivazionePraticaStato
  @OneToMany(mappedBy = "esenzioneDPraticaStato")
  private List<EsenzioneRMotivazionePraticaStato> esenzioneRMotivazionePraticaStatos;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDPraticaStato")
  private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneDPraticaStato")
  private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

  public EsenzioneDPraticaStato() {
  }

  public EsenzioneDPraticaStato(Integer skPraticaStato) {
    super();
    this.skPraticaStato = skPraticaStato;
  }

  public Integer getSkPraticaStato() {
    return this.skPraticaStato;
  }

  public void setSkPraticaStato(Integer skPraticaStato) {
    this.skPraticaStato = skPraticaStato;
  }

  public String getCodStato() {
    return this.codStato;
  }

  public void setCodStato(String codStato) {
    this.codStato = codStato;
  }

  public String getDescStato() {
    return this.descStato;
  }

  public void setDescStato(String descStato) {
    this.descStato = descStato;
  }

  public List<EsenzioneRMotivazionePraticaStato> getEsenzioneRMotivazionePraticaStatos() {
    return this.esenzioneRMotivazionePraticaStatos;
  }

  public void setEsenzioneRMotivazionePraticaStatos(List<EsenzioneRMotivazionePraticaStato> esenzioneRMotivazionePraticaStatos) {
    this.esenzioneRMotivazionePraticaStatos = esenzioneRMotivazionePraticaStatos;
  }
  
  public List<EsenzioneRAzioniPraticaStato> getEsenzioneRAzioniPraticaStatos() {
		return esenzioneRAzioniPraticaStatos;
	}

	public void setEsenzioneRAzioniPraticaStatos(List<EsenzioneRAzioniPraticaStato> esenzioneRAzioniPraticaStatos) {
		this.esenzioneRAzioniPraticaStatos = esenzioneRAzioniPraticaStatos;
	}

	public EsenzioneRAzioniPraticaStato addEsenzioneRAzioniPraticaStato(EsenzioneRAzioniPraticaStato esenzioneRAzioniPraticaStato) {
	    getEsenzioneRAzioniPraticaStatos().add(esenzioneRAzioniPraticaStato);
	    esenzioneRAzioniPraticaStato.setEsenzioneDPraticaStato(this);

	    return esenzioneRAzioniPraticaStato;
	  }

	  public EsenzioneRAzioniPraticaStato removeEsenzioneRAzioniPraticaStato(EsenzioneRAzioniPraticaStato esenzioneRAzioniPraticaStato) {
	    getEsenzioneRAzioniPraticaStatos().remove(esenzioneRAzioniPraticaStato);
	    esenzioneRAzioniPraticaStato.setEsenzioneDPraticaStato(null);

	    return esenzioneRAzioniPraticaStato;
	  }
	
  public EsenzioneRMotivazionePraticaStato addEsenzioneRMotivazionePraticaStato(EsenzioneRMotivazionePraticaStato esenzioneRMotivazionePraticaStato) {
    getEsenzioneRMotivazionePraticaStatos().add(esenzioneRMotivazionePraticaStato);
    esenzioneRMotivazionePraticaStato.setEsenzioneDPraticaStato(this);

    return esenzioneRMotivazionePraticaStato;
  }

  public EsenzioneRMotivazionePraticaStato removeEsenzioneRMotivazionePraticaStato(EsenzioneRMotivazionePraticaStato esenzioneRMotivazionePraticaStato) {
    getEsenzioneRMotivazionePraticaStatos().remove(esenzioneRMotivazionePraticaStato);
    esenzioneRMotivazionePraticaStato.setEsenzioneDPraticaStato(null);

    return esenzioneRMotivazionePraticaStato;
  }

  public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return this.esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDPraticaStato(this);

    return esenzioneSPraticaEsenzione;
  }

  public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneDPraticaStato(null);

    return esenzioneSPraticaEsenzione;
  }

  public List<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones() {
    return this.esenzioneTPraticaEsenziones;
  }

  public void setEsenzioneTPraticaEsenziones(List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones) {
    this.esenzioneTPraticaEsenziones = esenzioneTPraticaEsenziones;
  }

  public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().add(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDPraticaStato(this);

    return esenzioneTPraticaEsenzione;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
    esenzioneTPraticaEsenzione.setEsenzioneDPraticaStato(null);

    return esenzioneTPraticaEsenzione;
  }

}