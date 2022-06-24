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
 * The persistent class for the "ESENZIONE_D_DOCUMENTO_TIPO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_DOCUMENTO_TIPO\"")
@NamedQuery(name="EsenzioneDDocumentoTipo.findAll", query="SELECT e FROM EsenzioneDDocumentoTipo e")
public class EsenzioneDDocumentoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_DOCUMENTO_TIPO\"", unique=true, nullable=false)
	private Integer skDocumentoTipo;

	@Column(name="\"COD_DOCUMENTO_TIPO\"", nullable=false, length=2147483647)
	private String codDocumentoTipo;

	@Column(name="\"DESC_DOCUMENTO_TIPO\"", nullable=false, length=2147483647)
	private String descDocumentoTipo;

	//bi-directional many-to-one association to EsenzioneDDocumentoGruppoEsenzioni
	@OneToMany(mappedBy="esenzioneDDocumentoTipo")
	private List<EsenzioneDDocumentoGruppoEsenzioni> esenzioneDDocumentoGruppoEsenzionis;

	//bi-directional many-to-one association to EsenzioneSDocumento
	@OneToMany(mappedBy="esenzioneDDocumentoTipo")
	private List<EsenzioneSDocumento> esenzioneSDocumentos;

	//bi-directional many-to-one association to EsenzioneTDocumento
	@OneToMany(mappedBy="esenzioneDDocumentoTipo")
	private List<EsenzioneTDocumento> esenzioneTDocumentos;

	public EsenzioneDDocumentoTipo() {
	}

	public Integer getSkDocumentoTipo() {
		return this.skDocumentoTipo;
	}

	public void setSkDocumentoTipo(Integer skDocumentoTipo) {
		this.skDocumentoTipo = skDocumentoTipo;
	}

	public String getCodDocumentoTipo() {
		return this.codDocumentoTipo;
	}

	public void setCodDocumentoTipo(String codDocumentoTipo) {
		this.codDocumentoTipo = codDocumentoTipo;
	}

	public String getDescDocumentoTipo() {
		return this.descDocumentoTipo;
	}

	public void setDescDocumentoTipo(String descDocumentoTipo) {
		this.descDocumentoTipo = descDocumentoTipo;
	}

	public List<EsenzioneDDocumentoGruppoEsenzioni> getEsenzioneDDocumentoGruppoEsenzionis() {
		return this.esenzioneDDocumentoGruppoEsenzionis;
	}

	public void setEsenzioneDDocumentoGruppoEsenzionis(List<EsenzioneDDocumentoGruppoEsenzioni> esenzioneDDocumentoGruppoEsenzionis) {
		this.esenzioneDDocumentoGruppoEsenzionis = esenzioneDDocumentoGruppoEsenzionis;
	}

	public EsenzioneDDocumentoGruppoEsenzioni addEsenzioneDDocumentoGruppoEsenzioni(EsenzioneDDocumentoGruppoEsenzioni esenzioneDDocumentoGruppoEsenzioni) {
		getEsenzioneDDocumentoGruppoEsenzionis().add(esenzioneDDocumentoGruppoEsenzioni);
		esenzioneDDocumentoGruppoEsenzioni.setEsenzioneDDocumentoTipo(this);

		return esenzioneDDocumentoGruppoEsenzioni;
	}

	public EsenzioneDDocumentoGruppoEsenzioni removeEsenzioneDDocumentoGruppoEsenzioni(EsenzioneDDocumentoGruppoEsenzioni esenzioneDDocumentoGruppoEsenzioni) {
		getEsenzioneDDocumentoGruppoEsenzionis().remove(esenzioneDDocumentoGruppoEsenzioni);
		esenzioneDDocumentoGruppoEsenzioni.setEsenzioneDDocumentoTipo(null);

		return esenzioneDDocumentoGruppoEsenzioni;
	}

	public List<EsenzioneSDocumento> getEsenzioneSDocumentos() {
		return this.esenzioneSDocumentos;
	}

	public void setEsenzioneSDocumentos(List<EsenzioneSDocumento> esenzioneSDocumentos) {
		this.esenzioneSDocumentos = esenzioneSDocumentos;
	}

	public EsenzioneSDocumento addEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().add(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneDDocumentoTipo(this);

		return esenzioneSDocumento;
	}

	public EsenzioneSDocumento removeEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().remove(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneDDocumentoTipo(null);

		return esenzioneSDocumento;
	}

	public List<EsenzioneTDocumento> getEsenzioneTDocumentos() {
		return this.esenzioneTDocumentos;
	}

	public void setEsenzioneTDocumentos(List<EsenzioneTDocumento> esenzioneTDocumentos) {
		this.esenzioneTDocumentos = esenzioneTDocumentos;
	}

	public EsenzioneTDocumento addEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		getEsenzioneTDocumentos().add(esenzioneTDocumento);
		esenzioneTDocumento.setEsenzioneDDocumentoTipo(this);

		return esenzioneTDocumento;
	}

	public EsenzioneTDocumento removeEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		getEsenzioneTDocumentos().remove(esenzioneTDocumento);
		esenzioneTDocumento.setEsenzioneDDocumentoTipo(null);

		return esenzioneTDocumento;
	}

}