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
 * The persistent class for the "ESENZIONE_D_DOCUMENTO_STATO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_DOCUMENTO_STATO\"")
@NamedQueries({ 
  @NamedQuery(name="EsenzioneDDocumentoStato.findAll", query="SELECT e FROM EsenzioneDDocumentoStato e"),
  @NamedQuery(name = "EsenzioneDDocumentoStato.findPerCodStato", query = "SELECT e FROM EsenzioneDDocumentoStato e where e.codStato = :codStato") 
  })
public class EsenzioneDDocumentoStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_DOCUMENTO_STATO\"", unique=true, nullable=false)
	private Integer skDocumentoStato;

	@Column(name="\"COD_STATO\"", nullable=false, length=2147483647)
	private String codStato;

	@Column(name="\"DESC_STATO\"", nullable=false, length=2147483647)
	private String descStato;

	//bi-directional many-to-one association to EsenzioneSDocumento
	@OneToMany(mappedBy="esenzioneDDocumentoStato")
	private List<EsenzioneSDocumento> esenzioneSDocumentos;

	//bi-directional many-to-one association to EsenzioneTDocumento
	@OneToMany(mappedBy="esenzioneDDocumentoStato")
	private List<EsenzioneTDocumento> esenzioneTDocumentos;

	public EsenzioneDDocumentoStato() {
	}

	public Integer getSkDocumentoStato() {
		return this.skDocumentoStato;
	}

	public void setSkDocumentoStato(Integer skDocumentoStato) {
		this.skDocumentoStato = skDocumentoStato;
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

	public List<EsenzioneSDocumento> getEsenzioneSDocumentos() {
		return this.esenzioneSDocumentos;
	}

	public void setEsenzioneSDocumentos(List<EsenzioneSDocumento> esenzioneSDocumentos) {
		this.esenzioneSDocumentos = esenzioneSDocumentos;
	}

	public EsenzioneSDocumento addEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().add(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneDDocumentoStato(this);

		return esenzioneSDocumento;
	}

	public EsenzioneSDocumento removeEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().remove(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneDDocumentoStato(null);

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
		esenzioneTDocumento.setEsenzioneDDocumentoStato(this);

		return esenzioneTDocumento;
	}

	public EsenzioneTDocumento removeEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		getEsenzioneTDocumentos().remove(esenzioneTDocumento);
		esenzioneTDocumento.setEsenzioneDDocumentoStato(null);

		return esenzioneTDocumento;
	}

}