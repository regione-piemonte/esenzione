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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO\"")

@NamedQueries({ 
  @NamedQuery(name="EsenzioneDDistrettoSocioSanitario.findAll", query="SELECT e FROM EsenzioneDDistrettoSocioSanitario e"),
  @NamedQuery(name = "EsenzioneDDistrettoSocioSanitario.findByDistrettoId", query = "SELECT ed FROM EsenzioneDDistrettoSocioSanitario ed WHERE ed.skDistrettoSocioSanitario = :distrettoId")
  })
public class EsenzioneDDistrettoSocioSanitario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_DISTRETTO_SOCIO_SANITARIO\"", unique=true, nullable=false)
	private Integer skDistrettoSocioSanitario;

	@Column(name="\"DENOMINAZIONE\"", nullable=false, length=2147483647)
	private String denominazione;

	@Column(name="\"ID_AZIENDA\"", length=6)
	private String idAzienda;

	@Column(name="\"NUM_DISTRETTO\"", nullable=false)
	private Integer numDistretto;

	//bi-directional many-to-one association to EsenredDAziendasanitaria
	@ManyToOne
	@JoinColumn(name = "\"ID_AZIENDA\"", insertable = false, updatable = false)
	private EsenredDAziendasanitaria esenredDAziendasanitaria;

	//bi-directional many-to-one association to EsenzioneSPraticaEsenzione
	@OneToMany(mappedBy="esenzioneDDistrettoSocioSanitario")
	private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

	//bi-directional many-to-one association to EsenzioneTPraticaEsenzione
	@OneToMany(mappedBy="esenzioneDDistrettoSocioSanitario")
	private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

	public EsenzioneDDistrettoSocioSanitario() {
	}

	public Integer getSkDistrettoSocioSanitario() {
		return this.skDistrettoSocioSanitario;
	}

	public void setSkDistrettoSocioSanitario(Integer skDistrettoSocioSanitario) {
		this.skDistrettoSocioSanitario = skDistrettoSocioSanitario;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIdAzienda() {
		return this.idAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.idAzienda = idAzienda;
	}

	public Integer getNumDistretto() {
		return this.numDistretto;
	}

	public void setNumDistretto(Integer numDistretto) {
		this.numDistretto = numDistretto;
	}

	public EsenredDAziendasanitaria getEsenredDAziendasanitaria() {
		return this.esenredDAziendasanitaria;
	}

	public void setEsenredDAziendasanitaria(EsenredDAziendasanitaria esenredDAziendasanitaria) {
		this.esenredDAziendasanitaria = esenredDAziendasanitaria;
	}

	public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
		return this.esenzioneSPraticaEsenziones;
	}

	public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
		this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
	}

	public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenzioneDDistrettoSocioSanitario(this);

		return esenzioneSPraticaEsenzione;
	}

	public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenzioneDDistrettoSocioSanitario(null);

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
		esenzioneTPraticaEsenzione.setEsenzioneDDistrettoSocioSanitario(this);

		return esenzioneTPraticaEsenzione;
	}

	public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
		esenzioneTPraticaEsenzione.setEsenzioneDDistrettoSocioSanitario(null);

		return esenzioneTPraticaEsenzione;
	}

}