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
 * The persistent class for the "ESENZIONE_D_INVALIDITA_TIPO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_INVALIDITA_TIPO\"")
@NamedQuery(name="EsenzioneDInvaliditaTipo.findAll", query="SELECT e FROM EsenzioneDInvaliditaTipo e")
public class EsenzioneDInvaliditaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_INVALIDITA_TIPO\"", unique=true, nullable=false)
	private Integer skInvaliditaTipo;

	@Column(name="\"COD_INVALIDITA_TIPO\"", nullable=false, length=2147483647)
	private String codInvaliditaTipo;

	@Column(name="\"DESC_INVALIDITA_TIPO\"", nullable=false, length=2147483647)
	private String descInvaliditaTipo;

	//bi-directional many-to-one association to EsenzioneSPraticaEsenzione
	@OneToMany(mappedBy="esenzioneDInvaliditaTipo")
	private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

	//bi-directional many-to-one association to EsenzioneTPraticaEsenzione
	@OneToMany(mappedBy="esenzioneDInvaliditaTipo")
	private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

	public EsenzioneDInvaliditaTipo() {
	}

	public Integer getSkInvaliditaTipo() {
		return this.skInvaliditaTipo;
	}

	public void setSkInvaliditaTipo(Integer skInvaliditaTipo) {
		this.skInvaliditaTipo = skInvaliditaTipo;
	}

	public String getCodInvaliditaTipo() {
		return this.codInvaliditaTipo;
	}

	public void setCodInvaliditaTipo(String codInvaliditaTipo) {
		this.codInvaliditaTipo = codInvaliditaTipo;
	}

	public String getDescInvaliditaTipo() {
		return this.descInvaliditaTipo;
	}

	public void setDescInvaliditaTipo(String descInvaliditaTipo) {
		this.descInvaliditaTipo = descInvaliditaTipo;
	}

	public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
		return this.esenzioneSPraticaEsenziones;
	}

	public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
		this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
	}

	public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenzioneDInvaliditaTipo(this);

		return esenzioneSPraticaEsenzione;
	}

	public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenzioneDInvaliditaTipo(null);

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
		esenzioneTPraticaEsenzione.setEsenzioneDInvaliditaTipo(this);

		return esenzioneTPraticaEsenzione;
	}

	public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
		esenzioneTPraticaEsenzione.setEsenzioneDInvaliditaTipo(null);

		return esenzioneTPraticaEsenzione;
	}

}