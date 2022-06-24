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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_D_GRUPPO_UTENTI" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_GRUPPO_UTENTI\"")
@NamedQuery(name="EsenzioneDGruppoUtenti.findAll", query="SELECT e FROM EsenzioneDGruppoUtenti e")
public class EsenzioneDGruppoUtenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_GRUPPO_UTENTI\"", unique=true, nullable=false)
	private Integer skGruppoUtenti;

	@Column(name="\"COD_GRUPPO_UTENTI\"", nullable=false, length=2147483647)
	private String codGruppoUtenti;

	@Column(name="\"DESC_GRUPPO_UTENTI\"", nullable=false, length=2147483647)
	private String descGruppoUtenti;

  //bi-directional many-to-one association to EsenzioneDAzioniGruppoUtenti
	@OneToMany(mappedBy="esenzioneDGruppoUtenti")
  private List<EsenzioneRAzioniGruppoUtenti> esenzioneRAzioniGruppoUtentis;

	public EsenzioneDGruppoUtenti() {
	}

	public Integer getSkGruppoUtenti() {
		return this.skGruppoUtenti;
	}

	public void setSkGruppoUtenti(Integer skGruppoUtenti) {
		this.skGruppoUtenti = skGruppoUtenti;
	}

	public String getCodGruppoUtenti() {
		return this.codGruppoUtenti;
	}

	public void setCodGruppoUtenti(String codGruppoUtenti) {
		this.codGruppoUtenti = codGruppoUtenti;
	}

	public String getDescGruppoUtenti() {
		return this.descGruppoUtenti;
	}

	public void setDescGruppoUtenti(String descGruppoUtenti) {
		this.descGruppoUtenti = descGruppoUtenti;
	}

  public List<EsenzioneRAzioniGruppoUtenti> getEsenzioneRAzioniGruppoUtentis() {
    return this.esenzioneRAzioniGruppoUtentis;
	}

  public void setEsenzioneRAzioniGruppoUtentis(List<EsenzioneRAzioniGruppoUtenti> esenzioneRAzioniGruppoUtentis) {
    this.esenzioneRAzioniGruppoUtentis = esenzioneRAzioniGruppoUtentis;
	}

  public EsenzioneRAzioniGruppoUtenti addEsenzioneRAzioniGruppoUtenti(EsenzioneRAzioniGruppoUtenti esenzioneRAzioniGruppoUtenti) {
    getEsenzioneRAzioniGruppoUtentis().add(esenzioneRAzioniGruppoUtenti);
    esenzioneRAzioniGruppoUtenti.setEsenzioneDGruppoUtenti(this);

    return esenzioneRAzioniGruppoUtenti;
	}

  public EsenzioneRAzioniGruppoUtenti removeEsenzioneRAzioniGruppoUtenti(EsenzioneRAzioniGruppoUtenti esenzioneRAzioniGruppoUtenti) {
    getEsenzioneRAzioniGruppoUtentis().remove(esenzioneRAzioniGruppoUtenti);
    esenzioneRAzioniGruppoUtenti.setEsenzioneDGruppoUtenti(null);

    return esenzioneRAzioniGruppoUtenti;
	}

}